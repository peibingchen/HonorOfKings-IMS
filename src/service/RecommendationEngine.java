package service;

import model.Equipment;
import model.EquipmentType;
import model.Hero;
import model.HeroType;
import model.Player;
import model.Recommendation;
import model.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecommendationEngine {
    private final GameDataManager dataManager;

    public RecommendationEngine(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public GameDataManager getDataManager() {
        return dataManager;
    }

    public List<Recommendation> recommendEquipment(Hero hero, int limit) {
        if (hero == null || limit <= 0) {
            return List.of();
        }
        List<Recommendation> recommendations = new ArrayList<>();
        for (Equipment item : dataManager.getEquipment()) {
            double compatibleBonus = hero.getCompatibleEquipment().contains(item) ? 10.0 : 0.0;
            double typeBonus = getEquipmentTypeBonus(hero.getType(), item.getType());
            double usageBonus = Math.min(5.0, item.getUsageCount() * 0.5);
            double score = item.getScore() + compatibleBonus + typeBonus + usageBonus;
            recommendations.add(new Recommendation(
                    item.getId(),
                    item.getName(),
                    "Equipment",
                    score,
                    buildEquipmentReason(hero, item, compatibleBonus, typeBonus)));
        }
        recommendations.sort(Comparator.comparing(Recommendation::getScore).reversed()
                .thenComparing(Recommendation::getTargetName));
        return limit(recommendations, limit);
    }

    public List<Recommendation> recommendHeroes(Player player, int limit) {
        if (player == null || limit <= 0) {
            return List.of();
        }
        Set<HeroType> ownedTypes = EnumSet.noneOf(HeroType.class);
        Set<String> ownedHeroIds = new java.util.HashSet<>();
        for (Hero hero : player.getOwnedHeroes()) {
            ownedTypes.add(hero.getType());
            ownedHeroIds.add(hero.getId());
        }

        List<Recommendation> recommendations = new ArrayList<>();
        for (Hero hero : dataManager.getHeroes()) {
            if (ownedHeroIds.contains(hero.getId())) {
                continue;
            }
            double roleNeedBonus = ownedTypes.contains(hero.getType()) ? 0.0 : 15.0;
            double score = getHeroStatScore(hero) + roleNeedBonus + hero.getCompatibleEquipment().size() * 1.5;
            String reason = roleNeedBonus > 0.0
                    ? "fills missing " + hero.getType() + " role"
                    : "adds another strong " + hero.getType() + " option";
            recommendations.add(new Recommendation(hero.getId(), hero.getName(), "Hero", score, reason));
        }
        recommendations.sort(Comparator.comparing(Recommendation::getScore).reversed()
                .thenComparing(Recommendation::getTargetName));
        return limit(recommendations, limit);
    }

    public List<Recommendation> recommendTeamComposition(Team team) {
        if (team == null) {
            return List.of();
        }
        Map<HeroType, Integer> coverage = new EnumMap<>(HeroType.class);
        for (Player member : team.getMembers()) {
            for (Hero hero : member.getOwnedHeroes()) {
                coverage.merge(hero.getType(), 1, Integer::sum);
            }
        }

        List<Recommendation> recommendations = new ArrayList<>();
        addMissingRoleRecommendation(recommendations, coverage, HeroType.TANK);
        addMissingRoleRecommendation(recommendations, coverage, HeroType.MAGE);
        addMissingRoleRecommendation(recommendations, coverage, HeroType.MARKSMAN);
        addMissingRoleRecommendation(recommendations, coverage, HeroType.SUPPORT);
        if (!coverage.containsKey(HeroType.WARRIOR) && !coverage.containsKey(HeroType.ASSASSIN)) {
            Hero hero = findBestHeroByType(HeroType.WARRIOR);
            if (hero == null) {
                hero = findBestHeroByType(HeroType.ASSASSIN);
            }
            if (hero != null) {
                recommendations.add(new Recommendation(hero.getId(), hero.getName(), "Team Role",
                        getHeroStatScore(hero), "team needs a WARRIOR or ASSASSIN damage role"));
            }
        }
        recommendations.sort(Comparator.comparing(Recommendation::getScore).reversed()
                .thenComparing(Recommendation::getTargetName));
        return recommendations;
    }

    private double getEquipmentTypeBonus(HeroType heroType, EquipmentType equipmentType) {
        return switch (heroType) {
            case TANK -> equipmentType == EquipmentType.DEFENSE || equipmentType == EquipmentType.SUPPORT ? 8.0 : 0.0;
            case WARRIOR -> equipmentType == EquipmentType.ATTACK || equipmentType == EquipmentType.DEFENSE ? 8.0 : 0.0;
            case ASSASSIN -> equipmentType == EquipmentType.ATTACK || equipmentType == EquipmentType.MOVEMENT ? 8.0 : 0.0;
            case MAGE -> equipmentType == EquipmentType.MAGIC ? 8.0 : 0.0;
            case MARKSMAN -> equipmentType == EquipmentType.ATTACK || equipmentType == EquipmentType.MOVEMENT ? 8.0 : 0.0;
            case SUPPORT -> equipmentType == EquipmentType.SUPPORT || equipmentType == EquipmentType.DEFENSE ? 8.0 : 0.0;
        };
    }

    private String buildEquipmentReason(Hero hero, Equipment item, double compatibleBonus, double typeBonus) {
        if (compatibleBonus > 0.0 && typeBonus > 0.0) {
            return "compatible with " + hero.getName() + " and suitable for " + hero.getType();
        }
        if (compatibleBonus > 0.0) {
            return "already marked as compatible with " + hero.getName();
        }
        if (typeBonus > 0.0) {
            return item.getType() + " equipment supports " + hero.getType() + " play style";
        }
        return "ranked by equipment score and usage";
    }

    private void addMissingRoleRecommendation(List<Recommendation> recommendations,
                                              Map<HeroType, Integer> coverage,
                                              HeroType type) {
        if (coverage.containsKey(type)) {
            return;
        }
        Hero hero = findBestHeroByType(type);
        if (hero != null) {
            recommendations.add(new Recommendation(hero.getId(), hero.getName(), "Team Role",
                    getHeroStatScore(hero), "team has no " + type + " coverage"));
        }
    }

    private Hero findBestHeroByType(HeroType type) {
        Hero best = null;
        for (Hero hero : dataManager.getHeroes()) {
            if (hero.getType() != type) {
                continue;
            }
            if (best == null || getHeroStatScore(hero) > getHeroStatScore(best)) {
                best = hero;
            }
        }
        return best;
    }

    private double getHeroStatScore(Hero hero) {
        return hero.getAttack() * 0.35 + hero.getDefense() * 0.25 + hero.getHealth() * 0.02;
    }

    private List<Recommendation> limit(List<Recommendation> recommendations, int limit) {
        return recommendations.subList(0, Math.min(limit, recommendations.size()));
    }
}
