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

```
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
                H2 Database
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
| Maven | ✓ |
| Lombok | ✓ |
| JUnit 5 | ✓ |
| Mockito | ✓ |
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
- Mockito for dependency isolation
- Factory Pattern for test data creation
- Arrange – Act – Assert (AAA)
- Positive and Negative Test Scenarios
- JaCoCo Code Coverage
- PIT Mutation Testing

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

The project includes unit tests developed using:

- JUnit 5
- Mockito
- Factory Pattern
- Arrange – Act – Assert (AAA)

### Test Coverage (JaCoCo)

| Metric | Coverage |
|---------|---------:|
| Instructions | **91%** |
| Branches | **42%** |

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

Every push automatically performs:

- Project Build
- Unit Tests
- JaCoCo Coverage
- PIT Mutation Testing
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
JUnit Tests
    │
    ▼
JaCoCo
    │
    ▼
PIT Mutation Testing
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

- Expand Integration Tests
- Testcontainers
- Docker Support
- PostgreSQL Profile
- Performance Tests
- API Contract Testing

---

# 👨‍💻 Author

**Marcelo Bellato**

Senior QA Engineer | Software Quality Engineer

- GitHub: https://github.com/marcelo-bellato
- LinkedIn: https://www.linkedin.com/in/marcelobellato