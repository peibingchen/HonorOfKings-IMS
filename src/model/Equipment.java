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

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Equipment name cannot be empty.");
        }
        this.name = name.trim();
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        if (type == null) {
            throw new IllegalArgumentException("Equipment type cannot be null.");
        }
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException("Power cannot be negative.");
        }
        this.power = power;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
        this.rating = rating;
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
