package service;

import model.Admin;
import model.Equipment;
import model.Hero;
import model.MatchRecord;
import model.Person;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameDataManager {
    private final Map<String, Person> users = new LinkedHashMap<>();
    private final Map<String, Player> players = new LinkedHashMap<>();
    private final Map<String, Admin> admins = new LinkedHashMap<>();
    private final Map<String, Hero> heroes = new LinkedHashMap<>();
    private final Map<String, Equipment> equipment = new LinkedHashMap<>();
    private final Map<String, Team> teams = new LinkedHashMap<>();
    private final List<MatchRecord> matchRecords = new ArrayList<>();

    public void addAdmin(Admin admin) {
        requireUniqueUser(admin.getId());
        admins.put(admin.getId(), admin);
        users.put(admin.getId(), admin);
    }

    public void addPlayer(Player player) {
        requireUniqueUser(player.getId());
        players.put(player.getId(), player);
        users.put(player.getId(), player);
    }

    public void addHero(Hero hero) {
        requireUnique(heroes, hero.getId(), "Hero");
        heroes.put(hero.getId(), hero);
    }

    public void addEquipment(Equipment item) {
        requireUnique(equipment, item.getId(), "Equipment");
        equipment.put(item.getId(), item);
    }

    public void addTeam(Team team) {
        requireUnique(teams, team.getId(), "Team");
        teams.put(team.getId(), team);
    }

    public void addMatchRecord(MatchRecord record) {
        matchRecords.add(record);
    }

    public void clearAll() {
        users.clear();
        players.clear();
        admins.clear();
        heroes.clear();
        equipment.clear();
        teams.clear();
        matchRecords.clear();
    }

    public boolean deletePlayer(String id) {
        Person person = users.get(id);
        if (!(person instanceof Player)) {
            return false;
        }
        for (Team team : teams.values()) {
            team.removeMember(id);
        }
        players.remove(id);
        users.remove(id);
        return true;
    }

    public boolean deleteHero(String id) {
        return heroes.remove(id) != null;
    }

    public boolean deleteEquipment(String id) {
        return equipment.remove(id) != null;
    }

    public boolean deleteTeam(String id) {
        return teams.remove(id) != null;
    }

    public boolean deleteMatchRecord(String id) {
        return matchRecords.removeIf(record -> record.getId().equals(id));
    }

    public Person getUser(String id) {
        return users.get(id);
    }

    public Player getPlayer(String id) {
        return players.get(id);
    }

    public Hero getHero(String id) {
        return heroes.get(id);
    }

    public Equipment getEquipment(String id) {
        return equipment.get(id);
    }

    public Team getTeam(String id) {
        return teams.get(id);
    }

    public MatchRecord getMatchRecord(String id) {
        for (MatchRecord record : matchRecords) {
            if (record.getId().equals(id)) {
                return record;
            }
        }
        return null;
    }

    public Collection<Person> getUsers() {
        return users.values();
    }

    public Collection<Player> getPlayers() {
        return players.values();
    }

    public Collection<Hero> getHeroes() {
        return heroes.values();
    }

    public Collection<Equipment> getEquipment() {
        return equipment.values();
    }

    public Collection<Team> getTeams() {
        return teams.values();
    }

    public List<MatchRecord> getMatchRecords() {
        return matchRecords;
    }

    private void requireUniqueUser(String id) {
        requireUnique(users, id, "User");
    }

    private void requireUnique(Map<String, ?> map, String id, String label) {
        if (map.containsKey(id)) {
            throw new IllegalArgumentException(label + " ID already exists: " + id);
        }
    }
}
