# AGENTS.md

## Purpose

This repository is a Java 21 + Spring Boot 4 web application for managing catering event providers.
Use this file as the project-specific operating guide for autonomous changes, bug fixes, and PR work.

## Stack Snapshot

- Java 21
- Spring Boot 4.0.0
- Spring MVC + JSP/JSTL
- Spring Data JPA
- Flyway migrations
- MySQL for local development
- H2 for automated tests
- Maven wrapper (`mvnw`, `mvnw.cmd`)

## Repository Shape

- Backend source: `src/main/java/com/catering/app`
- Views: `src/main/webapp/WEB-INF/views`
- JSP tags: `src/main/webapp/WEB-INF/tags`
- Static assets: `src/main/resources/static`
- Migrations: `src/main/resources/db/migration`
- Test source: `src/test/java/com/catering/app`
- UI guidance: `docs/design-system.md`

## Architecture Conventions

- Keep a simple layered structure: `Controller -> Service -> Repository`.
- Controllers are thin and should mainly:
  - prepare view models
  - validate request objects
  - manage redirects and flash/model messages
  - read/write session state when needed
- Business rules belong in services.
- Persistence concerns belong in repositories.
- Request DTOs live under feature packages in `request/`.
- Domain objects live under `domain/`.

## Coding Style Observed In This Repo

- Constructor injection is the standard.
- Prefer explicit, readable code over compact abstractions.
- Use early returns for guard clauses.
- Variable names are descriptive and usually long enough to remove ambiguity.
- Avoid introducing unnecessary frameworks, helper layers, or generic base classes.
- Preserve package boundaries by feature: `account`, `eventprovider`, `image`, `dashboard`, `home`, `common`.

## Backend Engineering Expectations

- Apply solid software engineering fundamentals with emphasis on:
  - separation of responsibilities
  - clear naming
  - low cognitive load
  - maintainable code paths
- Keep controllers focused on HTTP concerns and delegate business rules to services.
- Keep services cohesive and centered on application rules, orchestration, and invariants.
- Keep repositories focused on persistence access only.
- Prefer small, intention-revealing methods over long mixed-responsibility flows.
- Avoid duplicating business rules across controller, service, mapper, and view layers.
- When introducing new backend code, optimize for clarity first and cleverness last.

## Validation And Error Handling

- Validation is driven by Spring + Jakarta Validation on request objects.
- Controller methods usually check `BindingResult` immediately after `@Valid`.
- User-facing validation failures return to the same JSP view with a message in the model.
- Domain lookup failures currently use `EntityNotFoundException`.
- Authentication and account flows use specific exceptions such as:
  - `InvalidCredentialsException`
  - `DuplicateEmailException`

## Session And Authorization Patterns

- Session key constants are centralized in `AccountSession`.
- Authenticated account id is stored in the HTTP session.
- Before editing event providers, verify ownership instead of trusting request ids.
- Preserve existing redirect behavior for unauthorized flows unless intentionally changing UX.

## Database And Migration Rules

- Schema changes should be added as new Flyway migrations under `src/main/resources/db/migration`.
- Do not edit old migrations unless the user explicitly asks for a history rewrite.
- Local dev targets MySQL via `src/main/resources/application.properties`.
- Tests target H2 with MySQL compatibility via `src/test/resources/application-test.properties`.

## Testing Preferences Mapped From The Codebase

- Unit tests:
  - JUnit 5
  - Mockito
  - AssertJ
- Integration tests:
  - `@SpringBootTest`
  - `@AutoConfigureMockMvc`
  - `@ActiveProfiles("test")`
  - `@Transactional`
- Tests are behavior-oriented and named with `should...`.
- Prefer adding or updating tests when changing business rules, validation, auth, or HTTP flows.
- For service tests, use mocks and assert side effects clearly.
- For controller/flow coverage, prefer MockMvc integration tests.

## Frontend And JSP Conventions

- Views are server-rendered JSP files under `WEB-INF/views`.
- Reusable form pieces live in tag files under `WEB-INF/tags`.
- Current form flows rely on Spring form binding with `modelAttribute`.
- Preserve existing URL structure such as `/events/create`, `/events/edit`, `/accounts/login`, `/dashboard`.
- Keep `pt-BR` as the document language for user-facing pages.
- In frontend code, prefer modular, class-based JavaScript files for maintainability and reuse.
- Even with plain HTML, CSS, JS, and JSP, structure behavior as reusable components with clear responsibilities.
- Separate concerns between markup, styling, and behavior whenever practical.
- New JavaScript should avoid becoming a large page script with mixed responsibilities; prefer focused classes or modules per feature.
- Reuse existing frontend utilities and patterns before creating one-off scripts.

## UI Design Rules Already Established

- Follow `docs/design-system.md` before introducing new visual patterns.
- The current UI direction is warm, editorial, and hospitality-oriented.
- Use the token system already defined in `src/main/resources/static/css/styles.css`.
- Main fonts:
  - `Cormorant Garamond` for display headings
  - `Instrument Sans` for body and interface text
- Prefer sectioned forms, strong hierarchy, soft elevated panels, and clear primary actions.
- Reuse the current visual language instead of adding generic bootstrap-like components.

## Copy And Localization Notes

- User-facing copy must be in PT-BR with correct accentuation.
- Prefer normalized, well-written PT-BR even if older parts of the codebase still contain unaccented text.
- Do not introduce hardcoded user-facing strings in Java classes or JSP files when they can be internationalized.
- All labels, helper texts, validation messages, button texts, titles, and other UI copy should be added to message bundles and rendered through Spring/JSP internationalization mechanisms.
- `src/main/resources/messages.properties` is the default source of truth for copy unless the project is later expanded to locale-specific bundles.
- When touching existing hardcoded UI text, prefer migrating it to internationalization keys instead of adding more inline text.
- When adding new copy, keep it clear, practical, and consistent with the product tone.

## Safe Change Strategy

- Before changing a feature, inspect:
  - controller
  - service
  - request DTOs
  - related JSP/tag files
  - existing tests
- When changing form fields, verify all of these stay aligned:
  - request object properties
  - JSP input names/binding
  - mapper logic
  - entity updates
  - integration tests
- When changing uploads, preserve multipart limits and current storage abstractions.

## Commands

- Run tests: `./mvnw test`
- Run app: `./mvnw spring-boot:run`

On Windows PowerShell:

- Run tests: `.\mvnw.cmd test`
- Run app: `.\mvnw.cmd spring-boot:run`

## Git And Commit Conventions

- Use Conventional Commits for commit messages.
- Prefer formats such as:
  - `feat: ...`
  - `fix: ...`
  - `refactor: ...`
  - `test: ...`
  - `docs: ...`
  - `style: ...`
  - `chore: ...`
- Keep commit messages short, specific, and scoped to the actual change.
- Do not mix unrelated changes in the same commit when it can be avoided.

## What Agents Should Optimize For

- Make focused, minimal changes that fit the current architecture.
- Preserve behavior unless the task explicitly changes requirements.
- Add regression coverage for bug fixes when practical.
- Keep the codebase approachable for a small Spring MVC application.
- Treat `docs/design-system.md` and existing CSS as the source of truth for UI consistency.
