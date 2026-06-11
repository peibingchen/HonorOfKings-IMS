package service;

import model.Admin;
import model.Equipment;
import model.EquipmentType;
import model.Hero;
import model.HeroType;
import model.MatchRecord;
import model.MatchResult;
import model.Player;
import model.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataLoadService {
    private static final List<String> REQUIRED_FILES = List.of(
            "players.csv",
            "heroes.csv",
            "equipment.csv",
            "teams.csv",
            "match-records.csv"
    );

    public boolean canLoad(Path directory) {
        for (String fileName : REQUIRED_FILES) {
            if (!Files.exists(directory.resolve(fileName))) {
                return false;
            }
        }
        return true;
    }

    public PersistenceReport validateFiles(Path directory) throws IOException {
        PersistenceReport report = new PersistenceReport("Load data");
        for (String fileName : REQUIRED_FILES) {
            Path file = directory.resolve(fileName);
            if (!Files.exists(file)) {
                report.addWarning("Missing required file: " + fileName);
            }
        }
        validateMinimumColumns(report, directory.resolve("players.csv"), 5);
        validateMinimumColumns(report, directory.resolve("heroes.csv"), 6);
        validateMinimumColumns(report, directory.resolve("equipment.csv"), 5);
        validateMinimumColumns(report, directory.resolve("teams.csv"), 2);
        validateMinimumColumns(report, directory.resolve("match-records.csv"), 5);
        validateMinimumColumns(report, directory.resolve("team-members.csv"), 2);
        validateMinimumColumns(report, directory.resolve("player-heroes.csv"), 2);
        validateMinimumColumns(report, directory.resolve("hero-equipment.csv"), 2);
        validateMinimumColumns(report, directory.resolve("match-picks.csv"), 3);
        return report;
    }

    public GameDataManager loadAll(Path directory) throws IOException {
        GameDataManager loaded = new GameDataManager();
        loadInto(directory, loaded);
        return loaded;
    }

    public void loadInto(Path directory, GameDataManager target) throws IOException {
        if (!canLoad(directory)) {
            throw new IOException("Required data files are missing in " + directory);
        }
        target.clearAll();
        loadAdmins(directory.resolve("users.csv"), target);
        loadEquipment(directory.resolve("equipment.csv"), target);
        loadHeroes(directory.resolve("heroes.csv"), target);
        loadTeams(directory.resolve("teams.csv"), target);
        loadPlayers(directory.resolve("players.csv"), target);
        loadTeamMembers(directory.resolve("team-members.csv"), target);
        loadPlayerHeroes(directory.resolve("player-heroes.csv"), target);
        loadHeroEquipment(directory.resolve("hero-equipment.csv"), target);
        loadMatchRecords(directory.resolve("match-records.csv"), target);
        loadMatchPicks(directory.resolve("match-picks.csv"), target);
    }

    private void loadAdmins(Path path, GameDataManager target) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        for (String[] row : readRows(path)) {
            if (row.length >= 3 && "ADMIN".equalsIgnoreCase(row[2])) {
                target.addAdmin(new Admin(row[0], row[1], "admin123"));
            }
        }
    }

    private void loadPlayers(Path path, GameDataManager target) throws IOException {
        for (String[] row : readRows(path)) {
            if (row.length >= 5) {
                Player player = new Player(row[0], row[1], row[0].toLowerCase(),
                        parseInt(row[2]), parseInt(row[3]), parseInt(row[4]));
                target.addPlayer(player);
                if (row.length >= 6 && !row[5].isBlank()) {
                    Team team = target.getTeam(row[5]);
                    if (team != null) {
                        team.addMember(player);
                    }
                }
            }
        }
    }

    private void loadHeroes(Path path, GameDataManager target) throws IOException {
        for (String[] row : readRows(path)) {
            if (row.length >= 6) {
                target.addHero(new Hero(row[0], row[1], HeroType.valueOf(row[2]),
                        parseInt(row[3]), parseInt(row[4]), parseInt(row[5])));
            }
        }
    }

    private void loadEquipment(Path path, GameDataManager target) throws IOException {
        for (String[] row : readRows(path)) {
            if (row.length >= 5) {
                target.addEquipment(new Equipment(row[0], row[1], EquipmentType.valueOf(row[2]),
                        parseInt(row[3]), Double.parseDouble(row[4])));
            }
        }
    }

    private void loadTeams(Path path, GameDataManager target) throws IOException {
        for (String[] row : readRows(path)) {
            if (row.length >= 2) {
                target.addTeam(new Team(row[0], row[1]));
            }
        }
    }

    private void loadTeamMembers(Path path, GameDataManager target) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        for (String[] row : readRows(path)) {
            if (row.length >= 2 && target.getTeam(row[0]) != null && target.getPlayer(row[1]) != null) {
                target.getTeam(row[0]).addMember(target.getPlayer(row[1]));
            }
        }
    }

    private void loadPlayerHeroes(Path path, GameDataManager target) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        for (String[] row : readRows(path)) {
            if (row.length >= 2 && target.getPlayer(row[0]) != null && target.getHero(row[1]) != null) {
                target.getPlayer(row[0]).addHero(target.getHero(row[1]));
            }
        }
    }

    private void loadHeroEquipment(Path path, GameDataManager target) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        for (String[] row : readRows(path)) {
            if (row.length >= 2 && target.getHero(row[0]) != null && target.getEquipment(row[1]) != null) {
                target.getHero(row[0]).addCompatibleEquipment(target.getEquipment(row[1]));
            }
        }
    }

    private void loadMatchRecords(Path path, GameDataManager target) throws IOException {
        for (String[] row : readRows(path)) {
            if (row.length >= 5 && target.getTeam(row[1]) != null) {
                target.addMatchRecord(new MatchRecord(row[0], target.getTeam(row[1]), row[2],
                        LocalDate.parse(row[3]), MatchResult.valueOf(row[4])));
            }
        }
    }

    private void loadMatchPicks(Path path, GameDataManager target) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        for (String[] row : readRows(path)) {
            if (row.length >= 3 && target.getMatchRecord(row[0]) != null
                    && target.getPlayer(row[1]) != null && target.getHero(row[2]) != null) {
                target.getMatchRecord(row[0]).addHeroPick(target.getPlayer(row[1]), target.getHero(row[2]));
            }
        }
    }

    private List<String[]> readRows(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        List<String[]> rows = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            if (!lines.get(i).isBlank()) {
                rows.add(parseCsvLine(lines.get(i)));
            }
        }
        return rows;
    }

    private String[] parseCsvLine(String line) {
        List<String> values = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char ch = line.charAt(i);
            if (ch == '"') {
                if (quoted && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else {
                    quoted = !quoted;
                }
            } else if (ch == ',' && !quoted) {
                values.add(current.toString());
                current.setLength(0);
            } else {
                current.append(ch);
            }
        }
        values.add(current.toString());
        return values.toArray(new String[0]);
    }

    private int parseInt(String value) {
        return Integer.parseInt(value.trim());
    }

    private void validateMinimumColumns(PersistenceReport report, Path path, int minimumColumns) throws IOException {
        if (!Files.exists(path)) {
            return;
        }
        List<String> lines = Files.readAllLines(path);
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                continue;
            }
            int actualColumns = parseCsvLine(line).length;
            if (actualColumns < minimumColumns) {
                report.addWarning(path.getFileName() + " line " + (i + 1)
                        + " has " + actualColumns + " columns; expected at least " + minimumColumns + ".");
            }
        }
    }
}
