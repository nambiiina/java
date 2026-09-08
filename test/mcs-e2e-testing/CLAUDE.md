# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository layout

This repo contains **two independent, unrelated Maven projects** — there is no parent/aggregator relationship between them despite the nesting:

- **`./` (root)** — `org.example:mcs-e2e-testing`, a bare-bones scaffold (`src/main/java/org/example/Main.java`, just prints "Hello world!"). No dependencies, no tests. Not the real application.
- **`./customer-service/`** — `org.example:customer-service`, the actual Spring Boot 3.4 / Java 21 microservice. This is where all real work happens.

Always `cd customer-service` (or pass `-f customer-service/pom.xml`) before running Maven commands — running from the repo root operates on the empty scaffold project instead.

## Common commands

Run all commands from inside `customer-service/`:

```bash
# Build
mvn compile

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=CustomerControllerTest

# Run a single test method
mvn test -Dtest=CustomerControllerTest#shouldFindAllCustomers

# Run the app locally (port 8084)
mvn spring-boot:run

# Package
mvn package
```

Some tests (`CustomerRepositoryWithContainerTest`, `TestCustomerServiceApplication`) use Testcontainers and spin up a real PostgreSQL container via Docker — Docker must be running for those to pass.

## Architecture

`customer-service` is a single Spring Boot microservice following a classic layered structure under `org.example.customerservice`:

```
controller  → REST endpoints (@RestController), thin — delegates straight to the service layer
service     → CustomerService interface + CustomerServiceImpl (@Service, @Transactional) holding business rules
mapper      → CustomerMapper (@Service), wraps ModelMapper for Entity <-> DTO conversion
repository  → CustomerRepository extends JpaRepository, with derived query methods
entities    → JPA entity (Customer) — Bean Validation annotations live here (@NotEmpty, @Size)
dto         → CustomerDTO — plain Lombok data carrier used at the controller boundary
exceptions  → CustomerNotFoundException (@ResponseStatus 404), EmailAlreadyExistException,
              plus ConstraintViolationExceptionHandler (@ControllerAdvice) that turns
              ConstraintViolationException into a 400 with a field→[messages] map
```

Request flow: `CustomerController` → `CustomerService`/`CustomerServiceImpl` → `CustomerMapper` (DTO↔entity) + `CustomerRepository` (persistence). Business exceptions (`CustomerNotFoundException`, `EmailAlreadyExistException`) are thrown from the service layer and mapped to HTTP statuses via `@ResponseStatus` / the `@ControllerAdvice` handler.

### Spring Cloud integration

The service depends on `spring-cloud-starter-config` and `spring-cloud-starter-netflix-eureka-client`, but both are disabled locally via `application.properties` (`spring.cloud.discovery.enabled=false`, `spring.cloud.config.enabled=false`) — this service is meant to run as part of a larger microservice system (config server + Eureka) but works standalone for local dev/tests.

### Local datastore

- `application.properties` points at an in-memory H2 database by default for `spring-boot:run`, but overrides the driver to `org.testcontainers.jdbc.ContainerDatabaseDriver` and enables `spring.docker.compose.enabled=true` with `spring.docker.compose.file=../compose.yaml` (compose.yaml lives at the repo root, one level above the module, since Spring Boot's Docker Compose support only looks in the app's working directory by default) — this starts a plain Postgres container (port 5432, db `customers-db`) and wires up a service-connection DataSource to it automatically.
- `spring.jpa.hibernate.ddl-auto=create` — the schema is recreated on every startup; there are no Flyway/Liquibase migrations.
- `CustomerServiceApplication` has a commented-out `CommandLineRunner` that seeds three demo customers — uncomment for manual/local testing.

## Testing conventions

Tests follow the standard Spring Boot testing pyramid, each isolating a different slice — match the existing style (`GIVEN`/`WHEN`/`THEN` comments, `@link` javadoc naming the method under test, `usingRecursiveComparison()` for entity/DTO assertions) when adding new tests:

- **`@WebMvcTest`** (`CustomerControllerTest`) — controller layer only, `CustomerService` mocked via `@MockitoBean`, driven through `MockMvc`.
- **`@DataJpaTest`** (`CustomerRepositoryTest`) — repository layer against the default in-memory test DB, auto-rollback per test.
- **`@DataJpaTest` + Testcontainers** (`CustomerRepositoryWithContainerTest`) — same repository tests but against a real Postgres container (`@Testcontainers`, `@Container`, `@ServiceConnection`), with `@AutoConfigureTestDatabase(replace = NONE)` to stop Spring from swapping in an embedded DB. Requires Docker.
- **`@SpringBootTest(webEnvironment = RANDOM_PORT)`** (`CustomerIntegrationTest`) — full end-to-end HTTP test via `TestRestTemplate` against a running application context; `@Transactional`/`@Rollback` keep mutating tests from polluting later ones.
- Plain unit tests (`CustomerServiceImplTest`, `CustomerMapperTest`) — no Spring context, mock collaborators directly.
- `TestCustomerServiceApplication` is a `@TestConfiguration` main class that boots the app with a `PostgreSQLContainer` bean wired in via `@ServiceConnection`, for running the app locally against a disposable Postgres.
