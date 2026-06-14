# Agent Log

This file records the AI agent roles used during the Honor of Kings Information Management System project. The purpose is to show how AI assistance was controlled by human judgment, reviewed before acceptance, and connected to Git evidence.

## Architect Agent

Main contribution:

The Architect Agent helped map the coursework PDF to a Java OOP architecture. It supported requirement analysis, class responsibility planning, package separation, UML-style relationships, persistence workflow design, and final architecture/documentation alignment.

Specific contributions included:

- suggesting a layered structure with `model`, `service`, `util`, and later `gui`;
- organizing the required domain model around `Person`, `Player`, `Admin`, `Hero`, `Equipment`, `Team`, and `MatchRecord`;
- identifying where inheritance, interface usage, aggregation, association, collections, file I/O, and enums should appear;
- designing the enhanced persistence workflow with backups, validation, and `PersistenceReport`;
- reviewing the final documentation so `docs/design.md` and `docs/plan.md` matched the implemented code.

Human decision:

I accepted the layered architecture because it matched the coursework requirements and prevented the project from becoming one large `Main` class. I kept the console application as the stable core workflow, then added GUI, combat simulation, recommendation, and enhanced persistence after the basic functions were working. I also decided not to add unnecessary classes that did not support the required features.

Related commits:

- `bdd60ebf69a5a330658ed67187719898cac5bff7` `[AI-Architect] optimize project plan and system design blueprints based on architectural feedback`
- `27d3359887b6d6dd555262ea9e6a186aea5de1fa` `[AI-Architect] design enhanced persistence workflow`
- `81b0c6c67fc1216d6fdc5134927e8fc3baaecfac` `[AI-Architect] finalize architecture design and documentation alignment`

## Implementation Agent

Main contribution:

The Implementation Agent helped implement selected Java classes and methods after the project structure was planned. It was used for focused tasks rather than generating the entire project at once.

Specific contributions included:

- implementing model classes such as `Player`, `Admin`, `Hero`, `Equipment`, `Team`, and `MatchRecord`;
- implementing core services for authentication, search, ranking, match history, validation, admin management, and file output;
- implementing admin add/edit/delete menu behavior;
- implementing CSV loading and relationship management;
- implementing combat simulation and recommendation features;
- connecting Swing GUI panels to existing services.

Human decision:

I accepted implementation suggestions only after checking that they matched the existing class structure and coursework requirements. I compiled and ran the program after implementation work. I adjusted outputs, menu wording, and feature scope manually where needed. I did not ask AI to generate the entire project in one bulk response; I used AI for modular implementation tasks.

Related commits:

- `9d59ed181809edd7298917bddb59a80235c6531a` `[AI-Implementation] implement Player Admin Hero Equipment Team MatchRecord models`
- `36739903c4fe85fc749d0d6b21f25c89cd984a10` `[AI-Implementation] implement core services for authentication search ranking management and persistence`
- `c7a64fcf89a0fab970e8dd5961ac2382fd73714d` `[AI-Implementation] develop admin capabilities to add, edit, and delete system entities`
- `caad27292f0fdd7b2b05fbe20ba43e800fbadecf` `[AI-Implementation] implement data loading and relationship management features`
- `824829bbfb8972b6f683dc0e159d9fc60b146a06` `[AI-Implementation] implement combat simulation feature`
- `9aea3507ec7d11a160cbad53d8b6a7c65e9c5f61` `[AI-Implementation] implement recommendation engine`
- `c8cef194f9fd2c3ee9dc27d7169611c4cc7dc02c` `[AI-Implementation] connect Swing GUI panels to core services`

## Testing/Reviewer Agent

Main contribution:

The Testing/Reviewer Agent helped inspect the project for gaps, risks, and consistency problems. It was used to review code and documentation, not to fabricate test results.

Specific contributions included:

- identifying that match records needed hero pick management, which led to later manual testing and fixes;
- reviewing combat simulation edge cases such as null heroes, minimum damage, dodge, critical hits, and maximum rounds;
- reviewing recommendation output so recommendations had clear reasons and deterministic scoring;
- reviewing GUI integration so GUI panels reused service classes instead of duplicating business logic;
- identifying referential cleanup risks when deleting players, heroes, equipment, or teams;
- improving the wording of `agent-log.md` so AI assistance was described professionally.

Human decision:

I accepted review findings when they identified real requirement gaps or data consistency risks. I tested the behavior manually and recorded the results in `docs/test-cases.md`. Test commits remain human-only evidence. The review agent supported bug finding and code review, but I remained responsible for deciding which fixes to apply.

Related commits:

- `d0d6a72cdf2f9ed4f6d7fd6020e6a6cbf47be1db` `[AI-Review] refine agent log wording based on review feedback`
- `b55406c5443409f0450931f1a4ce3856170d7a3b` `[AI-Review] fix referential cleanup for deleted records`

Related human-only fix and test evidence:

- `0f26edc15596ab7d352cda060eae8b1d0ba68279` `[Test] add test case identifying missing hero pick logic in admin operations`
- `3f7bf305995dee2e02a81b234e1c4d9665b785ec` `[Fix] patch missing hero pick logic in admin match record operations`
- `84062acb27d1dc8f911b49b2136c47c1ec7abe67` `[Test] validate successful fix of hero pick logic in match records`
- `38bd790b80d52fde530b921c2e9f27e2d318d152` `[Test] add full code-level regression test after extra features`
- `8a881871c08c2cb0ae4d8decec0e94069b2c3df9` `[Test] add GUI startup mode fix test`

## Human-Only Planning, Framework, and Testing

Main contribution:

Some work was intentionally completed as human-only work, especially early planning, framework creation, manual tests, documentation drafts, and final verification.

Human decision:

I used human-only commits to show that the project was developed iteratively and that AI did not replace the development process. I also treated all `[Test]` commits as manual test evidence rather than AI testing.

Related commits:

- `a8505f9b05acb67da295bc569c24b5feac63f004` `[Docs] update the draft of plan.md`
- `8887a32a4111b429a6cba9fd6d4890f83fe49f5d` `[Docs] update the draft of design.md`
- `f7490ed92ce0f3eb07620c8b71240e5b5d247e3a` `[Human] create Person abstract base class with core attributes`
- `cef464ce23175ae3bf4032505f2a1fda2bbb912d` `[Human] implement Reportable interface for standardized text output`
- `8cfeef12a1011224ddfaa9bf8e053dc98440c57d` `[Human] create the draft of UML class diagram using Mermaid`
- `3ee0f7abd14479c98499c90447da950b5c616813` `[Human] add service framework classes for core features`
- `5a7d8a64887d58e1298c88eb01d4768fb08452df` `[Human] add admin menu framework for add edit delete actions`
- `7f09d2409aff614c1036a65feb9f6e63c46cee37` `[Human] add extra feature class framework`
- `5e748d9126496f6495560b7a6ac62467103918fe` `[Human] add Swing GUI framework`

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

- Architect Agent: used for requirement mapping, class design, UML-style design, module planning, and final architecture alignment.
- Implementation Agent: used for selected model, service, persistence, extra feature, and GUI implementation tasks.
- Testing/Reviewer Agent: used for bug finding, edge-case review, documentation wording review, and consistency checks.

All AI-assisted output was reviewed, modified when necessary, compiled, and tested before being accepted into the project. Documentation, tests, and human-prefixed commits are counted as human-only work in this log.
