package service;

import model.Equipment;
import model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingService {
    private final GameDataManager dataManager;

    public RankingService(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Player> topPlayersByWinRate(int limit) {
        List<Player> players = new ArrayList<>(dataManager.getPlayers());
        players.sort(Comparator.comparing(Player::getWinRate).reversed()
                .thenComparing(Player::getLevel, Comparator.reverseOrder())
                .thenComparing(Player::getName));
        return players.subList(0, Math.min(limit, players.size()));
    }

    public List<Player> topPlayersByLevel(int limit) {
        List<Player> players = new ArrayList<>(dataManager.getPlayers());
        players.sort(Comparator.comparing(Player::getLevel).reversed()
                .thenComparing(Player::getWinRate, Comparator.reverseOrder())
                .thenComparing(Player::getName));
        return players.subList(0, Math.min(limit, players.size()));
    }

    public List<Player> topPlayersByMatches(int limit) {
        List<Player> players = new ArrayList<>(dataManager.getPlayers());
        players.sort(Comparator.comparing(Player::getTotalMatches).reversed()
                .thenComparing(Player::getWinRate, Comparator.reverseOrder())
                .thenComparing(Player::getName));
        return players.subList(0, Math.min(limit, players.size()));
    }

    public List<Equipment> rankEquipmentByScore() {
        List<Equipment> items = new ArrayList<>(dataManager.getEquipment());
        items.sort(Comparator.comparing(Equipment::getScore).reversed()
                .thenComparing(Equipment::getName));
        return items;
    }
}
