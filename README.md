# Example App

> ### **Java 21 + Spring Boot 3**  (CRUD, auth, advanced patterns, etc) spec and API.

### [Demo]

---

## Table of Contents
* [Considerations](#considerations)
* [How it works](#how-it-works)
  * [Project structures](#project-structures)
     * [Modules](#modules)
     * [Classes](#classes)
* [Database architecture](#database-architecture)
* [Getting started](#getting-started)
   * [Run application](#run-application)
   * [Apply code style](#apply-code-style)
   * [example GraphQl request](#apply-code-style)
   * [Run test](#run-test)
   * [Run build](#run-build)


---


## How it works
The example project aims to represent a simple blog  


1. Authenticate users via JWT 
2. sign up/login users
3. create/update/detete articles
4. add feedback to articles
5. Favorite articles
6. Follow other users

### Project structures
The project is implemented based on Java 21 and Spring Boot 3, 
utilizing various Spring technologies such as Spring MVC, Spring Data JPA, and Spring Security. 
It uses H2 DB (in-memory, MySQL mode) as the database.
and JUnit5 for writing test codes.

#### Modules


`core`, `persistence`, `api` and `bootstrap` modules exist, and each module performs the following roles.

- bootstrap: All existing modules are put together to form an executable application.
- core: Contains the core logic of the application, including the domain model, service, and exception handling.
- persistence: Contains the persistence layer logic of the application, including the repository and entity.
- api: Contains the API layer logic of the application, including the controller and DTO and Graphql.


#### Classes
- ~Controller: Processes HTTP requests, Graphql Requests, calls business logic, and generates responses.
- ~Service: Implements business logic and interacts with the database through Repositories.
- ~Repository: An interface for interacting with the database, implemented using Spring Data JPA. 

---

## Database architecture
> **Note:** I paid attention to data types, but did not pay much attention to size.


- [schema.sql](database/schema.sql)

---

## Getting started

> **Note:** You just need to have JDK 21 installed.

### Run application

```shell
./gradlew :bootstrap:bootRun
```

### Apply code style

> **Note:** When you run the `build` task, this task runs automatically. If the code style doesn't match, the build will fail.

```shell
./gradlew spotlessApply
```


### Example GraphQl request
```shell
POST using graphql client

http://localhost:8080/graphql

query
UserByName {
  userByName(username: "u1714138542") { 
      username
      email
      username
      bio
      article {
           title
      }
  }
}
```


### Run test

```shell
./gradlew test
```

### Run build

```shell
./gradlew build
```

### Run E2E test

1. Run application (**important**)
2. [Run E2E test](e2e/README.md#running-api-tests-locally)

