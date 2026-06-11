package service;

import model.Hero;
import model.Player;
import model.Recommendation;
import model.Team;

import java.util.Collections;
import java.util.List;

public class RecommendationEngine {
    private final GameDataManager dataManager;

    public RecommendationEngine(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public GameDataManager getDataManager() {
        return dataManager;
    }

    public List<Recommendation> recommendEquipment(Hero hero, int limit) {
        return Collections.emptyList();
    }

    public List<Recommendation> recommendHeroes(Player player, int limit) {
        return Collections.emptyList();
    }

    public List<Recommendation> recommendTeamComposition(Team team) {
        return Collections.emptyList();
    }
}
