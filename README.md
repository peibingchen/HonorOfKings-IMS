# Honor of Kings Information Management System

## Project Overview

This project is a Java Information Management System for Honor of Kings. It provides a console-based management system for the basic coursework requirements and includes extra features such as combat simulation, recommendation, enhanced CSV persistence, and a Swing GUI.

The system supports two user roles:

- Admin: can manage players, heroes, equipment, teams, match records, relationships, and saved data.
- Player: can log in, view public information, view personal information, and edit limited personal details.

## Environment

- Language: Java
- Recommended JDK: JDK 21
- External libraries: none
- Main class: `Main`

## How to Compile

From the project root:

```bash
javac -d out src/Main.java src/model/*.java src/service/*.java src/util/*.java src/gui/*.java
```

On Windows PowerShell, this command can also be written as:

```powershell
javac -d out src\Main.java src\model\*.java src\service\*.java src\util\*.java src\gui\*.java
```

## How to Run

Console or startup-selection mode:

```bash
java -cp out Main
```

The program then displays:

```text
Startup mode
1. Console mode
2. Swing GUI mode
0. Exit
```

Direct GUI mode:

```bash
java -cp out Main --gui
```

## Default Accounts

Admin account:

```text
User ID: A001
Password: admin123
```

Example player account:

```text
User ID: P001
Password: p001
```

## Main Features

Basic coursework features:

- Player lookup by ID or name.
- Team overview with members, average level, match count, win rate, and top player.
- Hero details with stats, compatible equipment, owners, and recommended equipment.
- Equipment statistics ranked by the documented score formula.
- Match history for players and teams.
- Leaderboards by win rate, level, or number of matches.
- Admin data management for players, heroes, equipment, teams, and match records.
- Match hero pick management.
- Player-hero and hero-equipment relationship management.
- Admin/player authentication and role-based permission control.
- CSV save and load.

Extra features:

- Combat simulation using hero stats, equipment bonus, random dodge, and critical hit events.
- Recommendation engine for equipment, heroes, and team composition.
- Enhanced persistence with backup folders and save/load reports.
- Swing GUI for login, player lookup, team overview, hero details, leaderboard, combat simulation, and recommendation.

## Sample Dataset

The program initializes sample data through `DataInitializer`.

The dataset includes:

- 3 teams.
- 15 players.
- 15 heroes.
- 20 equipment items.
- 10 match records.

Each team has 5 members. Players own multiple heroes, and heroes have compatible equipment.

## Project Structure

```text
src/
  Main.java
  model/
    Person.java
    Player.java
    Admin.java
    Hero.java
    Equipment.java
    Team.java
    MatchRecord.java
    BattleResult.java
    Recommendation.java
    enums and Reportable interface
  service/
    AuthenticationService.java
    GameDataManager.java
    SearchService.java
    RankingService.java
    MatchHistoryService.java
    AdminManagementService.java
    RelationshipManagementService.java
    DataPersistenceService.java
    DataLoadService.java
    CombatSimulator.java
    RecommendationEngine.java
  util/
    DataInitializer.java
    InputHelper.java
    AdminMenuOption.java
    AdminMenuPrinter.java
  gui/
    MainFrame.java
    LoginPanel.java
    PlayerLookupPanel.java
    TeamOverviewPanel.java
    HeroDetailsPanel.java
    LeaderboardPanel.java
    CombatSimulationPanel.java
    RecommendationPanel.java

docs/
  plan.md
  design.md
  test-cases.md
  data-summary.txt
  uml.png

ai/
  agent-log.md
```

## Persistence Files

The system saves structured CSV data in the `data/` folder:

```text
data/users.csv
data/players.csv
data/heroes.csv
data/equipment.csv
data/teams.csv
data/team-members.csv
data/player-heroes.csv
data/hero-equipment.csv
data/match-records.csv
data/match-picks.csv
```

When saving existing data, the system creates a timestamped backup under:

```text
data/backups/
```

## Testing

Manual test evidence is documented in:

```text
docs/test-cases.md
```

The test document covers compilation, login, lookup, team overview, hero details, equipment ranking, match history, leaderboard, admin CRUD, relationship management, save/load, combat simulation, recommendation, GUI integration, and final regression tests.

## AI Evidence

AI usage evidence is stored in the `ai/` folder. The project separates human-only work from AI-assisted work through commit prefixes and documentation.

Human-only commit prefixes include:

- `[Human]`
- `[Docs]`
- `[Test]`
- `[Fix]`

AI-related commit prefixes include:

- `[AI-Architect]`
- `[AI-Implementation]`
- `[AI-Review]`
- `[AI-Refactor]`

## Notes for IntelliJ IDEA

This is a plain Java project, not a Maven or Gradle project.

In IntelliJ IDEA:

1. Open the project root folder.
2. Set the Project SDK to JDK 21.
3. Mark `src` as Sources Root.
4. Mark `out` as Excluded if needed.
5. Run `Main.main()`.

To open the GUI directly in IntelliJ IDEA, add this program argument:

```text
--gui
```
