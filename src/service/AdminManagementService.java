package service;

import model.Equipment;
import model.EquipmentType;
import model.Hero;
import model.HeroType;
import model.MatchRecord;
import model.MatchResult;
import model.Person;
import model.Player;
import model.Team;

import java.time.LocalDate;

public class AdminManagementService {
    private final GameDataManager dataManager;
    private final PermissionService permissionService;
    private final DataValidationService validationService;

    public AdminManagementService(GameDataManager dataManager, PermissionService permissionService,
                                  DataValidationService validationService) {
        this.dataManager = dataManager;
        this.permissionService = permissionService;
        this.validationService = validationService;
    }

    public void addPlayer(Person user, Player player, String teamId) {
        permissionService.requireAdmin(user);
        validationService.requireFound(player, "Player", "new player");
        dataManager.addPlayer(player);
        if (teamId != null && !teamId.trim().isEmpty()) {
            Team team = dataManager.getTeam(teamId);
            validationService.requireFound(team, "Team", teamId);
            team.addMember(player);
        }
    }

    public boolean editPlayer(Person user, String playerId, String name, int level) {
        permissionService.requireAdmin(user);
        Player player = dataManager.getPlayer(playerId);
        validationService.requireFound(player, "Player", playerId);
        player.setName(name);
        player.setLevel(level);
        return true;
    }

    public boolean deletePlayer(Person user, String playerId) {
        permissionService.requireAdmin(user);
        return dataManager.deletePlayer(playerId);
    }

    public void addHero(Person user, Hero hero) {
        permissionService.requireAdmin(user);
        validationService.requireFound(hero, "Hero", "new hero");
        dataManager.addHero(hero);
    }

    public boolean editHero(Person user, String heroId, String name, HeroType type, int attack, int defense, int health) {
        permissionService.requireAdmin(user);
        Hero hero = dataManager.getHero(heroId);
        validationService.requireFound(hero, "Hero", heroId);
        hero.setName(name);
        hero.setType(type);
        hero.setAttack(attack);
        hero.setDefense(defense);
        hero.setHealth(health);
        return true;
    }

    public boolean deleteHero(Person user, String heroId) {
        permissionService.requireAdmin(user);
        return dataManager.deleteHero(heroId);
    }

    public void addEquipment(Person user, Equipment equipment) {
        permissionService.requireAdmin(user);
        validationService.requireFound(equipment, "Equipment", "new equipment");
        dataManager.addEquipment(equipment);
    }

    public boolean editEquipment(Person user, String equipmentId, String name, EquipmentType type, int power, double rating) {
        permissionService.requireAdmin(user);
        Equipment equipment = dataManager.getEquipment(equipmentId);
        validationService.requireFound(equipment, "Equipment", equipmentId);
        equipment.setName(name);
        equipment.setType(type);
        equipment.setPower(power);
        equipment.setRating(rating);
        return true;
    }

    public boolean deleteEquipment(Person user, String equipmentId) {
        permissionService.requireAdmin(user);
        return dataManager.deleteEquipment(equipmentId);
    }

    public void addTeam(Person user, Team team) {
        permissionService.requireAdmin(user);
        validationService.requireFound(team, "Team", "new team");
        dataManager.addTeam(team);
    }

    public boolean editTeam(Person user, String teamId, String name) {
        permissionService.requireAdmin(user);
        Team team = dataManager.getTeam(teamId);
        validationService.requireFound(team, "Team", teamId);
        team.setName(name);
        return true;
    }

    public boolean deleteTeam(Person user, String teamId) {
        permissionService.requireAdmin(user);
        return dataManager.deleteTeam(teamId);
    }

    public void addMatchRecord(Person user, String id, String teamId, String opponent, LocalDate date, MatchResult result) {
        permissionService.requireAdmin(user);
        Team team = dataManager.getTeam(teamId);
        validationService.requireFound(team, "Team", teamId);
        validationService.requireText(id, "Match ID");
        validationService.requireText(opponent, "Opponent");
        if (date == null || result == null) {
            throw new IllegalArgumentException("Date and result are required.");
        }
        dataManager.addMatchRecord(new MatchRecord(id, team, opponent, date, result));
    }

    public boolean deleteMatchRecord(Person user, String matchId) {
        permissionService.requireAdmin(user);
        return dataManager.deleteMatchRecord(matchId);
    }
}
