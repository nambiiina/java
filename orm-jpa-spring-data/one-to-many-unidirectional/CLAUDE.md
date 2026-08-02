# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Spring Boot (3.2.1, Java 17) demo of a **unidirectional `@OneToMany` + `@JoinColumn`** JPA mapping using Spring Data JPA and an in-memory H2 database. Maven artifact `com.example:test`.

## Commands

- Build: `./mvnw clean package` (`mvnw.cmd` on Windows PowerShell)
- Run: `./mvnw spring-boot:run`
- Test (all): `./mvnw test`
- Test (single class): `./mvnw test -Dtest=TestApplicationTests`
- H2 console (while app is running): `http://localhost:8080/h2-console` — JDBC URL `jdbc:h2:mem:testdb`, user `sa`, password `test`

## Architecture

Layering is `Controller → Repository → Entity` — **there is no service layer**. `JpaRepository`s (`PostRepository`, `CommentRepository`) are injected directly into `@RestController`s via Lombok's `@AllArgsConstructor`.

- `TestApplication` — `@SpringBootApplication` entry point.
- `controller/` — `PostController` (`/posts`), `CommentController` (`/comments`).
- `model/` — `Post`, `Comment` entities; `model/dto/PostDto` (currently unused — controllers bind directly to entities).
- `repository/` — plain `JpaRepository` interfaces, no custom queries.

### The unidirectional mapping

`Post` is the sole owner of the relationship; `Comment` has no back-reference and no `mappedBy`:

```java
// Post.java
@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
@JoinColumn(name = "post_id")
private Set<Comment> comments = new HashSet<>();
```

The FK column `post_id` physically lives on the `Comment` table (the "many" side), but ownership and cascading are driven entirely from `Post`. This is the point of the demo: contrast with a bidirectional mapping, which instead puts `mappedBy` on the `@OneToMany` side and a `@ManyToOne` back-reference on `Comment`. Fetch type defaults to LAZY.

### Behavioral notes

- `CommentController` persists a new `Comment` by calling `commentRepository.save()` directly — it does **not** rely on cascading from `Post`, even though the mapping is configured for cascade-on-save.
- `PostController`'s single-post lookup (`GET /posts/{postId}`) calls `.get()` on the `Optional` unguarded — a missing id throws rather than returning a handled 404.

### Persistence

H2 in-memory (`jdbc:h2:mem:testdb`), schema recreated on each run — no migration tooling involved.
