# Prompt Records

This file records the important AI prompts used during the Honor of Kings IMS project. The records follow the coursework format: time, tool/model, agent role, actual prompt, AI response summary, human decision, and related Git commit.

Some commits were supported by one focused prompt. More complex commits, such as core model design, admin management, data loading, persistence, and GUI integration, used two or three prompts because the work had separate design, implementation, and verification steps. Prompt numbers are record IDs; the `Time` field shows when each prompt was used.

## Prompt 01

Time: 2026-06-03 20:12  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `9d59ed1`

### My Prompt

Implement the model subclasses required by the Honor of Kings coursework. Use the existing plan and PDF requirements as the main source. Create Java classes for players, admins, heroes, equipment, teams, and match records. Keep the implementation focused on fields, constructors, getters, validation, relationships, and report output. Do not add unrelated extra-credit features yet.

### AI Response Summary

The AI helped implement the core model layer, including `Player`, `Admin`, `Hero`, `Equipment`, `Team`, and `MatchRecord`. It used the existing abstract `Person` class and the `Reportable` interface to keep the model classes consistent.

### My Decision

I accepted the model structure because it matched the required domain classes in the PDF. I reviewed the fields and methods to make sure they were explainable and focused on the basic coursework requirements.

## Prompt 02

Time: 2026-06-03 20:18  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `9d59ed1`

### My Prompt

Review the model classes after the first implementation pass. Check whether the fields and methods show encapsulation, inheritance, association, aggregation, and report output clearly enough for the coursework. Suggest small corrections only. Do not redesign the whole project.

### AI Response Summary

The AI suggested keeping fields private, exposing collection data safely, and using `getReport()` consistently through the `Reportable` interface.

### My Decision

I accepted the encapsulation suggestions because they made the model layer easier to explain. I did not add extra account/profile classes because they were unnecessary for the coursework requirements.

## Prompt 03

Time: 2026-06-03 20:43  
Tool/Model: Codex / GPT-5  
Agent Role: Architect Agent  
Related Commit: `bdd60eb`

### My Prompt

Review the current project plan and system design against the coursework PDF. Improve the plan so that it clearly maps the required features to Java classes, services, OOP concepts, data design, testing, and responsible AI usage. Keep the design realistic for a console-first Java project.

### AI Response Summary

The AI suggested a clearer layered architecture with `model`, `service`, and `util` packages. It also helped map required features to service classes such as authentication, search, ranking, match history, data management, and persistence.

### My Decision

I accepted the layered design because it reduced the risk of putting all logic into `Main`. I kept the console-first approach and deferred extra-credit features until the required functions were stable.

## Prompt 04

Time: 2026-06-03 20:50  
Tool/Model: Codex / GPT-5  
Agent Role: Architect Agent  
Related Commit: `bdd60eb`

### My Prompt

Help refine the UML and package responsibilities for the current Java project. The design must show inheritance from `Person`, association between players and heroes, aggregation between teams and players, an interface, service classes, and file I/O. Keep it aligned with the PDF and do not add features that are not planned yet.

### AI Response Summary

The AI suggested showing `Person` as an abstract superclass, `Reportable` as the interface, `Team` aggregating players, `Player` owning heroes, and service classes depending on `GameDataManager`.

### My Decision

I accepted the high-level UML relationships. I treated the UML as a design guide rather than generated source code and adjusted the documentation later as implementation changed.

## Prompt 05

Time: 2026-06-03 21:57  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `3673990`

### My Prompt

Using the existing model classes, implement the core services needed for authentication, searching, ranking, admin management, match history, validation, and basic file output. Keep the implementation modular and make sure the services support the menu functions required by the PDF.

### AI Response Summary

The AI helped implement service-layer classes such as `AuthenticationService`, `SearchService`, `RankingService`, `MatchHistoryService`, `AdminManagementService`, `DataValidationService`, `FileStorageService`, and `GameDataManager`.

### My Decision

I accepted the service separation and manually tested the main paths after implementation. I modified output and menu behavior where needed so the features matched the coursework wording.

## Prompt 06

Time: 2026-06-03 22:08  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `3673990`

### My Prompt

Implement leaderboard and equipment ranking logic using the current `Player` and `Equipment` classes. The leaderboard should support win rate, level, and match count. Explain or encode tie handling so it can be documented later.

### AI Response Summary

The AI helped implement ranking methods in `RankingService`. It sorted by the selected metric first and then applied tie-breakers such as win rate, level, and player name. It also used the equipment score formula already defined in `Equipment`.

### My Decision

I accepted the deterministic ordering because it made test results stable and explainable. I later documented the tie handling in the plan and test cases.

## Prompt 07

Time: 2026-06-04 01:49  
Tool/Model: Codex / GPT-5  
Agent Role: Testing/Reviewer Agent  
Related Commit: `d0d6a72`

### My Prompt

Read the agent log and review whether the wording is reasonable for coursework evidence. Make the language more professional if needed. Keep the distinction clear between human-only commits and AI-related commits.

### AI Response Summary

The AI suggested improving wording that sounded too negative or too passive. It recommended using terms such as "deferred" and "prioritized" instead of saying that features were simply rejected.

### My Decision

I accepted the wording improvement because it better explained the development decision. I also kept the rule that `[Human]`, `[Docs]`, `[Test]`, and `[Fix]` commits are human-only unless explicitly marked as AI-related.

## Prompt 08

Time: 2026-06-06 01:59  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `c7a64fc`

### My Prompt

Complete the admin add, edit, and delete menu functions for players, heroes, equipment, teams, and match records. Use the framework already created in the admin menu classes. Follow the existing service style and keep permission checks in the service layer.

### AI Response Summary

The AI helped connect admin menu options to `AdminManagementService` methods and implemented add, edit, and delete operations for the major system entities.

### My Decision

I accepted the implementation after checking that it followed the existing package structure. Later testing found that match record hero picks needed separate management, so that gap was fixed in later work.

## Prompt 09

Time: 2026-06-10 17:10  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `caad272`

### My Prompt

Implement the remaining data loading and relationship management functions. Add CSV loading into the existing `GameDataManager`, and add services for assigning or removing heroes from players and equipment from heroes. Keep the code compatible with the current save format.

### AI Response Summary

The AI helped implement `DataLoadService`, enhanced `DataPersistenceService`, added `RelationshipManagementService`, and connected the relevant admin menu options.

### My Decision

I accepted the approach because it completed the required file I/O and relationship management behavior. I manually tested save/load restoration and relationship assignment/removal.

## Prompt 10

Time: 2026-06-10 17:25  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `caad272`

### My Prompt

Add CSV loading in a way that can rebuild relationships after loading. Teams should regain members, players should regain owned heroes, heroes should regain equipment, and match records should regain hero picks. Keep the loading order safe.

### AI Response Summary

The AI suggested loading independent objects first, then relationship files. It recommended loading equipment, heroes, teams, and players before team members, player heroes, hero equipment, match records, and match picks.

### My Decision

I accepted the loading order because it avoided null references when restoring relationships. I manually tested by saving data, deleting a player, loading data, and confirming the player returned.

## Prompt 11

Time: 2026-06-11 20:05  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `824829b`

### My Prompt

Implement the extra-credit combat simulation feature using the existing `Hero`, `Equipment`, and `BattleResult` classes. The simulation should be turn-based, use hero stats, include equipment bonus, random critical hits and dodges, and output a combat report.

### AI Response Summary

The AI helped implement `CombatSimulator`, including the damage formula, critical-hit chance, dodge chance, round-by-round combat log, winner, loser, and remaining health.

### My Decision

I accepted the implementation because it matched the extra-credit requirement and was easy to explain. I tested it through the console menu with sample heroes such as `H001` and `H002`.

## Prompt 12

Time: 2026-06-11 20:56  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `9aea350`

### My Prompt

Implement the recommendation engine extra-credit feature. It should recommend equipment for a hero, heroes for a player, and team composition improvements. Use deterministic scoring rules so the output can be tested and explained in documentation.

### AI Response Summary

The AI helped implement `RecommendationEngine`, including equipment scoring based on compatibility and hero type, hero recommendations based on missing roles and stats, and team composition recommendations based on role coverage.

### My Decision

I accepted the deterministic formula because it is easier to test than random recommendations. I also connected recommendations to the console menu and hero details output.

## Prompt 13

Time: 2026-06-11 22:42  
Tool/Model: Codex / GPT-5  
Agent Role: Architect Agent  
Related Commit: `27d3359`

### My Prompt

Design an enhanced persistence workflow for the project. The current CSV save/load functions work, but I want the extra-credit version to include backup support, validation, and a readable report without breaking the existing save/load methods.

### AI Response Summary

The AI suggested adding a `PersistenceReport` class, backing up existing CSV files before saving, validating required files and minimum column counts before loading, and returning clear save/load messages.

### My Decision

I accepted the architecture because it improved persistence without changing the existing data model. I kept the old save/load methods available and added enhanced methods for the menu.

## Prompt 14

Time: 2026-06-12 00:42  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `c8cef19`

### My Prompt

Connect the Swing GUI panels to the existing service classes. The GUI should support login, player lookup, team overview, hero details, and leaderboard without duplicating business logic from the console application.

### AI Response Summary

The AI helped add Swing action listeners and connected `LoginPanel`, `PlayerLookupPanel`, `TeamOverviewPanel`, `HeroDetailsPanel`, and `LeaderboardPanel` to `AuthenticationService`, `SearchService`, and `RankingService`.

### My Decision

I accepted the service-based GUI connection because it reused tested logic instead of creating a second version of the same behavior. Later I extended the GUI with combat and recommendation panels.

## Prompt 15

Time: 2026-06-14 15:20  
Tool/Model: Codex / GPT-5  
Agent Role: Architect Agent  
Related Commit: `81b0c6c`

### My Prompt

Finalize the architecture documentation after all code features are implemented. Do not create a new UML.md file. Update the design and plan documents so they match the final code, including startup mode, GUI panels, combat simulation, recommendation engine, and enhanced persistence.

### AI Response Summary

The AI helped revise `docs/design.md` into a final design document and updated `docs/plan.md` so planned features were described as implemented features. It also aligned class lists and service responsibilities with the final code.

### My Decision

I accepted the documentation alignment because it removed outdated core-only wording and made the documents match the submitted code. I intentionally did not add `UML.md` because the current step was limited to design and plan alignment.

## Prompt 16

Time: 2026-06-06 02:15  
Tool/Model: Codex / GPT-5  
Agent Role: Testing/Reviewer Agent  
Related Commit: `c7a64fc`

### My Prompt

Review the admin management implementation against the PDF. Admin can add, edit, and delete match records, but check whether match records also need participants or hero picks to be manageable from the menu. Do not rewrite the whole admin service; identify gaps only.

### AI Response Summary

The AI pointed out that match records in the coursework include participants and hero picks, so adding a match record without a way to add hero picks would be incomplete.

### My Decision

I accepted the finding as a real gap. I recorded it as a failed manual test first, then later fixed it by adding match hero pick add/update and clear options.

## Prompt 17

Time: 2026-06-11 20:16  
Tool/Model: Codex / GPT-5  
Agent Role: Testing/Reviewer Agent  
Related Commit: `824829b`

### My Prompt

Review the combat simulation logic for edge cases. Check whether null heroes, very low damage, endless battles, random critical hits, and dodge events are handled safely. Suggest small fixes only.

### AI Response Summary

The AI recommended validating both heroes, ensuring minimum damage is at least 1, adding a maximum round guard, and logging critical-hit and dodge events clearly.

### My Decision

I accepted these safeguards because they made the simulation more reliable. I verified combat simulation through the console menu and kept the formulas simple enough to document.

## Prompt 18

Time: 2026-06-11 21:05  
Tool/Model: Codex / GPT-5  
Agent Role: Testing/Reviewer Agent  
Related Commit: `9aea350`

### My Prompt

Review the recommendation output. It should be explainable to a marker and should not look random. Check whether equipment, hero, and team recommendations each have a clear reason and a stable scoring rule.

### AI Response Summary

The AI suggested adding reason text to every `Recommendation`, such as compatibility with a hero, missing player role coverage, or missing team role coverage. It also recommended deterministic scoring instead of random recommendation output.

### My Decision

I accepted the reason field and deterministic scoring. This made recommendation results easier to verify in manual tests and easier to explain in the design documentation.

## Prompt 19

Time: 2026-06-11 22:55  
Tool/Model: Codex / GPT-5  
Agent Role: Architect Agent  
Related Commit: `27d3359`

### My Prompt

Review the enhanced persistence design before testing. Check whether the backup and report workflow could accidentally break the original `saveAll` and `loadInto` methods used by earlier tests. Keep backward compatibility if possible.

### AI Response Summary

The AI suggested keeping the original methods and adding wrapper methods such as `saveAllWithBackup` and `loadIntoWithReport`. It also suggested making the report object separate from the data manager.

### My Decision

I accepted this design because it preserved backward compatibility. The console menu uses the enhanced methods, while the original persistence methods remain available.

## Prompt 20

Time: 2026-06-12 00:55  
Tool/Model: Codex / GPT-5  
Agent Role: Testing/Reviewer Agent  
Related Commit: `c8cef19`

### My Prompt

Review the Swing GUI connection. The GUI should not duplicate business logic from the console menus. Check whether the panels call existing services and whether the minimum GUI requirement is covered.

### AI Response Summary

The AI confirmed that player lookup, team overview, hero details, and leaderboard are the minimum GUI screens required. It recommended calling `SearchService`, `RankingService`, and `AuthenticationService` directly from the panels.

### My Decision

I accepted the approach and kept GUI panels thin. Later I added combat and recommendation panels so the GUI also covered extra-credit features.

## Prompt 21

Time: 2026-06-03 22:20  
Tool/Model: Codex / GPT-5  
Agent Role: Testing/Reviewer Agent  
Related Commit: `3673990`

### My Prompt

Review the core services for likely missing coursework outputs. Focus on player match history, hero details, leaderboard output, and permission checks. Point out gaps instead of rewriting the whole implementation.

### AI Response Summary

The AI noted that match history should clearly show win/loss summary and hero pick rate for player history. It also reminded me that non-admin users should be blocked from admin data management.

### My Decision

I accepted the review findings and updated the relevant service/menu behavior. I then tested the outputs manually and recorded the passing results in the test document.

## Prompt 22

Time: 2026-06-10 17:36  
Tool/Model: Codex / GPT-5  
Agent Role: Testing/Reviewer Agent  
Related Commit: `caad272`

### My Prompt

Review the CSV loading and relationship management implementation. Check for risky cases such as missing files, missing referenced IDs, duplicate relationship entries, and loading records in the wrong order.

### AI Response Summary

The AI recommended checking required files before loading, skipping optional relationship rows when referenced objects are missing, and keeping relationship methods idempotent where possible.

### My Decision

I accepted the safer loading checks and then verified the feature by saving data, deleting a player, loading data, and confirming the deleted player was restored.

## Prompt 23

Time: 2026-06-12 01:05  
Tool/Model: Codex / GPT-5  
Agent Role: Implementation Agent  
Related Commit: `c8cef19`

### My Prompt

After connecting the basic Swing panels, check whether the GUI code has a reusable pattern for repeated layout code. If there is duplication, suggest a small helper class without changing the service layer.

### AI Response Summary

The AI suggested adding a small `PanelFactory` helper for shared output areas, search controls, borders, and scroll panes. This reduced repeated Swing layout setup across the GUI panels.

### My Decision

I accepted the helper because it reduced duplication while keeping the GUI simple. I did not move business logic into the helper; the panels still call the service layer directly.
