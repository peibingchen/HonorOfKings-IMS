package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends Person implements Reportable {
    private int level;
    private int wins;
    private int losses;
    private Team team;
    private final List<Hero> ownedHeroes = new ArrayList<>();

    public Player(String id, String name, String password, int level, int wins, int losses) {
        super(id, name, password, Role.PLAYER);
        this.level = level;
        this.wins = wins;
        this.losses = losses;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException("Level must be positive.");
        }
        this.level = level;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getTotalMatches() {
        return wins + losses;
    }

    public double getWinRate() {
        int total = getTotalMatches();
        return total == 0 ? 0.0 : wins * 100.0 / total;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void addHero(Hero hero) {
        if (hero != null && !ownedHeroes.contains(hero)) {
            ownedHeroes.add(hero);
        }
    }

    public boolean removeHero(Hero hero) {
        return ownedHeroes.remove(hero);
    }

    public List<Hero> getOwnedHeroes() {
        return Collections.unmodifiableList(ownedHeroes);
    }

    @Override
    public String getReport() {
        String teamName = team == null ? "No team" : team.getName();
        return getId() + " | " + getName() + " | team=" + teamName
                + " | level=" + level + " | winRate=" + String.format("%.1f%%", getWinRate());
    }
}
