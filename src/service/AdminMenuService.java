package service;

import model.Equipment;
import model.EquipmentType;
import model.Hero;
import model.HeroType;
import model.MatchResult;
import model.Person;
import model.Player;
import model.Team;
import util.AdminMenuOption;
import util.InputHelper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AdminMenuService {
    private final AdminManagementService adminManagementService;
    private final RelationshipManagementService relationshipManagementService;

    public AdminMenuService(AdminManagementService adminManagementService,
                            RelationshipManagementService relationshipManagementService) {
        this.adminManagementService = adminManagementService;
        this.relationshipManagementService = relationshipManagementService;
    }

    public boolean handleOption(Person user, String choice, InputHelper input) {
        AdminMenuOption option = AdminMenuOption.fromCode(choice);
        if (option == null) {
            System.out.println("Unknown admin option.");
            return true;
        }
        if (option == AdminMenuOption.BACK) {
            return false;
        }
        try {
            switch (option) {
                case ADD_PLAYER -> addPlayer(user, input);
                case EDIT_PLAYER -> editPlayer(user, input);
                case DELETE_PLAYER -> deletePlayer(user, input);
                case ADD_HERO -> addHero(user, input);
                case EDIT_HERO -> editHero(user, input);
                case DELETE_HERO -> deleteHero(user, input);
                case ADD_EQUIPMENT -> addEquipment(user, input);
                case EDIT_EQUIPMENT -> editEquipment(user, input);
                case DELETE_EQUIPMENT -> deleteEquipment(user, input);
                case ADD_TEAM -> addTeam(user, input);
                case EDIT_TEAM -> editTeam(user, input);
                case DELETE_TEAM -> deleteTeam(user, input);
                case ADD_MATCH_RECORD -> addMatchRecord(user, input);
                case EDIT_MATCH_RECORD -> editMatchRecord(user, input);
                case DELETE_MATCH_RECORD -> deleteMatchRecord(user, input);
                case ADD_OR_UPDATE_MATCH_PICK -> addOrUpdateMatchPick(user, input);
                case CLEAR_MATCH_PICKS -> clearMatchPicks(user, input);
                case ASSIGN_HERO_TO_PLAYER -> assignHeroToPlayer(user, input);
                case REMOVE_HERO_FROM_PLAYER -> removeHeroFromPlayer(user, input);
                case ASSIGN_EQUIPMENT_TO_HERO -> assignEquipmentToHero(user, input);
                case REMOVE_EQUIPMENT_FROM_HERO -> removeEquipmentFromHero(user, input);
                case BACK -> {
                    return false;
                }
            }
        } catch (IllegalArgumentException | SecurityException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    private void addPlayer(Person user, InputHelper input) {
        String id = input.readLine("Player ID: ");
        String name = input.readLine("Name: ");
        String password = input.readLine("Password: ");
        int level = input.readInt("Level: ", 1);
        int wins = input.readInt("Wins: ", 0);
        int losses = input.readInt("Losses: ", 0);
        String teamId = input.readLine("Team ID (optional): ");
        adminManagementService.addPlayer(user, new Player(id, name, password, level, wins, losses), teamId);
        System.out.println("Player added.");
    }

    private void editPlayer(Person user, InputHelper input) {
        String id = input.readLine("Player ID: ");
        String name = input.readLine("New name: ");
        int level = input.readInt("New level: ", 1);
        printResult(adminManagementService.editPlayer(user, id, name, level), "Player updated.");
    }

    private void deletePlayer(Person user, InputHelper input) {
        String id = input.readLine("Player ID: ");
        printResult(adminManagementService.deletePlayer(user, id), "Player deleted.");
    }

    private void addHero(Person user, InputHelper input) {
        String id = input.readLine("Hero ID: ");
        String name = input.readLine("Name: ");
        HeroType type = readHeroType(input);
        int attack = input.readInt("Attack: ", 0);
        int defense = input.readInt("Defense: ", 0);
        int health = input.readInt("Health: ", 1);
        adminManagementService.addHero(user, new Hero(id, name, type, attack, defense, health));
        System.out.println("Hero added.");
    }

    private void editHero(Person user, InputHelper input) {
        String id = input.readLine("Hero ID: ");
        String name = input.readLine("New name: ");
        HeroType type = readHeroType(input);
        int attack = input.readInt("New attack: ", 0);
        int defense = input.readInt("New defense: ", 0);
        int health = input.readInt("New health: ", 1);
        printResult(adminManagementService.editHero(user, id, name, type, attack, defense, health),
                "Hero updated.");
    }

    private void deleteHero(Person user, InputHelper input) {
        String id = input.readLine("Hero ID: ");
        printResult(adminManagementService.deleteHero(user, id), "Hero deleted.");
    }

    private void addEquipment(Person user, InputHelper input) {
        String id = input.readLine("Equipment ID: ");
        String name = input.readLine("Name: ");
        EquipmentType type = readEquipmentType(input);
        int power = input.readInt("Power: ", 0);
        double rating = readDouble(input, "Rating (0-5): ", 0.0);
        adminManagementService.addEquipment(user, new Equipment(id, name, type, power, rating));
        System.out.println("Equipment added.");
    }

    private void editEquipment(Person user, InputHelper input) {
        String id = input.readLine("Equipment ID: ");
        String name = input.readLine("New name: ");
        EquipmentType type = readEquipmentType(input);
        int power = input.readInt("New power: ", 0);
        double rating = readDouble(input, "New rating (0-5): ", 0.0);
        printResult(adminManagementService.editEquipment(user, id, name, type, power, rating),
                "Equipment updated.");
    }

    private void deleteEquipment(Person user, InputHelper input) {
        String id = input.readLine("Equipment ID: ");
        printResult(adminManagementService.deleteEquipment(user, id), "Equipment deleted.");
    }

    private void addTeam(Person user, InputHelper input) {
        String id = input.readLine("Team ID: ");
        String name = input.readLine("Name: ");
        adminManagementService.addTeam(user, new Team(id, name));
        System.out.println("Team added.");
    }

    private void editTeam(Person user, InputHelper input) {
        String id = input.readLine("Team ID: ");
        String name = input.readLine("New name: ");
        printResult(adminManagementService.editTeam(user, id, name), "Team updated.");
    }

    private void deleteTeam(Person user, InputHelper input) {
        String id = input.readLine("Team ID: ");
        printResult(adminManagementService.deleteTeam(user, id), "Team deleted.");
    }

    private void addMatchRecord(Person user, InputHelper input) {
        String id = input.readLine("Match ID: ");
        String teamId = input.readLine("Team ID: ");
        String opponent = input.readLine("Opponent: ");
        LocalDate date = readDate(input);
        MatchResult result = readMatchResult(input);
        adminManagementService.addMatchRecord(user, id, teamId, opponent, date, result);
        System.out.println("Match record added.");
    }

    private void editMatchRecord(Person user, InputHelper input) {
        String id = input.readLine("Match ID: ");
        String teamId = input.readLine("New team ID: ");
        String opponent = input.readLine("New opponent: ");
        LocalDate date = readDate(input);
        MatchResult result = readMatchResult(input);
        printResult(adminManagementService.editMatchRecord(user, id, teamId, opponent, date, result),
                "Match record updated.");
    }

    private void deleteMatchRecord(Person user, InputHelper input) {
        String id = input.readLine("Match ID: ");
        printResult(adminManagementService.deleteMatchRecord(user, id), "Match record deleted.");
    }

    private void addOrUpdateMatchPick(Person user, InputHelper input) {
        String matchId = input.readLine("Match ID: ");
        String playerId = input.readLine("Player ID: ");
        String heroId = input.readLine("Hero ID: ");
        printResult(adminManagementService.addOrUpdateMatchHeroPick(user, matchId, playerId, heroId),
                "Match hero pick saved.");
    }

    private void clearMatchPicks(Person user, InputHelper input) {
        String matchId = input.readLine("Match ID: ");
        printResult(adminManagementService.clearMatchHeroPicks(user, matchId), "Match hero picks cleared.");
    }

    private void assignHeroToPlayer(Person user, InputHelper input) {
        String playerId = input.readLine("Player ID: ");
        String heroId = input.readLine("Hero ID: ");
        printResult(relationshipManagementService.assignHeroToPlayer(user, playerId, heroId),
                "Hero assigned to player.");
    }

    private void removeHeroFromPlayer(Person user, InputHelper input) {
        String playerId = input.readLine("Player ID: ");
        String heroId = input.readLine("Hero ID: ");
        printResult(relationshipManagementService.removeHeroFromPlayer(user, playerId, heroId),
                "Hero removed from player.");
    }

    private void assignEquipmentToHero(Person user, InputHelper input) {
        String heroId = input.readLine("Hero ID: ");
        String equipmentId = input.readLine("Equipment ID: ");
        printResult(relationshipManagementService.assignEquipmentToHero(user, heroId, equipmentId),
                "Equipment assigned to hero.");
    }

    private void removeEquipmentFromHero(Person user, InputHelper input) {
        String heroId = input.readLine("Hero ID: ");
        String equipmentId = input.readLine("Equipment ID: ");
        printResult(relationshipManagementService.removeEquipmentFromHero(user, heroId, equipmentId),
                "Equipment removed from hero.");
    }

    private HeroType readHeroType(InputHelper input) {
        System.out.println("Hero types: TANK, WARRIOR, ASSASSIN, MAGE, MARKSMAN, SUPPORT");
        return HeroType.valueOf(input.readLine("Hero type: ").toUpperCase());
    }

    private EquipmentType readEquipmentType(InputHelper input) {
        System.out.println("Equipment types: ATTACK, MAGIC, DEFENSE, MOVEMENT, SUPPORT");
        return EquipmentType.valueOf(input.readLine("Equipment type: ").toUpperCase());
    }

    private MatchResult readMatchResult(InputHelper input) {
        System.out.println("Match results: WIN, LOSS");
        return MatchResult.valueOf(input.readLine("Result: ").toUpperCase());
    }

    private LocalDate readDate(InputHelper input) {
        try {
            return LocalDate.parse(input.readLine("Date (YYYY-MM-DD): "));
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Date must use YYYY-MM-DD format.");
        }
    }

    private double readDouble(InputHelper input, String prompt, double defaultValue) {
        String value = input.readLine(prompt);
        if (value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number. Using default: " + defaultValue);
            return defaultValue;
        }
    }

    private void printResult(boolean success, String message) {
        System.out.println(success ? message : "No matching record found.");
    }
}
