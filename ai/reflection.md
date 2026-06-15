# AI Reflection

## 1. Which AI tools or models did you use?

I used Codex, GPT, and Gemini-based assistance during the project. I used the tools in different roles rather than as one general code generator:

- Architect Agent for requirement analysis, class design, UML-style thinking, package structure, and final design alignment.
- Implementation Agent for selected Java methods and classes after the framework was already planned.
- Testing/Reviewer Agent for code review, gap finding, edge-case review, and wording review.
- Gemini as an extra review/reference model for checking agent-log wording, final architecture documentation alignment, and whether the Swing GUI connection covered the required screens without duplicating service logic.

I did not treat AI output as automatically correct. I used it as a development assistant and then checked the code through compilation, console runs, and manual test cases.

## 2. Which prompt was the most useful? Why?

The most useful prompt was the enhanced persistence architecture prompt:

> Design an enhanced persistence workflow for the project. The current CSV save/load functions work, but I want the extra-credit version to include backup support, validation, and a readable report without breaking the existing save/load methods.

This prompt was useful because it did not ask AI to rewrite the whole project. It gave a specific design problem and a constraint: the old `saveAll` and `loadInto` methods should not be broken. The AI suggested a separate `PersistenceReport` class and wrapper methods such as `saveAllWithBackup` and `loadIntoWithReport`. This helped me improve persistence while keeping the original CSV workflow stable.

## 3. Which AI-generated suggestion was wrong, incomplete, or misleading?

One incomplete area was match record management. The initial admin implementation could add, edit, and delete match records, but it did not fully manage hero picks for newly created match records. This was incomplete because the coursework describes match records as including participants and hero picks.

I did not simply hide the issue. I recorded it as a failed test case first, then fixed it by adding menu options to add/update and clear match hero picks. This became an important example of why AI-assisted code still needs requirement-based testing.

Another issue was documentation wording. Some early wording sounded like extra-credit features were rejected. I changed that wording to say they were deferred or prioritized after the core features, which more accurately describes the development process.

## 4. How did you check whether AI-generated code was correct?

I checked AI-assisted code in several ways:

- I compiled the full project frequently with:

```powershell
javac -d out src\Main.java src\model\*.java src\service\*.java src\util\*.java src\gui\*.java
```

- I ran console tests for login, lookup, team overview, hero details, equipment ranking, match history, leaderboard, admin data management, save/load, combat simulation, recommendation, and GUI startup.
- I recorded manual test evidence in `docs/test-cases.md`.
- I checked whether outputs matched the PDF requirements, not just whether the code compiled.
- I used Git commits to separate human framework work, AI-assisted implementation, fixes, tests, and documentation.
- I reviewed whether generated methods fit the existing package structure and naming style.

## 5. What bugs did you fix yourself instead of asking AI to fix?

I fixed several issues through my own testing and decisions:

- I found and recorded the missing hero pick management issue in match records.
- I fixed the match record hero pick workflow by adding add/update and clear hero pick operations.
- I added a startup mode so the program can choose console mode or Swing GUI mode.
- I reviewed the final code against the requirement and completed missing GUI coverage for combat and recommendation.
- I kept test records as manual test evidence rather than describing them as AI tests.

I also made decisions about scope. For example, I kept the console interface as the stable required workflow and treated GUI, combat simulation, recommendation, and enhanced persistence as extra-credit features.

## 6. What Java concept did you understand better after using AI?

I understood service-layer separation better. At the beginning, it was tempting to put menu handling, search logic, ranking logic, file I/O, and validation into `Main`. The AI architecture suggestions helped me see why separating the project into `model`, `service`, `util`, and `gui` packages makes the code easier to test and explain.

I also understood polymorphism more clearly. `AuthenticationService` stores the current user as `Person`, but at runtime that object may be an `Admin` or a `Player`. This made the inheritance design more meaningful instead of just satisfying a checklist.

## 7. What Java concept are you still unsure about?

I am still less confident about larger-scale GUI design patterns. The Swing GUI works and calls the existing services, but I know that larger GUI applications often use patterns such as MVC or observer-style updates. For this coursework, the current GUI is enough, but I would need more practice to design a larger GUI cleanly.

I am also still cautious about persistence design. CSV loading works for this project, but a more advanced project might need stronger transaction handling, schema validation, or a database.

## 8. Did AI make the project easier, harder, or both? Explain.

AI made the project both easier and harder.

It made the project easier because it helped me break the coursework into smaller tasks, suggested class responsibilities, and helped implement focused methods. It was especially useful for repetitive service methods, CSV loading structure, and GUI event wiring.

It also made the project harder in some ways because I had to verify everything. Some AI suggestions were incomplete, and documentation could become inaccurate if I accepted wording too quickly. The main lesson is that AI can speed up development, but it also creates a review responsibility. I still needed to compile, test, compare the output with the PDF, and decide what to accept or reject.

## 9. Which parts of the final project were mainly written by you?

The parts mainly written by me were the planning, the framework, the testing process, the documentation decisions, and several important fixes. I did not start by asking AI to generate the whole project. I first read the coursework requirement, decided the project scope, and broke the work into smaller stages.

At the beginning, I created the basic structure myself. I wrote the abstract `Person` base class and the `Reportable` interface because I wanted the inheritance and interface parts to be simple enough for me to explain clearly. I also drafted the early UML idea and created the service framework classes before filling in all the methods. This helped me understand where each responsibility should go before implementation became complicated.

I also created several framework commits myself before asking for implementation help. For example, I prepared the admin menu framework before completing the add/edit/delete functions. I also prepared the data loading and relationship management framework before implementing the full CSV loading behavior. Later, I created the extra feature class framework and the Swing GUI framework first, so the project structure was decided before the detailed logic was added.

The testing work was mainly done by me. I ran the program repeatedly from the console, checked the actual output, and wrote the results in `docs/test-cases.md`. I kept the test document as manual test evidence. I tested login, player lookup, team overview, hero details, equipment ranking, match history, leaderboard, admin management, relationship management, save/load, combat simulation, recommendation, and GUI startup. I also kept the historical failed test for the missing match hero pick feature because it shows the debugging process honestly.

I also made several important decisions myself when the project did not fully match the requirement. One example is the match hero pick issue. I noticed that adding a match record was not enough if the admin could not manage hero picks. I recorded the problem as a failed test, then fixed it and tested the fix. Another example is the GUI startup problem. At first, the GUI was only available after login from the console menu. I decided to add a startup mode and a `--gui` argument so the GUI feature was easier to find and run.

The documentation was also mainly controlled by me. I wrote and updated the plan, design document, README, prompt records, agent log, and test cases to match the actual project state. I also decided on the commit classification rule: `[Human]`, `[Docs]`, `[Test]`, and normal `[Fix]` commits are human-only, while only `[AI-Architect]`, `[AI-Implementation]`, `[AI-Review]`, and `[AI-Refactor]` are counted as AI-related.

Overall, the human-written parts were the project direction, the file and package organization, the framework creation, the manual testing, the requirement checking, the debugging decisions, and the final documentation control. AI helped with selected implementation and review tasks, but I decided what to accept, what to change, and what to test.

## 10. Which parts were mainly generated or heavily assisted by AI?

The AI-assisted parts were mainly selected implementation and architecture tasks:

- model class implementation;
- service-layer implementation;
- admin management methods;
- CSV loading and relationship management;
- enhanced persistence design;
- combat simulation;
- recommendation engine;
- Swing GUI service connection;
- referential cleanup review;
- final design alignment.

These are recorded in commits with `[AI-Architect]`, `[AI-Implementation]`, and `[AI-Review]` prefixes. Even when AI assisted, I reviewed the code, compiled it, tested it, and modified the design or output where necessary.

## Advanced AI Reflection

### Topic

Codex Implementation Agent vs Gemini Testing/Reviewer Agent on Swing GUI integration.

Before the GUI comparison, Gemini was also used in Prompt 15 as an Architect Agent to check the final plan and design documents after the code features were implemented. That prompt helped align the documentation with the real project structure, including the startup mode, GUI panels, combat simulation, recommendation engine, and enhanced persistence.

I chose the GUI comparison as the main advanced reflection because Prompt 20 directly overlaps with the GUI implementation work. Codex/GPT helped connect the Swing panels to existing services in Prompt 14 and checked reuse of the existing layout helper in Prompt 23. Gemini reviewed the GUI connection in Prompt 20 and checked whether the minimum GUI requirement was covered.

### Codex Implementation Agent

The Codex Implementation Agent was useful for turning the GUI framework into working screens. It helped connect `LoginPanel`, `PlayerLookupPanel`, `TeamOverviewPanel`, `HeroDetailsPanel`, and `LeaderboardPanel` to the existing service classes. This was helpful because I did not want the GUI to become a separate version of the console program.

The main strength was concrete Java implementation. It suggested action listeners, service calls, and output formatting. Because the services already existed, the implementation prompt could stay focused on wiring panels to `AuthenticationService`, `SearchService`, and `RankingService`.

The limitation was that implementation help can make the screen work without automatically proving that it satisfies the coursework requirement. A GUI can open and still miss a required view, so I still needed a review step.

### Gemini Testing/Reviewer Agent

Gemini was more useful as a reviewer for this part. In Prompt 20, I asked it to check whether the Swing GUI connection duplicated business logic and whether the minimum GUI requirement was covered. It confirmed that player lookup, team overview, hero details, and leaderboard were the required minimum screens, and it recommended keeping the panels connected to existing services.

This was useful because it checked the GUI from the requirement side instead of only from the coding side. It helped me think about whether the GUI was enough for submission, not just whether the buttons worked.

### Comparison

Correctness:

- Codex helped make the GUI panels functional.
- Gemini helped check whether the GUI functions matched the required feature list.

Code structure:

- Codex followed the existing service-based structure when wiring panel actions.
- Gemini reinforced the decision not to duplicate business logic inside the GUI classes.

Requirement coverage:

- Codex was better for implementing the screens.
- Gemini was better for confirming that the screens covered player lookup, team overview, hero details, and leaderboard.

Learning value:

- Codex helped me understand how Swing event handlers can call service-layer methods.
- Gemini helped me understand that GUI extra credit still needs to be checked against the exact requirement, not just added because it looks useful.

### Final judgment

For GUI work, Codex/GPT was better for the implementation step, while Gemini was better for the review step. The strongest workflow was to use Codex to connect the panels, then use Gemini to check whether the GUI stayed aligned with the coursework requirement and reused the existing service layer properly.
