package model;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class MatchRecord implements Reportable {
    private final String id;
    private final Team team;
    private final String opponent;
    private final LocalDate date;
    private final MatchResult result;
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

    public String getOpponent() {
        return opponent;
    }

    public LocalDate getDate() {
        return date;
    }

    public MatchResult getResult() {
        return result;
    }

    public void addHeroPick(Player player, Hero hero) {
        if (player != null && hero != null) {
            heroPicks.put(player, hero);
        }
    }

    public Map<Player, Hero> getHeroPicks() {
        return heroPicks;
    }

    @Override
    public String getReport() {
        return id + " | " + team.getName() + " vs " + opponent + " | " + date + " | " + result;
    }
}
