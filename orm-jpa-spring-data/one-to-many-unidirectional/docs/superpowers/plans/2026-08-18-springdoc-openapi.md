# Ajout de springdoc-openapi (Swagger UI) — Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Exposer une documentation OpenAPI 3 générée automatiquement (JSON + Swagger UI) pour l'API `/api/posts`, en zero-config minimal.

**Architecture:** Une dépendance Maven (`springdoc-openapi-starter-webmvc-ui`) + un unique bean `OpenAPI` de métadonnées dans `config/OpenApiConfig.java`. Springdoc scanne `PostController` et lit les contraintes Bean Validation déjà présentes sur les DTO pour générer le schéma — aucune annotation ajoutée dans le code métier.

**Tech Stack:** Spring Boot 4.1.0, Java 21, Maven, `org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.0`, JUnit 5 + AssertJ (déjà fournis par `spring-boot-starter-test`).

## Global Constraints

- Version de dépendance exacte : `org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.0` (seule version ciblant Spring Boot 4.1.0, la version du parent de ce projet).
- Zero-config minimal : aucune annotation `@Operation`/`@ApiResponse`/`@Schema` sur `PostController`, `PostRequest`, `PostResponse`, `CommentRequest`, `CommentResponse`.
- Pas de `@ControllerAdvice`/gestion 404 — hors scope, ne pas y toucher.
- Toute config transverse vit dans `com.example.blog.config` (convention déjà établie par `JpaConfig`, `H2ConsoleConfig`).
- Aucun changement à `application.properties` — les chemins par défaut (`/v3/api-docs`, `/swagger-ui/index.html`) suffisent.
- Spec source : `docs/superpowers/specs/2026-08-18-springdoc-openapi-design.md`.

---

### Task 1: Ajouter la dépendance Maven springdoc-openapi

**Files:**
- Modify: `pom.xml`

**Interfaces:**
- Produces: artefact `org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.0` disponible sur le classpath pour les tâches suivantes.

- [ ] **Step 1: Ajouter la propriété de version**

Dans `pom.xml`, dans le bloc `<properties>` existant (juste après `<lombok.version>`), ajouter :

```xml
<springdoc.version>3.1.0</springdoc.version>
```

Le bloc `<properties>` doit ressembler à :

```xml
<properties>
    <java.version>21</java.version>
    <org.mapstruct.version>1.5.5.Final</org.mapstruct.version>
    <lombok.version>1.18.32</lombok.version>
    <springdoc.version>3.1.0</springdoc.version>
</properties>
```

- [ ] **Step 2: Ajouter la dépendance**

Dans `pom.xml`, dans `<dependencies>`, juste après le bloc `spring-boot-starter-validation`, ajouter :

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>${springdoc.version}</version>
</dependency>
```

- [ ] **Step 3: Vérifier la résolution de la dépendance**

Run: `./mvnw.cmd -q dependency:tree -Dincludes=org.springdoc:springdoc-openapi-starter-webmvc-ui`
Expected: la sortie liste `org.springdoc:springdoc-openapi-starter-webmvc-ui:jar:3.1.0:compile` (pas d'erreur de résolution).

- [ ] **Step 4: Vérifier que le module compile toujours**

Run: `./mvnw.cmd -q clean compile`
Expected: aucune sortie (pas d'erreur).

- [ ] **Step 5: Commit**

```bash
git add pom.xml
git commit -m "Add springdoc-openapi dependency"
```

---

### Task 2: Créer OpenApiConfig avec les métadonnées OpenAPI (TDD)

**Files:**
- Create: `src/main/java/com/example/blog/config/OpenApiConfig.java`
- Test: `src/test/java/com/example/blog/config/OpenApiConfigTest.java`

**Interfaces:**
- Consumes: rien (nouveau bean autonome).
- Produces: bean Spring `OpenAPI customOpenAPI()` (type `io.swagger.v3.oas.models.OpenAPI`), titre `"Blog API"`, version `"0.0.1-SNAPSHOT"`, disponible pour Task 3 (l'endpoint `/v3/api-docs` l'utilisera).

- [ ] **Step 1: Écrire le test qui échoue**

Créer `src/test/java/com/example/blog/config/OpenApiConfigTest.java` :

```java
package com.example.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenApiConfigTest {

    @Autowired
    private OpenAPI openAPI;

    @Test
    void exposesBlogApiMetadata() {
        assertThat(openAPI.getInfo()).isNotNull();
        assertThat(openAPI.getInfo().getTitle()).isEqualTo("Blog API");
        assertThat(openAPI.getInfo().getVersion()).isEqualTo("0.0.1-SNAPSHOT");
        assertThat(openAPI.getInfo().getDescription()).contains("OneToMany");
    }
}
```

- [ ] **Step 2: Lancer le test pour vérifier qu'il échoue**

Run: `./mvnw.cmd -q test -Dtest=OpenApiConfigTest`
Expected: FAIL — soit `NoSuchBeanDefinitionException: No qualifying bean of type 'io.swagger.v3.oas.models.OpenAPI'` (aucun bean `OpenAPI` n'est déclaré tant que `OpenApiConfig` n'existe pas), soit une erreur de démarrage de contexte équivalente.

- [ ] **Step 3: Implémenter OpenApiConfig**

Créer `src/main/java/com/example/blog/config/OpenApiConfig.java` :

```java
package com.example.blog.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blog API")
                        .description("Démo Spring Data JPA : mapping @OneToMany unidirectionnel "
                                + "avec @JoinColumn entre Post et Comment.")
                        .version("0.0.1-SNAPSHOT"));
    }
}
```

- [ ] **Step 4: Lancer le test pour vérifier qu'il passe**

Run: `./mvnw.cmd -q test -Dtest=OpenApiConfigTest`
Expected: PASS (aucune sortie avec `-q`, code de sortie 0).

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/example/blog/config/OpenApiConfig.java src/test/java/com/example/blog/config/OpenApiConfigTest.java
git commit -m "Add OpenApiConfig with Blog API metadata"
```

---

### Task 3: Verrouiller les endpoints OpenAPI/Swagger UI par un test d'intégration + documenter dans CLAUDE.md

**Files:**
- Test: `src/test/java/com/example/blog/config/OpenApiEndpointsIT.java`
- Modify: `CLAUDE.md`

**Interfaces:**
- Consumes: bean `OpenAPI` de Task 2, `PostController` (`/api/posts`, `/api/posts/{postId}/comments`) déjà existant.
- Produces: rien de nouveau côté code — cette tâche verrouille par un test le comportement déjà livré par les Tasks 1-2 (les endpoints `/v3/api-docs` et `/swagger-ui/index.html` sont déjà fonctionnels à ce stade grâce à l'auto-configuration springdoc).

- [ ] **Step 1: Écrire le test d'intégration**

Créer `src/test/java/com/example/blog/config/OpenApiEndpointsIT.java` :

```java
package com.example.blog.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpenApiEndpointsIT {

    @Value("${local.server.port}")
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void apiDocsExposesPostEndpoints() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/v3/api-docs", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("\"/api/posts\"");
        assertThat(response.getBody()).contains("\"/api/posts/{postId}/comments\"");
    }

    @Test
    void swaggerUiIsReachable() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/swagger-ui/index.html", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
```

- [ ] **Step 2: Lancer le test pour vérifier qu'il passe**

Run: `./mvnw.cmd -q test -Dtest=OpenApiEndpointsIT`
Expected: PASS. Les endpoints existent déjà grâce à l'auto-configuration springdoc (Task 1) et au bean de Task 2 — ce test ajoute une couverture de régression, il ne doit pas échouer ici. S'il échoue, vérifier que Task 1/2 sont bien commitées et que `port` est bien injecté (`local.server.port` doit être résolu par Spring Boot Test avec `RANDOM_PORT`).

- [ ] **Step 3: Mettre à jour CLAUDE.md**

Dans `CLAUDE.md`, section `## Commands`, juste après la ligne H2 console, ajouter :

```markdown
- Swagger UI (while app is running): `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON (while app is running): `http://localhost:8080/v3/api-docs`
```

- [ ] **Step 4: Lancer toute la suite de tests pour confirmer l'absence de régression**

Run: `./mvnw.cmd -q test`
Expected: aucune sortie avec `-q` (tous les tests passent : `BlogApplicationTests`, `OpenApiConfigTest`, `OpenApiEndpointsIT`).

- [ ] **Step 5: Commit**

```bash
git add src/test/java/com/example/blog/config/OpenApiEndpointsIT.java CLAUDE.md
git commit -m "Add OpenAPI endpoints regression test and document Swagger UI URLs"
```
