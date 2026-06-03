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

    public HeroType getType() {
        return type;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getHealth() {
        return health;
    }

    public void addCompatibleEquipment(Equipment equipment) {
        if (equipment != null && !compatibleEquipment.contains(equipment)) {
            compatibleEquipment.add(equipment);
            equipment.incrementUsageCount();
        }
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
