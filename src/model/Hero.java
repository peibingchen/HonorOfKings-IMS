package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hero implements Reportable {
    private final String id;
    private String name;
    private HeroType type;
    private int attack;
    private int defense;
    private int health;
    private final List<Equipment> compatibleEquipment = new ArrayList<>();

    public Hero(String id, String name, HeroType type, int attack, int defense, int health) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.health = health;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Hero name cannot be empty.");
        }
        this.name = name.trim();
    }

    public HeroType getType() {
        return type;
    }

    public void setType(HeroType type) {
        if (type == null) {
            throw new IllegalArgumentException("Hero type cannot be null.");
        }
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (attack < 0) {
            throw new IllegalArgumentException("Attack cannot be negative.");
        }
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        if (defense < 0) {
            throw new IllegalArgumentException("Defense cannot be negative.");
        }
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health <= 0) {
            throw new IllegalArgumentException("Health must be positive.");
        }
        this.health = health;
    }

    public void addCompatibleEquipment(Equipment equipment) {
        if (equipment != null && !compatibleEquipment.contains(equipment)) {
            compatibleEquipment.add(equipment);
            equipment.incrementUsageCount();
        }
    }

    public boolean removeCompatibleEquipment(Equipment equipment) {
        return compatibleEquipment.remove(equipment);
    }

    public List<Equipment> getCompatibleEquipment() {
        return Collections.unmodifiableList(compatibleEquipment);
    }

    @Override
    public String getReport() {
        return id + " | " + name + " | " + type + " | atk=" + attack
                + " | def=" + defense + " | hp=" + health;
    }
}
