package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team implements Reportable {
    private final String id;
    private String name;
    private final List<Player> members = new ArrayList<>();

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be empty.");
        }
        this.name = name.trim();
    }

    public void addMember(Player player) {
        if (player != null && !members.contains(player)) {
            members.add(player);
            player.setTeam(this);
        }
    }

    public boolean removeMember(String playerId) {
        for (Player player : new ArrayList<>(members)) {
            if (player.getId().equals(playerId)) {
                members.remove(player);
                player.setTeam(null);
                return true;
            }
        }
        return false;
    }

    public List<Player> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public double getAverageLevel() {
        if (members.isEmpty()) {
            return 0.0;
        }
        int total = 0;
        for (Player player : members) {
            total += player.getLevel();
        }
        return total * 1.0 / members.size();
    }

    public int getTotalMatches() {
        int total = 0;
        for (Player player : members) {
            total += player.getTotalMatches();
        }
        return total;
    }

    public double getWinRate() {
        int wins = 0;
        int matches = 0;
        for (Player player : members) {
            wins += player.getWins();
            matches += player.getTotalMatches();
        }
        return matches == 0 ? 0.0 : wins * 100.0 / matches;
    }

    public Player getTopPlayer() {
        Player best = null;
        for (Player player : members) {
            if (best == null || player.getWinRate() > best.getWinRate()
                    || (player.getWinRate() == best.getWinRate() && player.getLevel() > best.getLevel())) {
                best = player;
            }
        }
        return best;
    }

    @Override
    public String getReport() {
        Player top = getTopPlayer();
        return id + " | " + name + " | members=" + members.size()
                + " | avgLevel=" + String.format("%.1f", getAverageLevel())
                + " | matches=" + getTotalMatches()
                + " | winRate=" + String.format("%.1f%%", getWinRate())
                + " | topPlayer=" + (top == null ? "N/A" : top.getName());
    }
}
