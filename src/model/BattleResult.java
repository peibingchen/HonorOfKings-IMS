package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattleResult implements Reportable {
    private Hero winner;
    private Hero loser;
    private int rounds;
    private final List<String> combatLog = new ArrayList<>();

    public BattleResult() {
    }

    public Hero getWinner() {
        return winner;
    }

    public void setWinner(Hero winner) {
        this.winner = winner;
    }

    public Hero getLoser() {
        return loser;
    }

    public void setLoser(Hero loser) {
        this.loser = loser;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void addLogEntry(String entry) {
        if (entry != null && !entry.trim().isEmpty()) {
            combatLog.add(entry.trim());
        }
    }

    public List<String> getCombatLog() {
        return Collections.unmodifiableList(combatLog);
    }

    @Override
    public String getReport() {
        String winnerName = winner == null ? "TBD" : winner.getName();
        String loserName = loser == null ? "TBD" : loser.getName();
        return "BattleResult | winner=" + winnerName + " | loser=" + loserName + " | rounds=" + rounds;
    }
}
