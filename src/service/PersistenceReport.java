package service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersistenceReport {
    private final String operation;
    private boolean successful;
    private Path backupDirectory;
    private final List<String> messages = new ArrayList<>();
    private final List<String> warnings = new ArrayList<>();

    public PersistenceReport(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Path getBackupDirectory() {
        return backupDirectory;
    }

    public void setBackupDirectory(Path backupDirectory) {
        this.backupDirectory = backupDirectory;
    }

    public void addMessage(String message) {
        if (message != null && !message.trim().isEmpty()) {
            messages.add(message.trim());
        }
    }

    public void addWarning(String warning) {
        if (warning != null && !warning.trim().isEmpty()) {
            warnings.add(warning.trim());
        }
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public List<String> getWarnings() {
        return Collections.unmodifiableList(warnings);
    }

    public String format() {
        StringBuilder builder = new StringBuilder();
        builder.append(operation).append(successful ? " completed." : " failed.").append(System.lineSeparator());
        if (backupDirectory != null) {
            builder.append("Backup: ").append(backupDirectory).append(System.lineSeparator());
        }
        for (String message : messages) {
            builder.append("- ").append(message).append(System.lineSeparator());
        }
        for (String warning : warnings) {
            builder.append("Warning: ").append(warning).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
