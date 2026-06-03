package service;

import model.Equipment;
import model.Hero;
import model.Player;
import model.Team;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStorageService {
    private final GameDataManager dataManager;

    public FileStorageService(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void saveSummary(Path path) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("# Honor of Kings IMS Data Summary\n\n");
        builder.append("Players\n");
        for (Player player : dataManager.getPlayers()) {
            builder.append(player.getReport()).append('\n');
        }
        builder.append("\nTeams\n");
        for (Team team : dataManager.getTeams()) {
            builder.append(team.getReport()).append('\n');
        }
        builder.append("\nHeroes\n");
        for (Hero hero : dataManager.getHeroes()) {
            builder.append(hero.getReport()).append('\n');
        }
        builder.append("\nEquipment\n");
        for (Equipment item : dataManager.getEquipment()) {
            builder.append(item.getReport()).append('\n');
        }
        Files.createDirectories(path.getParent());
        Files.writeString(path, builder.toString());
    }
}
