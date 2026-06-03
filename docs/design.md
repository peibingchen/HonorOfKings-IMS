# Honor of Kings IMS - Design Draft

## 1. Design Overview

This document describes the draft design of the Honor of Kings Information Management System. The system is implemented as a Java console application with a simple layered structure:

- `model`: domain classes such as players, heroes, equipment, teams, and match records.
- `service`: business logic such as authentication, searching, ranking, storage, and data management.
- `util`: helper classes for sample data and console input.
- `Main`: the console entry point and menu controller.

The design follows the coursework requirement that the project should demonstrate object-oriented programming rather than placing all logic in one large `Main` class.

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
```

## 3. Main Class Responsibility

`Main` is responsible for:

- starting the application;
- showing the login menu;
- showing the main menu after login;
- calling service classes based on user choices;
- printing formatted results to the console.

`Main` does not store the main data directly. Data is stored in `GameDataManager`, while search and ranking logic is placed in separate service classes.

## 4. Model Classes

### Person

`Person` is an abstract superclass for system users. It contains shared user fields:

- `id`
- `name`
- `password`
- `role`

It also provides common behavior such as checking a password and updating a valid name.

### Player

`Player` extends `Person` and represents a game player. It contains:

- level;
- wins and losses;
- team reference;
- owned heroes.

It calculates total matches and win rate from wins and losses.

### Admin

`Admin` extends `Person` and represents a user with data-management permission. Admin-specific behavior is currently handled through role checks in the menu and service layer.

### Hero

`Hero` represents a playable hero. It contains:

- hero ID and name;
- hero type;
- attack, defense, and health;
- compatible equipment.

Each hero can have multiple compatible equipment items.

### Equipment

`Equipment` represents an item that can be used by heroes. It contains:

- equipment ID and name;
- equipment type;
- power;
- rating;
- usage count.

The equipment ranking score is calculated as:

```text
usageCount * 2 + rating + power / 100
```

This formula gives more weight to equipment that is used by more heroes while still considering rating and power.

### Team

`Team` represents a group of players. It contains:

- team ID and name;
- a list of members.

It calculates:

- average level;
- total matches;
- team win rate;
- top player.

The top player is selected by win rate first, then level if win rates are tied.

### MatchRecord

`MatchRecord` represents one match. It contains:

- match ID;
- team;
- opponent;
- date;
- result;
- hero picks.

Hero picks are stored as a map from `Player` to `Hero`.

## 5. Interfaces and Enums

### Reportable

`Reportable` is a simple interface for classes that can provide a formatted text report:

```java
String getReport();
```

The interface is implemented by `Player`, `Hero`, `Equipment`, `Team`, and `MatchRecord`.

### Enums

The project uses enums to avoid unclear string values:

- `Role`: `ADMIN`, `PLAYER`
- `HeroType`: `TANK`, `WARRIOR`, `ASSASSIN`, `MAGE`, `MARKSMAN`, `SUPPORT`
- `EquipmentType`: `ATTACK`, `MAGIC`, `DEFENSE`, `MOVEMENT`, `SUPPORT`
- `MatchResult`: `WIN`, `LOSS`

## 6. Service Classes

### GameDataManager

`GameDataManager` is the central data container. It stores:

- users;
- players;
- admins;
- heroes;
- equipment;
- teams;
- match records.

It uses maps for ID-based lookup and a list for match records. It also checks duplicate IDs before adding data.

### AuthenticationService

`AuthenticationService` handles:

- login;
- logout;
- current logged-in user.

It uses `Person` as the current user type, which supports polymorphism because the logged-in user may be either an `Admin` or a `Player`.

### SearchService

`SearchService` handles:

- player search by ID or name;
- team search by ID or name;
- hero search by ID or name;
- finding players who own a hero;
- finding recent matches for a player;
- finding recent matches for a team.

### RankingService

`RankingService` handles:

- top players by win rate;
- top players by level;
- top players by number of matches;
- equipment ranking by custom score.

The current console menu mainly displays the win-rate leaderboard, but the service already contains other ranking methods for future menu extension.

### FileStorageService

`FileStorageService` currently saves a readable data summary to a text file. This is a draft persistence feature. A later version can extend it to save and load complete structured data.

## 7. Utility Classes

### DataInitializer

`DataInitializer` creates the sample dataset required by the coursework:

- 3 teams;
- 15 players;
- 15 heroes;
- 20 equipment items;
- 10 match records.

The data is currently hard-coded so that the program can run immediately without external files.

### InputHelper

`InputHelper` wraps `Scanner` and provides safer console input methods, including basic integer parsing with a default value.

## 8. Object Relationships

```text
Admin extends Person
Player extends Person

Team contains many Player objects
Player owns many Hero objects
Hero has many compatible Equipment objects
MatchRecord links Team, Player, and Hero objects
```

These relationships demonstrate inheritance, association, aggregation, and collections.

## 9. Access Control Design

The system uses role-based access control:

- Admin users can access the admin data-management menu.
- Player users can view public information and edit limited personal information.
- If a non-admin user tries to use admin-only features, the system displays a permission message.

Default accounts are created in `DataInitializer`:

```text
Admin:  A001 / admin123
Player: P001 / p001
```

## 10. Current Limitations

This is a draft-level design document, so it records both completed design and known gaps.

- Admin management currently supports basic deletion, but full add and edit operations still need to be completed.
- File I/O currently saves a summary file, but full save/load persistence is not complete yet.
- The console leaderboard currently focuses on win rate, although the service class supports other ranking methods.
- A visual UML image has not been created yet; this document currently provides a text-based UML description.
- Manual test documentation still needs to be completed in `docs/test-cases.md`.

## 11. Future Improvements

The next design improvements should be:

- expand admin data management to include add, edit, and delete for all major data types;
- add full structured file persistence;
- expose more leaderboard options in the console menu;
- add hero pick rate display in match history;
- create a visual UML diagram;
- write at least 10 manual test cases;
- complete AI evidence files and final reflection.
