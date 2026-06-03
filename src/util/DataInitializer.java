package util;

import model.Admin;
import model.Equipment;
import model.EquipmentType;
import model.Hero;
import model.HeroType;
import model.MatchRecord;
import model.MatchResult;
import model.Player;
import model.Team;
import service.GameDataManager;

import java.time.LocalDate;

public class DataInitializer {
    private DataInitializer() {
    }

    public static GameDataManager createSampleData() {
        GameDataManager data = new GameDataManager();
        data.addAdmin(new Admin("A001", "Admin", "admin123"));

        Equipment[] items = createEquipment();
        for (Equipment item : items) {
            data.addEquipment(item);
        }

        Hero[] heroes = createHeroes(items);
        for (Hero hero : heroes) {
            data.addHero(hero);
        }

        Player[] players = createPlayers(heroes);
        for (Player player : players) {
            data.addPlayer(player);
        }

        Team[] teams = createTeams(players);
        for (Team team : teams) {
            data.addTeam(team);
        }

        createMatches(data, teams, players);
        return data;
    }

    private static Equipment[] createEquipment() {
        return new Equipment[] {
                new Equipment("E001", "Shadow Blade", EquipmentType.ATTACK, 180, 4.6),
                new Equipment("E002", "Storm Sword", EquipmentType.ATTACK, 200, 4.4),
                new Equipment("E003", "Doomsday", EquipmentType.ATTACK, 190, 4.5),
                new Equipment("E004", "Frost Staff", EquipmentType.MAGIC, 150, 4.3),
                new Equipment("E005", "Arcane Boots", EquipmentType.MOVEMENT, 80, 4.0),
                new Equipment("E006", "Sage Tome", EquipmentType.MAGIC, 210, 4.7),
                new Equipment("E007", "Guardian Armor", EquipmentType.DEFENSE, 220, 4.2),
                new Equipment("E008", "Revival Shield", EquipmentType.DEFENSE, 240, 4.6),
                new Equipment("E009", "Swift Boots", EquipmentType.MOVEMENT, 90, 4.1),
                new Equipment("E010", "Holy Grail", EquipmentType.SUPPORT, 130, 4.2),
                new Equipment("E011", "Blood Bow", EquipmentType.ATTACK, 175, 4.0),
                new Equipment("E012", "Dragon Spear", EquipmentType.ATTACK, 195, 4.5),
                new Equipment("E013", "Magic Orb", EquipmentType.MAGIC, 185, 4.4),
                new Equipment("E014", "Ice Guard", EquipmentType.DEFENSE, 205, 4.1),
                new Equipment("E015", "Fleet Boots", EquipmentType.MOVEMENT, 95, 4.2),
                new Equipment("E016", "Healing Stone", EquipmentType.SUPPORT, 120, 4.0),
                new Equipment("E017", "Phoenix Cape", EquipmentType.DEFENSE, 230, 4.3),
                new Equipment("E018", "Thunder Staff", EquipmentType.MAGIC, 215, 4.6),
                new Equipment("E019", "Piercing Dagger", EquipmentType.ATTACK, 160, 4.2),
                new Equipment("E020", "Team Banner", EquipmentType.SUPPORT, 140, 4.5)
        };
    }

    private static Hero[] createHeroes(Equipment[] e) {
        Hero[] heroes = new Hero[] {
                new Hero("H001", "Li Bai", HeroType.ASSASSIN, 92, 48, 3200),
                new Hero("H002", "Diao Chan", HeroType.MAGE, 84, 42, 3000),
                new Hero("H003", "Arthur", HeroType.TANK, 70, 82, 4200),
                new Hero("H004", "Hou Yi", HeroType.MARKSMAN, 88, 40, 2900),
                new Hero("H005", "Zhang Fei", HeroType.SUPPORT, 66, 86, 4400),
                new Hero("H006", "Luna", HeroType.ASSASSIN, 90, 46, 3100),
                new Hero("H007", "Angela", HeroType.MAGE, 86, 38, 2800),
                new Hero("H008", "Sun Shangxiang", HeroType.MARKSMAN, 91, 39, 2850),
                new Hero("H009", "Bai Qi", HeroType.TANK, 68, 88, 4550),
                new Hero("H010", "Yao", HeroType.SUPPORT, 58, 65, 3300),
                new Hero("H011", "Mulan", HeroType.WARRIOR, 87, 62, 3600),
                new Hero("H012", "Zhao Yun", HeroType.WARRIOR, 85, 66, 3700),
                new Hero("H013", "Wang Zhaojun", HeroType.MAGE, 83, 41, 2950),
                new Hero("H014", "Marco Polo", HeroType.MARKSMAN, 89, 37, 2820),
                new Hero("H015", "Lan", HeroType.ASSASSIN, 93, 45, 3050)
        };
        for (int i = 0; i < heroes.length; i++) {
            heroes[i].addCompatibleEquipment(e[i % e.length]);
            heroes[i].addCompatibleEquipment(e[(i + 5) % e.length]);
            heroes[i].addCompatibleEquipment(e[(i + 10) % e.length]);
        }
        return heroes;
    }

    private static Player[] createPlayers(Hero[] h) {
        Player[] players = new Player[] {
                new Player("P001", "Chen Wei", "p001", 38, 70, 30),
                new Player("P002", "Lin Hao", "p002", 35, 62, 34),
                new Player("P003", "Zhou Yu", "p003", 41, 81, 29),
                new Player("P004", "Wang Lei", "p004", 30, 45, 40),
                new Player("P005", "Liu Xin", "p005", 33, 56, 38),
                new Player("P006", "Xu Ran", "p006", 39, 75, 31),
                new Player("P007", "Sun Qiao", "p007", 28, 43, 42),
                new Player("P008", "He Ming", "p008", 36, 66, 32),
                new Player("P009", "Tang Yi", "p009", 32, 52, 36),
                new Player("P010", "Guo Chen", "p010", 37, 69, 33),
                new Player("P011", "Fan Yue", "p011", 31, 50, 39),
                new Player("P012", "Jiang Nan", "p012", 34, 60, 35),
                new Player("P013", "Song Kai", "p013", 29, 48, 41),
                new Player("P014", "Qin Mo", "p014", 40, 78, 30),
                new Player("P015", "Ye Xuan", "p015", 27, 44, 44)
        };
        for (int i = 0; i < players.length; i++) {
            players[i].addHero(h[i % h.length]);
            players[i].addHero(h[(i + 3) % h.length]);
            players[i].addHero(h[(i + 6) % h.length]);
        }
        return players;
    }

    private static Team[] createTeams(Player[] p) {
        Team red = new Team("T001", "Red Phoenix");
        Team blue = new Team("T002", "Blue Dragon");
        Team silver = new Team("T003", "Silver Moon");
        for (int i = 0; i < 5; i++) {
            red.addMember(p[i]);
            blue.addMember(p[i + 5]);
            silver.addMember(p[i + 10]);
        }
        return new Team[] { red, blue, silver };
    }

    private static void createMatches(GameDataManager data, Team[] teams, Player[] players) {
        for (int i = 0; i < 10; i++) {
            Team team = teams[i % teams.length];
            MatchRecord record = new MatchRecord(
                    "M" + String.format("%03d", i + 1),
                    team,
                    "Opponent " + (i + 1),
                    LocalDate.now().minusDays(i + 1L),
                    i % 2 == 0 ? MatchResult.WIN : MatchResult.LOSS);
            for (Player player : team.getMembers()) {
                record.addHeroPick(player, player.getOwnedHeroes().get(i % player.getOwnedHeroes().size()));
            }
            data.addMatchRecord(record);
        }
    }
}
