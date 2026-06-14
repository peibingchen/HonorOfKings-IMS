# Honor of Kings IMS - Final Design Document

## 1. Design Overview

This document describes the final design of the Honor of Kings Information Management System. The project is a plain Java application with two startup modes:

- Console mode for the required coursework workflow.
- Swing GUI mode for the extra-credit GUI feature.

The system uses a layered structure:

- `model`: domain objects and enums.
- `service`: business logic, data management, persistence, recommendations, and combat simulation.
- `util`: input helpers, menu options, and sample data initialization.
- `gui`: Swing frames and panels that call existing service classes.
- `Main`: application entry point, startup-mode selection, and console menu controller.

The design keeps domain logic and service logic outside `Main` so the project is not a single procedural class.

## 2. Package Structure

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
    Role.java
    HeroType.java
    EquipmentType.java
    MatchResult.java
    Reportable.java
  service/
    GameDataManager.java
    AuthenticationService.java
    PermissionService.java
    SearchService.java
    RankingService.java
    MatchHistoryService.java
    AdminManagementService.java
    AdminMenuService.java
    RelationshipManagementService.java
    DataValidationService.java
    FileStorageService.java
    DataPersistenceService.java
    DataLoadService.java
    PersistenceReport.java
    CombatSimulator.java
    RecommendationEngine.java
  util/
    DataInitializer.java
    InputHelper.java
    AdminMenuOption.java
    AdminMenuPrinter.java
  gui/
    MainFrame.java
    PanelFactory.java
    LoginPanel.java
    PlayerLookupPanel.java
    TeamOverviewPanel.java
    HeroDetailsPanel.java
    LeaderboardPanel.java
    CombatSimulationPanel.java
    RecommendationPanel.java
```

## 3. Startup Design

`Main.main()` supports two ways to start the application.

Normal startup:

```bash
java -cp out Main
```

This displays a startup menu:

```text
1. Console mode
2. Swing GUI mode
0. Exit
```

Direct GUI startup:

```bash
java -cp out Main --gui
```

Console mode remains the primary stable mode for required functions. GUI mode is an extra-credit interface that reuses the same service layer.

## 4. Model Design

### Person

`Person` is an abstract superclass for users. It stores ID, name, password, and role. It provides shared password checking and basic user information behavior.

### Player

`Player` extends `Person`. It stores level, wins, losses, team, and owned heroes. It calculates total matches and win rate.

### Admin

`Admin` extends `Person`. Admin users can access management functions through role-based permission checks.

### Hero

`Hero` stores ID, name, hero type, attack, defense, health, and compatible equipment.

### Equipment

`Equipment` stores ID, name, type, power, rating, usage count, and ranking score.

The equipment score formula is:

```text
score = usageCount * 2 + rating + power / 100
```

### Team

`Team` contains players and calculates average level, total matches, win rate, and top player. Top player selection uses win rate first and level as a tie-breaker.

### MatchRecord

`MatchRecord` stores match ID, team, opponent, date, result, and hero picks as a map from player to hero.

### BattleResult

`BattleResult` stores combat simulation output, including winner, loser, round count, remaining health, and combat log entries.

### Recommendation

`Recommendation` stores recommendation target, type, score, and explanation.

## 5. Interfaces and Enums

`Reportable` provides a shared reporting contract:

```java
String getReport();
```

The project uses these enums:

- `Role`: `ADMIN`, `PLAYER`
- `HeroType`: `TANK`, `WARRIOR`, `ASSASSIN`, `MAGE`, `MARKSMAN`, `SUPPORT`
- `EquipmentType`: `ATTACK`, `MAGIC`, `DEFENSE`, `MOVEMENT`, `SUPPORT`
- `MatchResult`: `WIN`, `LOSS`

## 6. Service Design

### GameDataManager

`GameDataManager` owns the in-memory collections for users, admins, players, heroes, equipment, teams, and match records. It also provides add, delete, lookup, duplicate-checking, and clear operations.

### AuthenticationService

`AuthenticationService` handles login, logout, and current-user state. The current user is stored as `Person`, demonstrating polymorphism because it may be an `Admin` or a `Player`.

### PermissionService

`PermissionService` protects admin-only operations and keeps role checks outside menu code.

### SearchService

`SearchService` handles player, team, and hero search. It also finds hero owners and supports match-history related lookup.

### RankingService

`RankingService` handles player leaderboards and equipment ranking. Leaderboard ties are handled by selected metric first, then win rate, level, and name where applicable.

### MatchHistoryService

`MatchHistoryService` returns recent player/team matches, win/loss summaries, and hero pick rates.

### AdminManagementService and AdminMenuService

`AdminManagementService` performs CRUD operations for players, heroes, equipment, teams, and match records. It also manages match hero picks. `AdminMenuService` reads admin menu choices and routes them to service methods.

### RelationshipManagementService

`RelationshipManagementService` manages player-hero and hero-equipment relationships.

### Persistence Services

`FileStorageService` writes a readable summary to `docs/data-summary.txt`.

`DataPersistenceService` saves structured CSV files and creates timestamped backups before overwriting existing data.

`DataLoadService` loads CSV files into `GameDataManager` and validates required files and minimum column counts.

`PersistenceReport` records save/load messages, warnings, backup directory, and success state.

### CombatSimulator

`CombatSimulator` implements extra-credit combat simulation. It uses hero stats, compatible equipment power, random critical hits, random dodge events, and a maximum round guard.

Damage is based on:

```text
damage = max(1, attackerAttack + equipmentBonus - defenderDefense * 0.4)
```

### RecommendationEngine

`RecommendationEngine` implements extra-credit recommendations:

- Equipment recommendations use equipment score, hero type compatibility, current compatible-equipment links, and usage bonus.
- Hero recommendations use missing role coverage and hero stats.
- Team composition recommendations identify missing team role coverage.

## 7. GUI Design

The GUI uses Swing and calls existing services. It does not duplicate business logic.

GUI classes:

- `MainFrame`: top-level window and tab container.
- `PanelFactory`: shared layout helper.
- `LoginPanel`: login and logout.
- `PlayerLookupPanel`: player lookup and owned hero display.
- `TeamOverviewPanel`: team report and member list.
- `HeroDetailsPanel`: hero details, equipment, owners, and recommendations.
- `LeaderboardPanel`: leaderboard by win rate, level, or match count.
- `CombatSimulationPanel`: extra-credit combat simulation.
- `RecommendationPanel`: extra-credit equipment, hero, and team recommendations.

The GUI can be opened from startup mode, from the console menu, or by running with `--gui`.

## 8. Data Design

`DataInitializer` creates the required dataset:

- 3 teams.
- 15 players.
- 15 heroes.
- 20 equipment items.
- 10 match records.

Persistence files:

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
docs/data-summary.txt
```

Backups are stored under:

```text
data/backups/
```

## 9. Functional Coverage

The final implementation covers:

- player lookup;
- team overview;
- hero details;
- equipment statistics;
- match history;
- leaderboard;
- admin/player authentication;
- role-based data management;
- match hero pick management;
- player-hero and hero-equipment relationship management;
- player self-service;
- CSV save/load;
- enhanced persistence report and backup;
- combat simulation;
- recommendation engine;
- Swing GUI.

## 10. Design Compliance Summary

- Required domain classes are included.
- Inheritance is shown through `Person`, `Player`, and `Admin`.
- Interface usage is shown through `Reportable`.
- Collections are used throughout the data model and services.
- Enums are used for roles, hero types, equipment types, and match results.
- File I/O is implemented through text summary output and structured CSV persistence.
- Exception handling is used for validation, missing data, invalid input, duplicate IDs, date parsing, and file errors.
- Business logic is separated into service classes instead of being placed entirely in `Main`.
