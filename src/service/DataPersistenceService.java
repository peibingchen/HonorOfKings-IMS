package service;

import model.Equipment;
import model.Hero;
import model.MatchRecord;
import model.Person;
import model.Player;
import model.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataPersistenceService {
    private final GameDataManager dataManager;

    public DataPersistenceService(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void saveAll(Path directory) throws IOException {
        Files.createDirectories(directory);
        saveUsers(directory.resolve("users.csv"));
        savePlayers(directory.resolve("players.csv"));
        saveHeroes(directory.resolve("heroes.csv"));
        saveEquipment(directory.resolve("equipment.csv"));
        saveTeams(directory.resolve("teams.csv"));
        saveTeamMembers(directory.resolve("team-members.csv"));
        savePlayerHeroes(directory.resolve("player-heroes.csv"));
        saveHeroEquipment(directory.resolve("hero-equipment.csv"));
        saveMatchRecords(directory.resolve("match-records.csv"));
        saveMatchPicks(directory.resolve("match-picks.csv"));
    }

    public boolean hasSavedData(Path directory) {
        return Files.exists(directory.resolve("players.csv"))
                && Files.exists(directory.resolve("heroes.csv"))
                && Files.exists(directory.resolve("equipment.csv"))
                && Files.exists(directory.resolve("teams.csv"))
                && Files.exists(directory.resolve("match-records.csv"));
    }

    public GameDataManager loadAll(Path directory) throws IOException {
        return new DataLoadService().loadAll(directory);
    }

    private void saveUsers(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,role");
        for (Person user : dataManager.getUsers()) {
            lines.add(csv(user.getId(), user.getName(), user.getRole().name()));
        }
        Files.write(path, lines);
    }

    private void savePlayers(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,level,wins,losses,teamId");
        for (Player player : dataManager.getPlayers()) {
            String teamId = player.getTeam() == null ? "" : player.getTeam().getId();
            lines.add(csv(player.getId(), player.getName(), String.valueOf(player.getLevel()),
                    String.valueOf(player.getWins()), String.valueOf(player.getLosses()), teamId));
        }
        Files.write(path, lines);
    }

    private void saveHeroes(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,type,attack,defense,health");
        for (Hero hero : dataManager.getHeroes()) {
            lines.add(csv(hero.getId(), hero.getName(), hero.getType().name(),
                    String.valueOf(hero.getAttack()), String.valueOf(hero.getDefense()),
                    String.valueOf(hero.getHealth())));
        }
        Files.write(path, lines);
    }

    private void saveEquipment(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,name,type,power,rating,usageCount");
        for (Equipment item : dataManager.getEquipment()) {
            lines.add(csv(item.getId(), item.getName(), item.getType().name(),
                    String.valueOf(item.getPower()), String.valueOf(item.getRating()),
                    String.valueOf(item.getUsageCount())));
        }
        Files.write(path, lines);
    }

    private void saveTeams(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,name");
        for (Team team : dataManager.getTeams()) {
            lines.add(csv(team.getId(), team.getName()));
        }
        Files.write(path, lines);
    }

    private void saveTeamMembers(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("teamId,playerId");
        for (Team team : dataManager.getTeams()) {
            for (Player player : team.getMembers()) {
                lines.add(csv(team.getId(), player.getId()));
            }
        }
        Files.write(path, lines);
    }

    private void savePlayerHeroes(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("playerId,heroId");
        for (Player player : dataManager.getPlayers()) {
            for (Hero hero : player.getOwnedHeroes()) {
                lines.add(csv(player.getId(), hero.getId()));
            }
        }
        Files.write(path, lines);
    }

    private void saveHeroEquipment(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("heroId,equipmentId");
        for (Hero hero : dataManager.getHeroes()) {
            for (Equipment item : hero.getCompatibleEquipment()) {
                lines.add(csv(hero.getId(), item.getId()));
            }
        }
        Files.write(path, lines);
    }

    private void saveMatchRecords(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("id,teamId,opponent,date,result");
        for (MatchRecord record : dataManager.getMatchRecords()) {
            lines.add(csv(record.getId(), record.getTeam().getId(), record.getOpponent(),
                    record.getDate().toString(), record.getResult().name()));
        }
        Files.write(path, lines);
    }

    private void saveMatchPicks(Path path) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("matchId,playerId,heroId");
        for (MatchRecord record : dataManager.getMatchRecords()) {
            for (Map.Entry<Player, Hero> entry : record.getHeroPicks().entrySet()) {
                lines.add(csv(record.getId(), entry.getKey().getId(), entry.getValue().getId()));
            }
        }
        Files.write(path, lines);
    }

    private String csv(String... values) {
        List<String> escaped = new ArrayList<>();
        for (String value : values) {
            String safe = value == null ? "" : value.replace("\"", "\"\"");
            if (safe.contains(",") || safe.contains("\"") || safe.contains("\n")) {
                safe = "\"" + safe + "\"";
            }
            escaped.add(safe);
        }
        return String.join(",", escaped);
    }
}
