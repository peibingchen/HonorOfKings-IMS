package service;

import model.BattleResult;
import model.Equipment;
import model.Hero;

import java.util.Random;

public class CombatSimulator {
    private static final double CRITICAL_CHANCE = 0.10;
    private static final double DODGE_CHANCE = 0.08;
    private static final double CRITICAL_MULTIPLIER = 1.5;
    private static final int MAX_ROUNDS = 50;

    private final Random random;

    public CombatSimulator() {
        this(new Random());
    }

    public CombatSimulator(Random random) {
        this.random = random;
    }

    public BattleResult simulate(Hero first, Hero second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Both heroes are required for combat simulation.");
        }

        BattleResult result = new BattleResult();
        int firstHealth = first.getHealth();
        int secondHealth = second.getHealth();
        int rounds = 0;

        result.addLogEntry("Battle started: " + first.getName() + " vs " + second.getName());
        while (firstHealth > 0 && secondHealth > 0 && rounds < MAX_ROUNDS) {
            rounds++;
            secondHealth = performAttack(result, rounds, first, second, secondHealth);
            if (secondHealth <= 0) {
                break;
            }
            firstHealth = performAttack(result, rounds, second, first, firstHealth);
        }

        result.setRounds(rounds);
        if (firstHealth == secondHealth) {
            chooseWinnerByAttack(result, first, second, firstHealth, secondHealth);
        } else if (firstHealth > secondHealth) {
            setOutcome(result, first, second, firstHealth, Math.max(0, secondHealth));
        } else {
            setOutcome(result, second, first, secondHealth, Math.max(0, firstHealth));
        }
        result.addLogEntry("Battle finished. Winner: " + result.getWinner().getName());
        return result;
    }

    public int calculateDamage(Hero attacker, Hero defender) {
        if (attacker == null || defender == null) {
            throw new IllegalArgumentException("Attacker and defender are required.");
        }
        double equipmentBonus = calculateEquipmentAttackBonus(attacker);
        double damage = attacker.getAttack() + equipmentBonus - defender.getDefense() * 0.4;
        return Math.max(1, (int) Math.round(damage));
    }

    private int performAttack(BattleResult result, int round, Hero attacker, Hero defender, int defenderHealth) {
        if (random.nextDouble() < DODGE_CHANCE) {
            result.addLogEntry("Round " + round + ": " + defender.getName() + " dodged "
                    + attacker.getName() + "'s attack.");
            return defenderHealth;
        }

        int damage = calculateDamage(attacker, defender);
        boolean critical = random.nextDouble() < CRITICAL_CHANCE;
        if (critical) {
            damage = Math.max(1, (int) Math.round(damage * CRITICAL_MULTIPLIER));
        }
        int remainingHealth = Math.max(0, defenderHealth - damage);
        String criticalText = critical ? " critical hit" : "";
        result.addLogEntry("Round " + round + ": " + attacker.getName() + criticalText
                + " dealt " + damage + " damage to " + defender.getName()
                + " (remaining HP: " + remainingHealth + ").");
        return remainingHealth;
    }

    private double calculateEquipmentAttackBonus(Hero hero) {
        double bonus = 0.0;
        for (Equipment equipment : hero.getCompatibleEquipment()) {
            bonus += equipment.getPower() * 0.1;
        }
        return bonus;
    }

    private void chooseWinnerByAttack(BattleResult result, Hero first, Hero second, int firstHealth, int secondHealth) {
        if (first.getAttack() >= second.getAttack()) {
            setOutcome(result, first, second, firstHealth, secondHealth);
        } else {
            setOutcome(result, second, first, secondHealth, firstHealth);
        }
    }

    private void setOutcome(BattleResult result, Hero winner, Hero loser, int winnerHealth, int loserHealth) {
        result.setWinner(winner);
        result.setLoser(loser);
        result.setWinnerRemainingHealth(Math.max(0, winnerHealth));
        result.setLoserRemainingHealth(Math.max(0, loserHealth));
    }
}
