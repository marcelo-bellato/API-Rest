# 🚀 API-Rest

[![Java CI](https://github.com/marcelo-bellato/API-Rest/actions/workflows/ci.yml/badge.svg)](https://github.com/marcelo-bellato/API-Rest/actions/workflows/ci.yml)

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.5.5-brightgreen)
![JUnit5](https://img.shields.io/badge/JUnit-5-success)
![Mockito](https://img.shields.io/badge/Mockito-Unit_Testing-blue)
![JaCoCo](https://img.shields.io/badge/Coverage-91%25-brightgreen)
![SonarCloud](https://img.shields.io/badge/Code_Quality-SonarCloud-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

# 📖 About

REST API developed with **Spring Boot** to demonstrate backend development, software quality engineering practices, automated testing and continuous integration.

The project follows a layered architecture and applies testing best practices to ensure maintainability, reliability and code quality.

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
| JUnit 5 | ✓ |
| Mockito | ✓ |
| JaCoCo | ✓ |
| GitHub Actions | ✓ |
| SonarCloud | ✓ |

---

# ⭐ Quality Engineering Practices

This project was developed applying Quality Engineering principles.

## Software Engineering

- Layered Architecture
- SOLID Principles
- Clean Code
- DTO Pattern
- Dependency Injection
- Global Exception Handling

## Automated Testing

- Unit Tests using JUnit 5
- Mockito for dependency isolation
- Factory Pattern for test data creation
- Arrange – Act – Assert (AAA)
- Positive and Negative Test Scenarios
- JaCoCo Code Coverage

## DevOps

- GitHub Actions CI
- SonarCloud Static Analysis
- Automated Build Validation

---

# 📂 Project Structure

```
src
├── main
│   ├── config
│   ├── domain
│   ├── repositories
│   ├── resources
│   ├── services
│   └── exceptions
│
└── test
    ├── factory
    ├── resources
    ├── services
    └── exceptions
```

---

# ⚙ Features

- Create User
- List Users
- Find User by ID
- Update User
- Delete User
- Exception Handling
- Input Validation

---

# 🧪 Testing

The project contains unit tests using:

- JUnit 5
- Mockito
- Factory Pattern
- AAA Pattern (Arrange – Act – Assert)

## Current Coverage

| Metric | Coverage |
|---------|---------:|
| Instructions | **91%** |
| Branches | **42%** |

Quality metrics:

- ✅ Unit Tests
- ✅ JaCoCo
- ✅ GitHub Actions
- ✅ SonarCloud
- ✅ Quality Gate Passed
- ✅ 0% Code Duplication

---

# 🚀 Running the project

Clone repository

```bash
git clone https://github.com/marcelo-bellato/API-Rest.git
```

Enter the project

```bash
cd API-Rest
```

Run

Linux / Mac

```bash
./mvnw spring-boot:run
```

Windows

```bash
mvnw.cmd spring-boot:run
```

Application:

```
http://localhost:8080
```

---

# ▶ Running the tests

Execute

```bash
./mvnw clean test
```

Generate coverage report

```bash
./mvnw clean verify
```

Coverage report:

```
target/site/jacoco/index.html
```

---

# 📊 Continuous Integration

Every push automatically performs:

- Build
- Unit Tests
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
JUnit Tests
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

# 🚀 Future Improvements

- Integration Tests
- Testcontainers
- Docker Support
- PostgreSQL Profile
- Swagger / OpenAPI
- Performance Tests

---

# 👨‍💻 Author

**Marcelo Bellato**

Senior QA Engineer

- LinkedIn: https://www.linkedin.com/in/marcelobellato
- GitHub: https://github.com/marcelo-bellato