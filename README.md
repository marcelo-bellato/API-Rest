# 🚀 API-Rest

[![Java CI](https://github.com/marcelo-bellato/API-Rest/actions/workflows/ci.yml/badge.svg)](https://github.com/marcelo-bellato/API-Rest/actions/workflows/ci.yml)

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.5.5-brightgreen)
![JUnit5](https://img.shields.io/badge/JUnit-5-success)
![Mockito](https://img.shields.io/badge/Mockito-Unit_Testing-blue)
![JaCoCo](https://img.shields.io/badge/JaCoCo-91%25-success)
![SonarCloud](https://img.shields.io/badge/SonarCloud-A-brightgreen)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

# 📖 About

This project was developed to demonstrate **Software Quality Engineering** practices using **Spring Boot**.

Rather than simply implementing a REST API, the project focuses on building maintainable software through automated testing, code quality analysis, continuous integration, and clean architecture.

The application implements a complete CRUD for user management while following backend development and Quality Engineering best practices.

---

# 🏗 Architecture

### Application

```text
HTTP Request
     │
     ▼
REST Controller
     │
     ▼
Service Layer
     │
     ▼
Spring Data JPA
     │
     ▼
Database
```
### Integration Testing

Integration tests use JUnit 5 and Testcontainers to provision a PostgreSQL container running through Docker.

```
   JUnit 5
     │
     ▼
Testcontainers
     │
     ▼
PostgreSQL Container
     │
     ▼
Docker Engine
```

---

# 🛠 Technologies

| Technology | Version |
|------------|----------|
| Java | 17 |
| Spring Boot | 2.5.5 |
| Spring Data JPA | ✓ |
| Bean Validation | ✓ |
| H2 Database | ✓ |
| PostgreSQL | ✓ |
| Maven | ✓ |
| Lombok | ✓ |
| JUnit 5 | ✓ |
| Mockito | ✓ |
| Testcontainers | 1.21.4 |
| JaCoCo | ✓ |
| GitHub Actions | ✓ |
| SonarCloud | ✓ |


---

# ⭐ Quality Engineering Practices

This project applies modern Quality Engineering principles throughout the development lifecycle.

## Software Engineering

- Layered Architecture
- SOLID Principles
- Clean Code
- DTO Pattern
- Dependency Injection
- Global Exception Handling

## Automated Testing

- Unit Tests with JUnit 5
- Integration Tests with PostgreSQL
- Testcontainers for database isolation
- Mockito for dependency isolation
- Factory Pattern for test data creation
- Arrange – Act – Assert (AAA)
- Positive and Negative Test Scenarios
- JaCoCo Code Coverage

## Continuous Integration

- GitHub Actions
- Automated Build Validation
- SonarCloud Static Code Analysis
- Quality Gate Validation

---

# 📂 Project Structure

```
API-Rest
├── .github
├── .mvn
├── scripts
│   └── setup-java.ps1
├── src
│   ├── main
│   │   ├── config
│   │   ├── domain
│   │   ├── repositories
│   │   ├── resources
│   │   ├── services
│   │   └── exceptions
│   │
│   └── test
│       ├── factory
│       ├── repositories
│       ├── resources
│       ├── services
│       └── exceptions
│
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

# ⚙ Features

- Create User
- List Users
- Find User by ID
- Update User
- Delete User
- Bean Validation
- Global Exception Handling
- RESTful API Design

---

# 🧪 Testing

The project includes automated unit and integration tests developed using:

- JUnit 5
- Mockito
- Testcontainers
- PostgreSQL
- Factory Pattern
- Arrange – Act – Assert (AAA)

### Test Execution

The test suite includes unit tests and integration tests.

Integration tests use **Testcontainers** to provision a PostgreSQL database dynamically in Docker, avoiding the need for a local PostgreSQL installation.

```bash
./mvnw clean test
```
Current test result:
```
Tests run: 42
Failures: 0
Errors: 0
Skipped: 0
BUILD SUCCESS
```

Test Coverage (JaCoCo)

| Metric | Coverage |
|---------|---------:|
| JaCoCo Instructions | **91%** |
| JaCoCo Branches | **42%** |
| SonarCloud Coverage | **80.5%** |

Coverage report:
```
target/site/jacoco/index.html
```
---
# 🐳 Integration Testing with Testcontainers

The project uses Testcontainers to execute repository integration tests against a real PostgreSQL database running in a Docker container.

This approach provides:

- Isolated test environments
- Reproducible integration tests
- No dependency on a locally installed PostgreSQL database
- Real database behavior during integration testing
- Automatic container lifecycle management

The PostgreSQL container is created during the integration test execution and automatically removed after the tests finish.

### Example

```java
@Testcontainers
@DataJpaTest
class UserRepositoryPostgresIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:16-alpine");

    // integration tests
}
```
---


# 📊 Code Quality

Current quality metrics:

| Metric | Status |
|---------|--------|
| Quality Gate | ✅ Passed |
| Security Rating | 🟢 A |
| Reliability Rating | 🟢 A |
| Maintainability Rating | 🟢 A |
| Code Duplication | **0%** |
| SonarCloud Coverage | **80.5%** |

### Tools

- ✅ JUnit 5
- ✅ Mockito
- ✅ JaCoCo
- ✅ PIT Mutation Testing
- ✅ GitHub Actions
- ✅ SonarCloud

---

# 🚀 Running the project

## Clone the repository

```bash
git clone https://github.com/marcelo-bellato/API-Rest.git
```

Enter the project

```bash
cd API-Rest
```

## Environment Setup

The project uses Java 17.

Windows

A PowerShell script is available to simplify the Java environment configuration:
```bash
.\scripts\setup-java.ps1
```
The script configures:

- JAVA_HOME
- Java PATH
- Java version validation

After running the script, validate the Java and Maven configuration:

```bash
.\mvnw.cmd -version
```
The expected output should show Java 17.

## Run the application

Linux / macOS

```bash
./mvnw spring-boot:run
```

Windows

```bash
mvnw.cmd spring-boot:run
```

Application URL

```
http://localhost:8080
```

---

# ▶ Running the tests

Execute unit tests

```bash
./mvnw clean test
```

Generate JaCoCo report

```bash
./mvnw clean verify
```

Coverage report location

```
target/site/jacoco/index.html
```


---

# 🔄 Continuous Integration

Changes submitted through GitHub automatically trigger:

- Project Build
- Unit Tests
- Integration Tests
- PostgreSQL Container
- JaCoCo Coverage
- SonarCloud Analysis
- Quality Gate Validation
```
Developer
    │
    ▼
GitHub
    │
    ▼
GitHub Actions
    │
    ▼
Build
    │
    ▼
Unit Tests
    │
    ▼
Integration Tests
    │
    ▼
Testcontainers
    │
    ▼
PostgreSQL
    │
    ▼
JaCoCo
    │
    ▼
SonarCloud
    │
    ▼
Quality Gate
```


---

# 🔐 Quality Gate

The `master` branch is protected by a GitHub Ruleset named **Master Quality Gate**.

All changes targeting `master` must be submitted through a Pull Request.

The following checks are required before merging:

- Pull Request required
- GitHub Actions `quality` check
- Branch must be up to date with `master`
- Force pushes are blocked
- Branch deletion is restricted

The `quality` workflow validates:

- Maven build
- Automated tests
- JaCoCo code coverage
- PIT mutation testing
- SonarCloud Quality Gate

A Pull Request can only be merged when the required `quality` check passes successfully.

```text
Feature Branch
      │
      ▼
Pull Request
      │
      ▼
GitHub Actions
      │
      ▼
   quality
      │
      ├── Tests
      ├── JaCoCo
      ├── PIT
      └── SonarCloud
      │
      ▼
Quality Gate
      │
      ▼
   ✅ PASS
      │
      ▼
Merge to master
```

---



# 🚀 Future Improvements

- Docker Support for Application
- PostgreSQL Profile
- Performance Tests
- API Contract TestingJaCoCo

---

# 👨‍💻 Author

**Marcelo Bellato**

Senior QA Engineer | Software Quality Engineer

- GitHub: https://github.com/marcelo-bellato
- LinkedIn: https://www.linkedin.com/in/marcelobellato