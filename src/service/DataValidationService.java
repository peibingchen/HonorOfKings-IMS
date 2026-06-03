package service;

public class DataValidationService {
    public void requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
    }

    public void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive.");
        }
    }

    public void requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative.");
        }
    }

    public void requireRating(double rating) {
        if (rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 0 and 5.");
        }
    }

    public void requireFound(Object value, String label, String id) {
        if (value == null) {
            throw new IllegalArgumentException(label + " not found: " + id);
        }
    }
}
