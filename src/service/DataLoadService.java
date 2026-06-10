package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataLoadService {
    public boolean canLoad(Path directory) {
        return Files.exists(directory.resolve("players.csv"))
                && Files.exists(directory.resolve("heroes.csv"))
                && Files.exists(directory.resolve("equipment.csv"))
                && Files.exists(directory.resolve("teams.csv"))
                && Files.exists(directory.resolve("match-records.csv"));
    }

    public GameDataManager loadAll(Path directory) throws IOException {
        if (!canLoad(directory)) {
            throw new IOException("Required data files are missing in " + directory);
        }
        throw new UnsupportedOperationException("CSV loading framework is present but not implemented yet.");
    }
}
