package service;

import model.Hero;
import model.MatchRecord;
import model.MatchResult;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MatchHistoryService {
    private final GameDataManager dataManager;

    public MatchHistoryService(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<MatchRecord> getRecentMatchesForPlayer(Player player, int limit) {
        List<MatchRecord> matches = new ArrayList<>();
        if (player == null || limit <= 0) {
            return matches;
        }
        for (MatchRecord record : dataManager.getMatchRecords()) {
            if (record.getHeroPicks().containsKey(player)) {
                matches.add(record);
            }
        }
        matches.sort(Comparator.comparing(MatchRecord::getDate).reversed());
        return matches.subList(0, Math.min(limit, matches.size()));
    }

    public List<MatchRecord> getRecentMatchesForTeam(Team team, int limit) {
        List<MatchRecord> matches = new ArrayList<>();
        if (team == null || limit <= 0) {
            return matches;
        }
        for (MatchRecord record : dataManager.getMatchRecords()) {
            if (record.getTeam().equals(team)) {
                matches.add(record);
            }
        }
        matches.sort(Comparator.comparing(MatchRecord::getDate).reversed());
        return matches.subList(0, Math.min(limit, matches.size()));
    }

    public String getPlayerWinLossSummary(Player player) {
        int wins = 0;
        int losses = 0;
        for (MatchRecord record : dataManager.getMatchRecords()) {
            if (record.getHeroPicks().containsKey(player)) {
                if (record.getResult() == MatchResult.WIN) {
                    wins++;
                } else {
                    losses++;
                }
            }
        }
        return "Wins=" + wins + ", Losses=" + losses;
    }

    public String getTeamWinLossSummary(Team team) {
        int wins = 0;
        int losses = 0;
        for (MatchRecord record : dataManager.getMatchRecords()) {
            if (record.getTeam().equals(team)) {
                if (record.getResult() == MatchResult.WIN) {
                    wins++;
                } else {
                    losses++;
                }
            }
        }
        return "Wins=" + wins + ", Losses=" + losses;
    }

    public Map<Hero, Double> getHeroPickRatesForPlayer(Player player) {
        Map<Hero, Integer> counts = new LinkedHashMap<>();
        int total = 0;
        for (MatchRecord record : dataManager.getMatchRecords()) {
            Hero hero = record.getHeroPicks().get(player);
            if (hero != null) {
                counts.put(hero, counts.getOrDefault(hero, 0) + 1);
                total++;
            }
        }
        Map<Hero, Double> rates = new LinkedHashMap<>();
        for (Map.Entry<Hero, Integer> entry : counts.entrySet()) {
            rates.put(entry.getKey(), total == 0 ? 0.0 : entry.getValue() * 100.0 / total);
        }
        return rates;
    }

    public String formatMatchHistoryForPlayer(Player player, int limit) {
        StringBuilder builder = new StringBuilder();
        builder.append(getPlayerWinLossSummary(player)).append(System.lineSeparator());
        for (MatchRecord record : getRecentMatchesForPlayer(player, limit)) {
            builder.append(record.getReport()).append(System.lineSeparator());
            Hero pickedHero = record.getHeroPicks().get(player);
            if (pickedHero != null) {
                builder.append("  Picked hero: ").append(pickedHero.getName()).append(System.lineSeparator());
            }
        }
        builder.append("Hero pick rates:").append(System.lineSeparator());
        for (Map.Entry<Hero, Double> entry : getHeroPickRatesForPlayer(player).entrySet()) {
            builder.append("  ").append(entry.getKey().getName()).append(": ")
                    .append(String.format("%.1f%%", entry.getValue())).append(System.lineSeparator());
        }
        return builder.toString();
    }
}
