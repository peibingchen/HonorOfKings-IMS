# Honor of Kings IMS - Project Plan

## 1. Project Goal

This project implements an AI-assisted Information Management System for Honor of Kings. The system is designed as a Java console application for two user roles: Admin and Player.

Admins can log in, view public information, and manage system data. Players can log in, view their own profile, edit limited personal information, and access public player, team, hero, match, and leaderboard information. The final system focuses on clear Java object-oriented design, responsible AI-assisted development, Git evidence, documentation, and testing evidence.

## 2. Requirement Analysis

The completed system addresses the coursework requirements through the following features:

- Player Lookup: The user can search for a player by ID or name. The system displays the player's ID, name, team, level, win rate, owned heroes, and each hero's compatible equipment.
- Team Overview: The user can search for a team by ID or name. The system displays the team name, all members, average level, total matches, team win rate, and top player.
- Hero Details: The user can search for a hero by ID or name. The system displays the hero name, hero type, base stats, compatible equipment, and players who own that hero.
- Equipment Statistics: The system ranks equipment by a documented custom score. The current formula is `usageCount * 2 + rating + power / 100`.
- Match History: The user can retrieve the last N matches for a player or team. The system displays opponent, date, result, and hero picks.
- Leaderboard: The system displays the top X players by win rate. Ties are handled by sorting by level and then by name.
- Data Management: Admin users can manage data through an admin menu. The current foundation supports deletion of players, heroes, equipment, and teams, and it is structured so add and edit operations can be extended cleanly.
- Authentication: The system includes login and logout with Admin and Player roles. Admin accounts have management permissions, while Player accounts have limited access.
- Initial Dataset: The project initializes with at least 3 teams, 15 players, 15 heroes, 20 equipment items, and 10 match records.
- File I/O: The system includes a file storage service that can write a data summary to the `docs` folder. This provides a foundation for fuller persistence.

## 3. Java Concepts Used

- Inheritance: `Player` and `Admin` extend the abstract superclass `Person`.
- Encapsulation: Fields are private, and access is controlled through constructors, getters, setters, and validation methods.
- Association: A `Player` owns multiple `Hero` objects. A `Hero` can use multiple `Equipment` objects.
- Aggregation: A `Team` contains multiple `Player` objects.
- Interface: The `Reportable` interface is used by major domain classes that can produce formatted report text.
- Polymorphism: Authentication stores the logged-in user as a `Person`, allowing both `Admin` and `Player` objects to be handled through a common superclass.
- Collections: The project uses `ArrayList`, `LinkedHashMap`, `List`, `Map`, and `Collection` to manage users, players, heroes, equipment, teams, and match records.
- Exception Handling: The code validates empty names, invalid passwords, duplicate IDs, invalid levels, invalid numeric input, and file saving errors.
- File I/O: `FileStorageService` uses `java.nio.file.Files` and `Path` to save a system data summary.
- Enums: The project uses `Role`, `HeroType`, `EquipmentType`, and `MatchResult` to make domain values clear and type-safe.

## 4. Class Design

- `Main`: Runs the console menu, controls user interaction, and connects services together.
- `Person`: Abstract superclass for all system users. It stores ID, name, password, and role.
- `Player`: Subclass of `Person`. It stores level, wins, losses, team, and owned heroes.
- `Admin`: Subclass of `Person`. It represents a user with data-management permission.
- `Hero`: Represents a playable hero, including hero type, attack, defense, health, and compatible equipment.
- `Equipment`: Represents an item with equipment type, power, rating, usage count, and ranking score.
- `Team`: Represents a team containing multiple players. It calculates average level, total matches, win rate, and top player.
- `MatchRecord`: Represents a match result, team, opponent, date, result, and hero picks.
- `GameDataManager`: Stores and manages users, players, admins, heroes, equipment, teams, and match records.
- `AuthenticationService`: Handles login, logout, and current-user state.
- `SearchService`: Provides player, team, hero, owner, and match-history search functions.
- `RankingService`: Provides player leaderboard sorting and equipment ranking.
- `FileStorageService`: Saves a readable summary of current system data.
- `DataInitializer`: Creates the initial dataset required by the coursework.
- `InputHelper`: Handles console input and basic invalid-number protection.

## 5. UML Draft

Text-based UML draft:

```text
Person <<abstract>>
  - id: String
  - name: String
  - password: String
  - role: Role
        ^
        |
   ----------------
   |              |
 Player          Admin
  - level
  - wins
  - losses
  - team: Team
  - ownedHeroes: List<Hero>

Team
  - members: List<Player>

Hero
  - type: HeroType
  - attack: int
  - defense: int
  - health: int
  - compatibleEquipment: List<Equipment>

Equipment
  - type: EquipmentType
  - power: int
  - rating: double
  - usageCount: int

MatchRecord
  - team: Team
  - opponent: String
  - date: LocalDate
  - result: MatchResult
  - heroPicks: Map<Player, Hero>

Reportable <<interface>>
  + getReport(): String

Player, Team, Hero, Equipment, and MatchRecord implement Reportable.
```

Relationship summary:

- `Player` and `Admin` inherit from `Person`.
- `Team` aggregates many `Player` objects.
- `Player` is associated with many `Hero` objects.
- `Hero` is associated with many `Equipment` objects.
- `MatchRecord` associates a `Team`, multiple `Player` objects, and their selected `Hero` objects.

## 6. Data Design

The initial dataset is created in `DataInitializer`.

- Teams: 3 teams, each with 5 players.
- Players: 15 players, each owning 3 heroes.
- Heroes: 15 heroes, each with at least 3 compatible equipment items.
- Equipment: 20 equipment items.
- Match Records: 10 match records.

Data is currently stored in memory through `GameDataManager`. The manager uses maps for ID-based lookup and lists for ordered records. This makes the system easy to search and extend.

The current persistence feature saves a readable data summary using `FileStorageService`. A full persistence version can extend this service to save and load structured CSV or text files for players, heroes, equipment, teams, and match records.

## 7. AI Usage Plan

AI assistance was planned and used as a controlled development aid, not as a replacement for human understanding. The project uses three required AI agent roles:

- Architect Agent: Used for reviewing class structure, OOP relationships, interface choices, and whether the design matches the coursework requirements.
- Implementation Agent: Used for selected implementation tasks such as service methods, menu logic, search logic, and ranking logic.
- Testing/Reviewer Agent: Used for identifying possible bugs, missing validation, weak menu behavior, and test-case ideas.

The human developer remains responsible for checking whether the code compiles, whether the behavior matches the requirements, and whether every class and method can be explained.

## 8. Prompt Strategy

The prompt strategy is specific and requirement-driven:

- Prompts include the coursework requirements or the relevant class context.
- Prompts ask for small, focused outputs instead of the entire project at once.
- AI suggestions are reviewed before being accepted.
- Suggestions that are too complex, unrelated, or difficult to explain are rejected or simplified.
- Important prompts are recorded in `ai/prompts.md`.
- Agent contributions and human decisions are recorded in `ai/agent-log.md`.
- Final lessons, limitations, and responsibility are recorded in `ai/reflection.md`.

This strategy supports responsible AI use because it keeps the development process transparent and verifiable.

## 9. Development Timeline

- Stage 1: Read the coursework PDF, create the project folders, and prepare the first plan.
- Stage 2: Ask the Architect Agent for OOP design advice and revise the class structure.
- Stage 3: Implement the required model classes: `Person`, `Player`, `Admin`, `Hero`, `Equipment`, `Team`, and `MatchRecord`.
- Stage 4: Add enums, the `Reportable` interface, and service classes.
- Stage 5: Create the initial dataset in `DataInitializer`.
- Stage 6: Implement the console menu, login/logout, player lookup, team overview, hero details, equipment statistics, match history, and leaderboard.
- Stage 7: Implement admin permissions and basic data-management functions.
- Stage 8: Add file output support for data summaries.
- Stage 9: Use the Testing/Reviewer Agent to inspect the code and identify missing features or bugs.
- Stage 10: Write manual test cases in `docs/test-cases.md`.
- Stage 11: Complete `README.md`, `docs/design.md`, AI evidence files, reflection, and Git history export.
- Stage 12: Perform final compilation, run manual tests, and prepare the submission.

## 10. Testing Plan

Testing is based on manual test cases and compilation checks. At least 10 test cases will be documented in `docs/test-cases.md`.

Planned test coverage:

- Valid admin login.
- Invalid login with wrong password.
- Valid player login.
- Player lookup by ID.
- Player lookup by name.
- Team overview by ID or name.
- Hero details by hero name.
- Equipment statistics ranking.
- Match history for a player.
- Match history for a team.
- Leaderboard top X players.
- Player editing own basic information.
- Admin deleting a record.
- Non-admin access restriction for management features.
- Saving data summary and handling file errors.

Each test case will include test ID, function tested, input, expected output, actual output, pass/fail result, and any bug found.

## 11. Risk Analysis

- Risk: The system may become too procedural if too much logic is placed in `Main`.
  Mitigation: Domain behavior is placed in model classes, and feature logic is separated into service classes.

- Risk: AI-generated code may be incorrect or too difficult to explain.
  Mitigation: AI outputs are reviewed, simplified, compiled, and tested before being accepted.

- Risk: Git evidence may be insufficient.
  Mitigation: Development is divided into meaningful commits using required commit prefixes such as `[Human]`, `[AI-Architect]`, `[AI-Implementation]`, `[AI-Review]`, `[Fix]`, `[Test]`, and `[Docs]`.

- Risk: File persistence may be incomplete.
  Mitigation: A basic file output service is implemented first, and full structured save/load can be added in a later iteration.

- Risk: Admin data management may not fully cover add, edit, and delete for every entity.
  Mitigation: `GameDataManager` centralizes data operations so additional add/edit menu actions can be added without changing the whole program.

- Risk: Manual test evidence may be too vague.
  Mitigation: Test cases will record input, expected output, actual output, result, and bug notes.

## 12. Final Reflection Placeholder

The final reflection will be completed in `ai/reflection.md` after implementation and testing. It will answer the required coursework questions:

1. Which AI tools or models were used.
2. Which prompt was the most useful and why.
3. Which AI suggestion was wrong, incomplete, or misleading.
4. How AI-generated code was checked.
5. Which bugs were fixed manually.
6. Which Java concept became clearer after using AI.
7. Which Java concept remains uncertain.
8. Whether AI made the project easier, harder, or both.
9. Which parts were mainly written by the student.
10. Which parts were mainly generated or heavily assisted by AI.
