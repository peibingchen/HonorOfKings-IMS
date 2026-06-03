import model.Equipment;
import model.Hero;
import model.MatchRecord;
import model.Person;
import model.Player;
import model.Role;
import model.Team;
import service.AdminManagementService;
import service.AuthenticationService;
import service.DataPersistenceService;
import service.DataValidationService;
import service.FileStorageService;
import service.GameDataManager;
import service.MatchHistoryService;
import service.PermissionService;
import service.RankingService;
import service.SearchService;
import util.DataInitializer;
import util.InputHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final GameDataManager dataManager = DataInitializer.createSampleData();
    private final AuthenticationService auth = new AuthenticationService(dataManager);
    private final SearchService search = new SearchService(dataManager);
    private final RankingService ranking = new RankingService(dataManager);
    private final FileStorageService storage = new FileStorageService(dataManager);
    private final PermissionService permission = new PermissionService();
    private final DataValidationService validation = new DataValidationService();
    private final AdminManagementService adminManagement =
            new AdminManagementService(dataManager, permission, validation);
    private final MatchHistoryService matchHistory = new MatchHistoryService(dataManager);
    private final DataPersistenceService persistence = new DataPersistenceService(dataManager);
    private final InputHelper input = new InputHelper(new Scanner(System.in));

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        System.out.println("Honor of Kings Information Management System");
        System.out.println("Default admin: A001 / admin123");
        System.out.println("Default player example: P001 / p001");
        while (true) {
            if (!auth.isLoggedIn()) {
                loginMenu();
            } else {
                mainMenu();
            }
        }
    }

    private void loginMenu() {
        System.out.println("\n1. Login");
        System.out.println("0. Exit");
        String choice = input.readLine("Choose: ");
        if ("0".equals(choice)) {
            System.out.println("Goodbye.");
            System.exit(0);
        }
        if ("1".equals(choice)) {
            String id = input.readLine("User ID: ");
            String password = input.readLine("Password: ");
            if (auth.login(id, password)) {
                System.out.println("Login successful. Welcome, " + auth.getCurrentUser().getName() + ".");
            } else {
                System.out.println("Login failed. Check ID and password.");
            }
        }
    }

    private void mainMenu() {
        Person user = auth.getCurrentUser();
        System.out.println("\nCurrent user: " + user.getName() + " (" + user.getRole() + ")");
        System.out.println("1. Player lookup");
        System.out.println("2. Team overview");
        System.out.println("3. Hero details");
        System.out.println("4. Equipment statistics");
        System.out.println("5. Match history");
        System.out.println("6. Leaderboard");
        System.out.println("7. My information");
        if (user.getRole() == Role.ADMIN) {
            System.out.println("8. Admin data management");
            System.out.println("9. Save data");
        }
        System.out.println("L. Logout");
        System.out.println("0. Exit");
        String choice = input.readLine("Choose: ").toUpperCase();
        switch (choice) {
            case "1" -> showPlayerLookup();
            case "2" -> showTeamOverview();
            case "3" -> showHeroDetails();
            case "4" -> showEquipmentStatistics();
            case "5" -> showMatchHistory();
            case "6" -> showLeaderboard();
            case "7" -> showMyInformation();
            case "8" -> runAdminManagement(user);
            case "9" -> saveDataSummary(user);
            case "L" -> auth.logout();
            case "0" -> System.exit(0);
            default -> System.out.println("Unknown option.");
        }
    }

    private void showPlayerLookup() {
        Player player = search.findPlayer(input.readLine("Player ID or name: "));
        if (player == null) {
            System.out.println("Player not found.");
            return;
        }
        System.out.println(player.getReport());
        System.out.println("Owned heroes and equipment:");
        for (Hero hero : player.getOwnedHeroes()) {
            System.out.println("  " + hero.getReport());
            for (Equipment item : hero.getCompatibleEquipment()) {
                System.out.println("    - " + item.getName());
            }
        }
    }

    private void showTeamOverview() {
        Team team = search.findTeam(input.readLine("Team ID or name: "));
        if (team == null) {
            System.out.println("Team not found.");
            return;
        }
        System.out.println(team.getReport());
        System.out.println("Members:");
        for (Player player : team.getMembers()) {
            System.out.println("  " + player.getReport());
        }
    }

    private void showHeroDetails() {
        Hero hero = search.findHero(input.readLine("Hero ID or name: "));
        if (hero == null) {
            System.out.println("Hero not found.");
            return;
        }
        System.out.println(hero.getReport());
        System.out.println("Compatible equipment:");
        for (Equipment item : hero.getCompatibleEquipment()) {
            System.out.println("  " + item.getReport());
        }
        System.out.println("Players owning this hero:");
        for (Player player : search.findPlayersOwningHero(hero)) {
            System.out.println("  " + player.getName());
        }
    }

    private void showEquipmentStatistics() {
        System.out.println("Equipment ranking formula: usageCount * 2 + rating + power / 100.");
        for (Equipment item : ranking.rankEquipmentByScore()) {
            System.out.println(item.getReport());
        }
    }

    private void showMatchHistory() {
        String type = input.readLine("Search by player or team? (P/T): ").toUpperCase();
        int limit = input.readInt("Last N matches (default 5): ", 5);
        if ("P".equals(type)) {
            Player player = search.findPlayer(input.readLine("Player ID or name: "));
            if (player == null) {
                System.out.println("Player not found.");
                return;
            }
            System.out.print(matchHistory.formatMatchHistoryForPlayer(player, limit));
        } else {
            Team team = search.findTeam(input.readLine("Team ID or name: "));
            if (team == null) {
                System.out.println("Team not found.");
                return;
            }
            System.out.println(matchHistory.getTeamWinLossSummary(team));
            printMatches(matchHistory.getRecentMatchesForTeam(team, limit));
        }
    }

    private void printMatches(List<MatchRecord> matches) {
        if (matches.isEmpty()) {
            System.out.println("No match records found.");
            return;
        }
        for (MatchRecord record : matches) {
            System.out.println(record.getReport());
            record.getHeroPicks().forEach((player, hero) ->
                    System.out.println("  " + player.getName() + " picked " + hero.getName()));
        }
    }

    private void showLeaderboard() {
        int limit = input.readInt("Top X players (default 5): ", 5);
        System.out.println("1. Win rate");
        System.out.println("2. Level");
        System.out.println("3. Number of matches");
        String metric = input.readLine("Rank by: ");
        System.out.println("Tie handling: selected metric, then win rate, level, and name.");
        List<Player> players = switch (metric) {
            case "2" -> ranking.topPlayersByLevel(limit);
            case "3" -> ranking.topPlayersByMatches(limit);
            default -> ranking.topPlayersByWinRate(limit);
        };
        for (Player player : players) {
            System.out.println(player.getReport());
        }
    }

    private void showMyInformation() {
        Person user = auth.getCurrentUser();
        if (user instanceof Player player) {
            System.out.println(player.getReport());
            String newName = input.readLine("Enter new name or press Enter to keep current: ");
            if (!newName.isEmpty()) {
                player.setName(newName);
                System.out.println("Name updated.");
            }
        } else {
            System.out.println("Admin account: " + user.getId() + " | " + user.getName());
        }
    }

    private void runAdminManagement(Person user) {
        if (!permission.isAdmin(user)) {
            System.out.println("Only admins can manage data.");
            return;
        }
        System.out.println("Admin management delete menu:");
        System.out.println("1. Delete player");
        System.out.println("2. Delete hero");
        System.out.println("3. Delete equipment");
        System.out.println("4. Delete team");
        System.out.println("5. Delete match record");
        String choice = input.readLine("Choose: ");
        String id = input.readLine("ID to delete: ");
        try {
            boolean deleted = switch (choice) {
                case "1" -> adminManagement.deletePlayer(user, id);
                case "2" -> adminManagement.deleteHero(user, id);
                case "3" -> adminManagement.deleteEquipment(user, id);
                case "4" -> adminManagement.deleteTeam(user, id);
                case "5" -> adminManagement.deleteMatchRecord(user, id);
                default -> false;
            };
            System.out.println(deleted ? "Deleted." : "No matching record found.");
        } catch (SecurityException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveDataSummary(Person user) {
        if (!permission.isAdmin(user)) {
            System.out.println("Only admins can save data.");
            return;
        }
        try {
            storage.saveSummary(Path.of("docs", "data-summary.txt"));
            persistence.saveAll(Path.of("data"));
            System.out.println("Saved to docs/data-summary.txt and data/*.csv");
        } catch (IOException ex) {
            System.out.println("Could not save data: " + ex.getMessage());
        }
    }
}
