# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Spring Boot (4.1.0, Java 21) demo of a **unidirectional `@OneToMany` + `@JoinColumn`** JPA mapping using Spring Data JPA and an in-memory H2 database. Maven artifact `com.example:blog`, base package `com.example.blog`.

## Commands

- Build: `./mvnw clean package` (`mvnw.cmd` on Windows PowerShell)
- Run: `./mvnw spring-boot:run`
- Test (all): `./mvnw test`
- Test (single class): `./mvnw test -Dtest=BlogApplicationTests`
- H2 console (while app is running): `http://localhost:8080/h2-console` — JDBC URL `jdbc:h2:mem:testdb`, user `sa`, password `test`

## Architecture

Packages are organized **by business feature first, technical role second**: the top level
is `post/` (there is only one feature in this demo), and *inside* it classes are still split
by technical role (`entity/`, `controller/`, `service/`, `repository/`, `mapper/`, `dto/`) —
a hybrid of package-by-feature and package-by-layer. Only cross-cutting infrastructure
(`config/`) stays split out at the top level, outside any feature.

- `BlogApplication` — `@SpringBootApplication` entry point (`com.example.blog`, root package). Component-scan covers `post` and `config` as sub-packages, no explicit `@ComponentScan` needed.
- `config/` — `JpaConfig` (`@EnableJpaAuditing`), `H2ConsoleConfig` (manually registers the H2 console servlet, gated by `spring.h2.console.enabled`, needed because Spring Boot 4 dropped the H2 console auto-configuration). Deliberately technical/transverse, not business — stays outside `post/`.
- `post/` — the `Post` feature, split by technical role:
  - `post/entity/Post.java`
  - `post/controller/PostController.java` — `/api/posts`, constructor injection via Lombok's `@RequiredArgsConstructor`.
  - `post/service/PostService.java` (interface) + `PostServiceImpl.java` — `@Service`, `@Transactional(readOnly = true)` at class level, `@Transactional` overridden per write method.
  - `post/repository/PostRepository.java` — `extends JpaRepository<Post, Long>`, overrides `findById` with `@EntityGraph(attributePaths = "comments")` to fetch comments in one query.
  - `post/mapper/PostMapper.java` — MapStruct, `componentModel = "spring"`, `uses = CommentMapper.class`.
  - `post/dto/PostRequest.java`, `PostResponse.java`.
  - `post/comment/` — the `Comment` sub-feature, nested under `post` (not a sibling package) because there is no standalone `CommentController` or `CommentRepository` — comments are managed exclusively through `PostController`/`PostService`, matching the unidirectional mapping where `Post` is the sole aggregate root. Same technical split, minus what doesn't apply to it: `post/comment/entity/Comment.java`, `post/comment/mapper/CommentMapper.java`, `post/comment/dto/CommentRequest.java`/`CommentResponse.java`.

Requests carry Bean Validation annotations (`@NotBlank`, `@Size`); controller methods take `@Valid @RequestBody`.

### The unidirectional mapping

`Post` is the sole owner of the relationship; `Comment` has no back-reference and no `mappedBy`:

```java
// Post.java
@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
@JoinColumn(name = "post_id")
private Set<Comment> comments = new HashSet<>();
```

The FK column `post_id` physically lives on the `Comment` table (the "many" side), but ownership and cascading are driven entirely from `Post`. This is the point of the demo: contrast with a bidirectional mapping, which instead puts `mappedBy` on the `@OneToMany` side and a `@ManyToOne` back-reference on `Comment`. Fetch type is explicitly `LAZY` (matches the JPA default, made explicit).

Both entities use Lombok (`@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder`), `@EqualsAndHashCode(onlyExplicitlyIncluded = true)` keyed on `id` only, and `@EntityListeners(AuditingEntityListener.class)` + `@CreatedDate` for auditing (requires `JpaConfig`'s `@EnableJpaAuditing`). `Set<Comment> comments` needs `@Builder.Default` to keep the `new HashSet<>()` initializer when built via `@Builder`.

### Behavioral notes

- `PostServiceImpl.addComment()` maps the incoming `CommentRequest` to a `Comment` entity and adds it to `post.getComments()`, relying on `CascadeType.ALL` from `Post` to persist it — comments are never saved through a repository of their own.
- `PostServiceImpl.removeComment()` removes the comment from `post.getComments()` (triggering `orphanRemoval = true`) rather than deleting it directly.
- Not-found lookups (`findById`, `findByIdWithComments`) throw `jakarta.persistence.EntityNotFoundException` — there is no `@ExceptionHandler`/`@ControllerAdvice` yet, so this surfaces as an unhandled 500, not a 404.
- `PostMapper.toEntity`/`updateEntityFromDto` ignore `id`, `createdAt`, `updatedAt`, and `comments` — comments are only ever attached via `PostService.addComment`, never through `PostRequest`.

### Persistence

H2 in-memory (`jdbc:h2:mem:testdb`), schema recreated on each run — no migration tooling involved. Datasource and H2 console settings are in `src/main/resources/application.properties` (`spring.jpa.show-sql=true` with pretty-printing enabled for debugging).
