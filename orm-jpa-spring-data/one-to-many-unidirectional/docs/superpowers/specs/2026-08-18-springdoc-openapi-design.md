# Ajout de springdoc-openapi (Swagger UI)

## Contexte

Le projet est une démo Spring Boot 4.1.0 / Java 21 illustrant un mapping JPA
`@OneToMany` unidirectionnel (`Post` → `Comment`), organisée en package-by-feature
(`post/entity`, `post/controller`, `post/service`, `post/repository`, `post/mapper`,
`post/dto`, `post/comment/...`) avec `config/` pour l'infrastructure transverse
(`JpaConfig`, `H2ConsoleConfig`). Il n'existe aujourd'hui aucune documentation d'API
générée : pour connaître les routes, payloads et contraintes de validation, il faut lire
le code.

L'objectif est d'ajouter `springdoc-openapi` pour générer automatiquement une
documentation OpenAPI 3 (JSON) et une interface Swagger UI, avec le minimum de code à
maintenir — le projet expose déjà des contraintes Bean Validation (`@NotBlank`, `@Size`)
sur les DTO, que springdoc doit pouvoir lire sans annotation supplémentaire.

## Décisions validées avec l'utilisateur

- **Profondeur de documentation : zero-config minimal.** Aucune annotation
  `@Operation`/`@ApiResponse`/`@Schema` ajoutée dans le code — springdoc infère tout
  (routes, types, contraintes de validation) depuis `PostController`, `PostRequest`,
  `PostResponse`, `CommentRequest`, `CommentResponse` existants.
- **Gestion du 404 manquant : hors scope.** `PostServiceImpl.findById`/
  `findByIdWithComments` lèvent `EntityNotFoundException`, non interceptée par un
  `@ControllerAdvice` (comportement déjà documenté dans `CLAUDE.md`, remonte en 500).
  On ne corrige pas ce point ici : la documentation générée reflétera fidèlement le
  comportement actuel (pas de réponse 404 documentée). Un futur `@ControllerAdvice`
  sera une tâche séparée.

## Version retenue

`org.springdoc:springdoc-openapi-starter-webmvc-ui:3.1.0` — vérifié via les releases
GitHub du projet springdoc (`v3.1.0`, "Upgrade Spring Boot to version 4.1.0") : c'est la
version qui cible précisément Spring Boot 4.1.0, la version du parent de ce projet
(`pom.xml` : `spring-boot-starter-parent` 4.1.0). Les branches `2.x` visent Spring Boot
3.x/javax et ne conviennent pas ; `3.0.x` vise Spring Boot 4.0.x, une minor derrière.

Sources : [springdoc.org](https://springdoc.org/) · [springdoc/springdoc-openapi releases](https://github.com/springdoc/springdoc-openapi/releases)

## Approches comparées

### 1. Gestion de version Maven

| Option | Choix |
|---|---|
| **Propriété explicite** `${springdoc.version}` + `<version>` sur la dépendance | **Retenu** — cohérent avec le style déjà en place dans `pom.xml` (`${org.mapstruct.version}`, `${lombok.version}`). |
| BOM `springdoc-openapi-bom` en `dependencyManagement` | Écarté — utile seulement avec plusieurs artefacts springdoc (webmvc + security + kotlin...). Un seul artefact ici → complexité inutile (YAGNI). |

### 2. Emplacement des métadonnées OpenAPI (titre/description/version)

| Option | Choix |
|---|---|
| **Nouvelle classe `OpenApiConfig`** dans `config/`, `@Bean OpenAPI customOpenAPI()` | **Retenu** — cohérent avec la convention déjà établie : toute config transverse (`JpaConfig`, `H2ConsoleConfig`) vit dans `config/`. |
| Annotation `@OpenAPIDefinition` sur `BlogApplication` | Écarté — plus "zero-config" au sens littéral, mais mélange la classe d'entrée Spring Boot avec de la métadonnée de documentation, et rompt la convention `config/`. |

## Design

### Dépendance (`pom.xml`)

Ajouter une propriété `springdoc.version` (valeur `3.1.0`) au bloc `<properties>`
existant, puis la dépendance dans `<dependencies>` :

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>${springdoc.version}</version>
</dependency>
```

### Configuration (`config/OpenApiConfig.java`)

Nouvelle classe `@Configuration`, un seul `@Bean OpenAPI customOpenAPI()` définissant :
- titre : `"Blog API"`
- description courte mentionnant le mapping unidirectionnel `@OneToMany`/`@JoinColumn`
  (cohérent avec le sujet pédagogique du repo — la doc généré doit se comprendre comme
  une démo, pas une API de prod)
- version : reprise de la version Maven du projet (`0.0.1-SNAPSHOT`, valeur statique —
  pas de dépendance à `spring-boot-actuator`/`BuildProperties`, absent du classpath et
  hors scope pour l'ajouter juste pour ça)

### `application.properties`

Aucun changement. Les chemins par défaut de springdoc suffisent :
- OpenAPI JSON : `/v3/api-docs`
- Swagger UI : `/swagger-ui/index.html` (redirection depuis `/swagger-ui.html`)

Pas de collision avec `/h2-console` (déjà configuré) ni avec `/api/posts`.

### Code métier

Aucune modification de `PostController`, `PostService(Impl)`, `PostRepository`,
`PostMapper`/`CommentMapper`, ni des DTO. Springdoc scanne `@RestController` et lit les
types + annotations Bean Validation déjà présentes pour générer le schéma (ex. `title`
apparaîtra comme `string`, requis, `maxLength: 200`, depuis `@NotBlank @Size(max = 200)`
sur `PostRequest.title`).

### `CLAUDE.md`

Ajouter, à côté de la ligne H2 console déjà présente dans la section Commands :
- `http://localhost:8080/swagger-ui/index.html` (Swagger UI)
- `http://localhost:8080/v3/api-docs` (OpenAPI JSON brut)

## Error handling

Aucun nouveau code métier n'est introduit — uniquement une dépendance déclarative et un
bean de configuration statique (pas de logique conditionnelle, pas d'accès I/O au
démarrage au-delà de ce que fait déjà l'auto-configuration Spring Boot). Le seul risque
identifié — incompatibilité de version avec Spring Boot 4.1.0 — est déjà écarté par la
vérification de version ci-dessus.

## Testing

- `./mvnw clean compile` — confirme que la dépendance résout et que le module compile.
- `./mvnw test` — `BlogApplicationTests.contextLoads()` couvre le risque qu'une
  mauvaise auto-configuration springdoc empêche le contexte Spring de démarrer.
- Vérification manuelle après `./mvnw spring-boot:run` :
  - `curl http://localhost:8080/v3/api-docs` → JSON OpenAPI valide, contient les routes
    `/api/posts` et `/api/posts/{id}/comments` et `/api/posts/{id}/comments/{commentId}`.
  - Ouvrir `http://localhost:8080/swagger-ui/index.html` → les 7 endpoints de
    `PostController` sont listés et exécutables ("Try it out").

## Hors scope (rappel)

- Pas de `@ControllerAdvice`/gestion 404 propre.
- Pas d'annotations `@Operation`/`@ApiResponse`/`@Schema` enrichies.
- Pas de groupement `GroupedOpenApi` (une seule feature `post` aujourd'hui — à
  reconsidérer si le projet grandit).
- Pas de schéma de sécurité OpenAPI (aucune authentification dans le projet à ce jour).
