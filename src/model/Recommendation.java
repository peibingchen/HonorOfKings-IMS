package model;

public class Recommendation implements Reportable {
    private final String targetId;
    private final String targetName;
    private final String type;
    private final double score;
    private final String reason;

    public Recommendation(String targetId, String targetName, String type, double score, String reason) {
        this.targetId = targetId;
        this.targetName = targetName;
        this.type = type;
        this.score = score;
        this.reason = reason;
    }

    public String getTargetId() {
        return targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public String getType() {
        return type;
    }

    public double getScore() {
        return score;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String getReport() {
        return type + " | " + targetId + " | " + targetName
                + " | score=" + String.format("%.2f", score)
                + " | reason=" + reason;
    }
}
