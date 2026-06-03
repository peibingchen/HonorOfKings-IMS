# Honor of Kings IMS - Core Design Document

## 1. Design Overview

This document describes the core design of the Honor of Kings Information Management System. The design focuses on completing the required coursework features first. Extra-credit features such as GUI, combat simulation, and recommendation engine are not included in this version and will be considered only after the core requirements are stable.

The project is a Java console application with a layered structure:

- `model`: domain classes for users, players, heroes, equipment, teams, and match records.
- `service`: application logic for authentication, data management, searching, ranking, and file output.
- `util`: helper classes for sample data and console input.
- `Main`: the console entry point and menu controller.

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
    Role.java
    HeroType.java
    EquipmentType.java
    MatchResult.java
    Reportable.java
  service/
    GameDataManager.java
    AuthenticationService.java
    SearchService.java
    RankingService.java
    FileStorageService.java
  util/
    DataInitializer.java
    InputHelper.java
docs/
  plan.md
  design.md
  test-cases.md
ai/
  prompts.md
  agent-log.md
  reflection.md
UML.md
README.md
git-history.txt
```

## 3. UML

The standalone UML file is stored at `UML.md` in the project root. It documents the same core design described in this file.

## 4. Model Design

### Person

`Person` is an abstract superclass for system users. It stores ID, name, password, and role. It provides shared behavior for checking passwords and updating basic user information.

### Player

`Player` extends `Person`. It stores level, wins, losses, team, and owned heroes. It calculates total matches and win rate. Players can view their information, owned heroes, and match history.

### Admin

`Admin` extends `Person`. Admin users have permission to manage system data through the admin menu.

### Hero

`Hero` represents a playable hero. It stores hero ID, name, hero type, attack, defense, health, and compatible equipment.

### Equipment

`Equipment` represents an item that can be used by heroes. It stores equipment ID, name, type, power, rating, and usage count.

The equipment ranking formula is:

```text
score = usageCount * 2 + rating + power / 100
```

### Team

`Team` contains multiple players. It calculates average level, total matches, win rate, and top player. The top player is selected by win rate first, then level.

### MatchRecord

`MatchRecord` stores one match result, including team, opponent, date, result, and hero picks.

## 5. Interfaces and Enums

`Reportable` is used by classes that can produce a readable report:

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

`GameDataManager` owns the in-memory data collections. It stores users, players, admins, heroes, equipment, teams, and match records. It also handles adding records, deleting records, and duplicate ID checks.

### AuthenticationService

`AuthenticationService` handles login, logout, and current-user state. It stores the current user as `Person`, which demonstrates polymorphism because the logged-in user may be an `Admin` or a `Player`.

### SearchService

`SearchService` handles:

- player lookup;
- team lookup;
- hero lookup;
- finding players who own a hero;
- recent match history for a player;
- recent match history for a team;
- hero pick rate calculation.

### RankingService

`RankingService` handles:

- top players by win rate;
- top players by level;
- top players by number of matches;
- equipment ranking by score.

Leaderboard tie handling is based on the selected metric first, then win rate, level, and name where applicable.

### FileStorageService

`FileStorageService` saves a readable system data summary to a text file. This provides the required file I/O evidence for the core version.

## 7. Utility Design

### DataInitializer

`DataInitializer` creates the required starting dataset:

- 3 teams;
- 15 players;
- 15 heroes;
- 20 equipment items;
- 10 match records.

Each team has 5 players. Each player owns at least 3 heroes. Each hero has at least 2 compatible equipment items.

### InputHelper

`InputHelper` wraps `Scanner` and provides safer console input methods, including integer parsing with default values.

## 8. Access Control

The application uses role-based access control:

- Admin users can access data-management functions.
- Player users can view public information and edit limited personal information.
- Non-admin users are blocked from admin-only menu actions.

Default accounts are initialized in `DataInitializer`:

```text
Admin: A001 / admin123
Player example: P001 / p001
```

## 9. Core Functional Coverage

This design covers the required core functions:

- player lookup;
- team overview;
- hero details;
- equipment statistics;
- match history;
- leaderboard;
- admin/player authentication;
- role-based data management;
- player self-service information editing;
- file output.

## 10. Documentation and Evidence

The final core submission will include:

- complete Java source code;
- `docs/plan.md`;
- `docs/design.md`;
- `docs/test-cases.md`;
- root `UML.md`;
- `ai/prompts.md`;
- `ai/agent-log.md`;
- `ai/reflection.md`;
- `README.md`;
- `git-history.txt`.

## 11. Design Compliance Summary

- The required seven domain classes are included.
- Inheritance is shown through `Person`, `Player`, and `Admin`.
- Interface usage is shown through `Reportable`.
- Collections are used for players, heroes, equipment, teams, users, and match records.
- Enums are used for roles, hero types, equipment types, and match results.
- File I/O is represented by `FileStorageService`.
- Exception handling is used for validation, invalid input, duplicate IDs, and file output errors.
- The design keeps business logic outside one large `Main` class.
