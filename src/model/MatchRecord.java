package model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchRecord implements Reportable {
    private final String id;
    private Team team;
    private String opponent;
    private LocalDate date;
    private MatchResult result;
    private final Map<Player, Hero> heroPicks = new LinkedHashMap<>();

    public MatchRecord(String id, Team team, String opponent, LocalDate date, MatchResult result) {
        this.id = id;
        this.team = team;
        this.opponent = opponent;
        this.date = date;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null.");
        }
        this.team = team;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        if (opponent == null || opponent.trim().isEmpty()) {
            throw new IllegalArgumentException("Opponent cannot be empty.");
        }
        this.opponent = opponent.trim();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.date = date;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        if (result == null) {
            throw new IllegalArgumentException("Result cannot be null.");
        }
        this.result = result;
    }

    public void addHeroPick(Player player, Hero hero) {
        if (player != null && hero != null) {
            heroPicks.put(player, hero);
        }
    }

    public boolean removeHeroPick(Player player) {
        return heroPicks.remove(player) != null;
    }

    public boolean removeHeroPickByHero(Hero hero) {
        return heroPicks.entrySet().removeIf(entry -> entry.getValue().equals(hero));
    }

    public void clearHeroPicks() {
        heroPicks.clear();
    }

    public Map<Player, Hero> getHeroPicks() {
        return heroPicks;
    }

    @Override
    public String getReport() {
        return id + " | " + team.getName() + " vs " + opponent + " | " + date + " | " + result;
    }
}
