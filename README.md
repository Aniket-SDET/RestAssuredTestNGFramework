# Spotify Playlist API Automation Framework

This project is a Java-based API automation framework built with TestNG and RestAssured to validate Spotify playlist operations. It is designed to reflect real-world automation practices: layered architecture, reusable utilities, secure OAuth2 token handling, and clean separation between test logic and API behavior.

For recruiters and technical reviewers, this repository demonstrates that I can design and build maintainable automation frameworks rather than writing isolated test scripts.

## What concepts and skills are demonstrated?

- Layered architecture with clear responsibilities
- API test automation using RestAssured and TestNG
- OAuth2 token refresh and expiry handling
- Singleton-based configuration management
- Builder pattern for request payload construction
- POJO modeling for JSON payloads
- Enum-driven endpoint management
- Data-driven and environment-based configuration
- Reusable HTTP abstractions and request/response specs
- Allure reporting for test visibility
- Maven-based project structure with scalable test organization

## High-level architecture

```text
+--------------------------------------------------------------------------+
|                               Test Layer                                 |
|  com.spotify.oauth2.tests                                                 |
|  - PlaylistApiTests                                                      |
|  - Business assertions and API validation                                |
+-------------------------------------------+------------------------------+
                                           |
                                           v
+-------------------------------------------+------------------------------+
|                              API Layer                                  |
|  com.spotify.oauth2.api                                                   |
|  - SpecBuilder                                                            |
|  - PlaylistApi                                                            |
|  - Encapsulates endpoint calls and request/response setup                |
+-------------------------------------------+------------------------------+
                                           |
                                           v
+-------------------------------------------+------------------------------+
|                          Utility / Support Layer                        |
|  com.spotify.oauth2.utility                                               |
|  - RestHTTPMethods                                                        |
|  - ConfigLoader                                                           |
|  - DataLoader                                                             |
|  - PropertyLoader                                                         |
|  - RenewToken                                                             |
|  - FakerUtils                                                             |
+-------------------------------------------+------------------------------+
                                           |
                                           v
+-------------------------------------------+------------------------------+
|                               POJO Layer                                  |
|  com.spotify.oauth2.Pojo                                                  |
|  - playlistRoot                                                           |
|  - JSON mapping and response validation                                  |
+-------------------------------------------+------------------------------+
                                           |
                                           v
+-------------------------------------------+------------------------------+
|                         Configuration / Resources                       |
|  src/test/resources/config.properties                                    |
|  src/test/resources/data.properties                                      |
|  src/test/resources/allure.properties                                    |
+--------------------------------------------------------------------------+
```

## Why this architecture matters

This framework is structured to separate concerns and improve maintainability:

- Test layer focuses on business validation and assertions
- API layer defines endpoint behavior and reusable request specs
- Utility layer handles configuration, HTTP execution, and token logic
- POJO layer maps API payloads to Java objects for safer validations
- Configuration files keep environment values external and reusable

This is a strong example of a scalable API automation design, not just a set of tests.

## Core design concepts used

### 1. Layered architecture
The project is organized by responsibility rather than by test type or endpoint alone.

- `tests` contains test scenarios
- `api` contains endpoint wrappers and request building
- `utility` contains reusable technical logic
- `Pojo` contains POJO classes for request & response
- `enums` contains endpoint definitions

This supports maintainability, readability, and future growth.

### 2. Singleton pattern
Configuration classes are implemented as singletons so that the framework loads configuration once and reuses it across the suite.

Examples:
- `ConfigLoader.getInstance()`
- `DataLoader.getInstance()`

Benefits:
- avoids repeated file reads
- centralizes config access
- reduces memory and object churn

### 3. Builder pattern
The request object uses Lombok `@Builder`, making payload creation cleaner and safer.

```java
playlistRoot requestPlaylist = playlistRoot.builder()
    .name(name)
    .description(description)
    .isPublic(setPublic)
    .build();
```

This makes object creation more readable and reduces manual setter boilerplate.

### 4. POJO modeling
The framework uses Java model classes to represent JSON request and response payloads.

This enables:
- structured validation of response objects
- type-safe handling of API data
- simpler assertions compared with raw JSON strings

### 5. Enum-based endpoint management
Endpoints are centralized in enums instead of being repeated manually in tests or utility classes.

This avoids duplication and makes route updates easier when the API evolves.

### 6. Data-driven configuration
Environment-specific values such as credentials, API base information, and static resource data are separated into property files.

This improves:
- test portability across environments
- maintainability
- easier CI/CD integration

### 7. OAuth2 token management
A dedicated token refresh mechanism handles expired access tokens before protected API calls are made.

This is a crucial real-world automation skill because production APIs commonly enforce token expiry and refresh cycles.

### 8. Reusable HTTP specification
RestAssured request and response specs are centralized in `SpecBuilder` to define common behavior such as:

- base URI
- content type
- auth configuration
- response validation
- Allure request logging

This reduces repetition and keeps tests consistent.

### 9. Allure reporting
The framework integrates with Allure to provide richer test reporting, including request visibility and execution insights.

This demonstrates an understanding of how automation is used in real projects beyond just console output.

## Layer responsibilities

### Test layer
Package: `com.spotify.oauth2.tests`

This layer contains scenario-based tests such as:
- creating a playlist
- fetching playlists
- updating a playlist
- negative testing for invalid requests
- expired token validation

Responsibilities:
- construct valid payloads
- invoke API methods
- assert response status and content
- verify business behavior

### API layer
Packages:
- `com.spotify.oauth2.api`
- `com.spotify.oauth2.api.applicationApi`

This layer acts as a bridge between tests and HTTP execution.

Key components:
- `SpecBuilder` - central request/response config
- `PlaylistApi` - endpoint-specific methods for playlist operations

This keeps tests readable and allows the framework to hide raw request complexity.

### Utility layer
Package: `com.spotify.oauth2.utility`

This is where reusable technical logic exists:

- `RestHTTPMethods` - common HTTP calling patterns
- `ConfigLoader` - loads config values
- `DataLoader` - static test data access
- `PropertyLoader` - shared property loading utilities
- `RenewToken` - OAuth2 refresh logic
- `FakerUtils` - dynamic data generation

### POJO layer
Package: `com.spotify.oauth2.Pojo`

This layer models request and response bodies so API interactions are strongly typed and easier to validate.

Example structures include:
- playlist name and description
- identifiers and URIs
- follower metadata
- owner details
- nested response objects

## Request flow

A typical request in this framework follows this flow:

```text
Test method
    -> PlaylistApi method
        -> RestHTTPMethods
            -> SpecBuilder.getRequestSpec()
                -> OAuth2 token from RenewToken
                -> API call to Spotify
            -> Response extraction and validation
        -> Assertions in test class
```

This is a clean separation of concerns and is a common pattern in enterprise-grade API frameworks.

## Key files in the framework

- `src/test/java/com/spotify/oauth2/tests/PlaylistApiTests.java`
  - test scenarios for playlist API behavior
- `src/test/java/com/spotify/oauth2/api/SpecBuilder.java`
  - reusable RestAssured request/response specs
- `src/test/java/com/spotify/oauth2/api/applicationApi/PlaylistApi.java`
  - endpoint-specific API wrapper
- `src/test/java/com/spotify/oauth2/utility/RestHTTPMethods.java`
  - abstraction for HTTP calls
- `src/test/java/com/spotify/oauth2/utility/RenewToken.java`
  - OAuth2 token refresh logic
- `src/test/java/com/spotify/oauth2/utility/ConfigLoader.java`
  - central configuration access
- `src/test/java/com/spotify/oauth2/Pojo/playlistRoot.java`
  - main payload model
- `src/test/java/com/spotify/oauth2/enums/endPointEnums.java`
  - centralized endpoint definitions

## Tools and technologies

This project uses:

- Java 17
- Maven
- TestNG
- RestAssured
- Jackson
- Lombok
- Allure

## Execution

Run the suite with:

```bash
mvn test
```

## Summary for recruiters

This project showcases hands-on understanding of modern API test automation practices, including:

- scalable framework design
- reusable automation architecture
- OAuth2-aware API testing
- maintainable code organization
- strong validation and assertion strategies
- configuration-driven test execution

In short, this repository reflects not just test execution, but real engineering thinking behind a reusable API automation framework.
