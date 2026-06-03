# Agent Log

This file records the AI agent roles used during the development of the Honor of Kings Information Management System. The purpose is to show how AI assistance was reviewed, modified, and controlled by human judgment.

## Architect Agent

Main contribution:

The Architect Agent helped review the coursework requirements and organize the initial object-oriented structure. It suggested using an abstract `Person` superclass, `Player` and `Admin` subclasses, required domain classes such as `Hero`, `Equipment`, `Team`, and `MatchRecord`, and service classes for authentication, searching, ranking, data management, and file output.

Human decision:

I accepted the required OOP structure because it matched the PDF requirements. I chose to defer extra-credit features, such as GUI, recommendation, and combat simulation, and prioritized the core domain design to ensure all basic requirements were robustly implemented first.

Related commits:

- `bdd60eb` `[AI-Architect] optimize project plan and system design blueprints based on architectural feedback`

## Implementation Agent

Main contribution:

The Implementation Agent helped implement selected Java code after the class structure was planned. The main areas were the core model classes, service-layer methods, match history output, leaderboard sorting, role checks, validation helpers, and CSV-style persistence output.

Human decision:

Instead of generating the entire project in bulk, I utilized AI for modular, focused implementation tasks. I then compiled and tested the results manually. I also adjusted the implementation to match the existing classes and removed claims or designs that did not exist in the current codebase.

Related commits:

- `9d59ed1` `[AI-Implementation] implement Player Admin Hero Equipment Team MatchRecord models`
- `3673990` `[AI-Implementation] implement core services for authentication search ranking management and persistence`

## Testing/Reviewer Agent

Main contribution:

The Testing/Reviewer Agent helped identify gaps between the program output and the coursework wording. The most important review finding was that the first match history output displayed match records and hero picks but did not explicitly show win/loss summary or hero pick rate.

Human decision:

I accepted this review finding and updated the match history service so player match history now includes a win/loss summary and hero pick rates. I then reran manual tests and updated `docs/test-cases.md` to show the new passing result.

Related commits:

- No dedicated `[AI-Review]` commit has been created yet. The review finding was implemented in `3673990` `[AI-Implementation] implement core services for authentication search ranking management and persistence`, and the later manual test evidence was recorded as human-only `[Test]` work.

## Human-Only Planning and Framework Work

Main contribution:

Some project work was intentionally done manually, especially setting up service framework files, writing documentation, creating test evidence, and deciding to focus on the core requirements before attempting extra-credit features.

Human decision:

I created framework classes first so later implementation could be organized around the coursework requirements. I also treated all commits starting with `[Human]`, `[Docs]`, and `[Test]` as human-only evidence. Only commits starting with `[AI-Architect]`, `[AI-Implementation]`, or `[AI-Review]` are counted as AI-assisted commits.

Related commits:

- `a8505f9` `[Docs] update the draft of plan.md`
- `8887a32` `[Docs] update the draft of design.md`
- `f7490ed` `[Human] create Person abstract base class with core attributes`
- `cef464c` `[Human] implement Reportable interface for standardized text output`
- `8cfeef1` `[Human] create the draft of UML class diagram using Mermaid`
- `3ee0f7a` `[Human] add service framework classes for core features`
- `67d0581` `[Test] update manual test cases and persistence evidence`

## Commit Classification Rule

AI-related commit prefixes:

- `[AI-Architect]`
- `[AI-Implementation]`
- `[AI-Review]`
- `[AI-Refactor]`

Human-only commit prefixes:

- `[Human]`
- `[Docs]`
- `[Test]`
- `[Fix]`, unless the commit message explicitly states that the fix was based on AI review

## Summary of Agent Roles

- Architect Agent: used for planning, class design, UML, and requirement mapping.
- Implementation Agent: used for selected model and service implementation.
- Testing/Reviewer Agent: used for bug finding, gap analysis, and test evidence improvement.

All AI-assisted output was reviewed and adjusted before being accepted into the project. Documentation, tests, and human-prefixed commits are counted as human-only work in this log.
