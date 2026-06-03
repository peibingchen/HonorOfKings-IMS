package service;

import model.Hero;
import model.MatchRecord;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SearchService {
    private final GameDataManager dataManager;

    public SearchService(GameDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Player findPlayer(String idOrName) {
        String query = normalize(idOrName);
        for (Player player : dataManager.getPlayers()) {
            if (normalize(player.getId()).equals(query) || normalize(player.getName()).contains(query)) {
                return player;
            }
        }
        return null;
    }

    public Team findTeam(String idOrName) {
        String query = normalize(idOrName);
        for (Team team : dataManager.getTeams()) {
            if (normalize(team.getId()).equals(query) || normalize(team.getName()).contains(query)) {
                return team;
            }
        }
        return null;
    }

    public Hero findHero(String idOrName) {
        String query = normalize(idOrName);
        for (Hero hero : dataManager.getHeroes()) {
            if (normalize(hero.getId()).equals(query) || normalize(hero.getName()).contains(query)) {
                return hero;
            }
        }
        return null;
    }

    public List<Player> findPlayersOwningHero(Hero hero) {
        List<Player> owners = new ArrayList<>();
        for (Player player : dataManager.getPlayers()) {
            if (player.getOwnedHeroes().contains(hero)) {
                owners.add(player);
            }
        }
        return owners;
    }

    public List<MatchRecord> findRecentMatchesForPlayer(Player player, int limit) {
        List<MatchRecord> matches = new ArrayList<>();
        for (MatchRecord record : dataManager.getMatchRecords()) {
            if (record.getHeroPicks().containsKey(player)) {
                matches.add(record);
            }
        }
        matches.sort(Comparator.comparing(MatchRecord::getDate).reversed());
        return matches.subList(0, Math.min(limit, matches.size()));
    }

    public List<MatchRecord> findRecentMatchesForTeam(Team team, int limit) {
        List<MatchRecord> matches = new ArrayList<>();
        for (MatchRecord record : dataManager.getMatchRecords()) {
            if (record.getTeam().equals(team)) {
                matches.add(record);
            }
        }
        matches.sort(Comparator.comparing(MatchRecord::getDate).reversed());
        return matches.subList(0, Math.min(limit, matches.size()));
    }

    public double getHeroPickRate(Player player, Hero hero) {
        int total = 0;
        int picked = 0;
        for (MatchRecord record : dataManager.getMatchRecords()) {
            for (Map.Entry<Player, Hero> entry : record.getHeroPicks().entrySet()) {
                if (entry.getKey().equals(player)) {
                    total++;
                    if (entry.getValue().equals(hero)) {
                        picked++;
                    }
                }
            }
        }
        return total == 0 ? 0.0 : picked * 100.0 / total;
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }
}
