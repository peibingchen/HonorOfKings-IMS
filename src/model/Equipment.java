package model;

public class Equipment implements Reportable {
    private final String id;
    private String name;
    private EquipmentType type;
    private int power;
    private double rating;
    private int usageCount;

    public Equipment(String id, String name, EquipmentType type, int power, double rating) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.power = power;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EquipmentType getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public double getRating() {
        return rating;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public void incrementUsageCount() {
        usageCount++;
    }

    public double getScore() {
        return usageCount * 2.0 + rating + power / 100.0;
    }

    @Override
    public String getReport() {
        return id + " | " + name + " | " + type + " | power=" + power
                + " | rating=" + String.format("%.1f", rating)
                + " | usage=" + usageCount
                + " | score=" + String.format("%.2f", getScore());
    }
}
