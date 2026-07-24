# 🚀 API-Rest

[![Java CI](https://github.com/marcelo-bellato/API-Rest/actions/workflows/ci.yml/badge.svg)](https://github.com/marcelo-bellato/API-Rest/actions/workflows/ci.yml)

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.5.5-brightgreen)
![JUnit5](https://img.shields.io/badge/JUnit-5-success)
![Mockito](https://img.shields.io/badge/Mockito-Unit_Testing-blue)
![JaCoCo](https://img.shields.io/badge/Coverage-JaCoCo-yellowgreen)
![SonarCloud](https://img.shields.io/badge/Code_Quality-SonarCloud-blue)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## 📖 About

REST API developed with **Spring Boot** following good software engineering practices.

This project demonstrates:

- REST API development
- Layered Architecture
- CRUD operations
- Bean Validation
- Global Exception Handling
- Unit Testing
- Code Coverage with JaCoCo
- Continuous Integration using GitHub Actions
- Static Code Analysis using SonarCloud

---

## 🏗 Architecture

```
Controller
     │
     ▼
 Service
     │
     ▼
Repository
     │
     ▼
Database (H2)
```

---

## 🛠 Technologies

| Technology | Version |
|------------|----------|
| Java | 17 |
| Spring Boot | 2.5.5 |
| Spring Data JPA | ✓ |
| Bean Validation | ✓ |
| H2 Database | ✓ |
| JUnit 5 | ✓ |
| Mockito | ✓ |
| JaCoCo | ✓ |
| Maven | ✓ |
| GitHub Actions | ✓ |
| SonarCloud | ✓ |

---
# API-Rest

Badges

About

Architecture

Technologies

⭐ Quality Engineering Practices   ← AQUI

Project Structure

Features

Testing

Running the project

Running the tests

Continuous Integration

Code Quality

Author

---

## 📂 Project Structure

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

## ⚙ Features

- Create User
- List Users
- Find User by ID
- Update User
- Delete User

---

## 🧪 Testing

The project contains unit tests using:

- JUnit 5
- Mockito
- UserFactory Pattern
- AAA Pattern (Arrange, Act, Assert)

### Current Quality Metrics

- ✅ GitHub Actions
- ✅ SonarCloud
- ✅ JaCoCo
- ✅ Quality Gate Passed
- ✅ 0% Code Duplication

---

## 🚀 Running the project

Clone the repository

```bash
git clone https://github.com/marcelo-bellato/API-Rest.git
```

Enter the folder

```bash
cd API-Rest
```

Run

```bash
./mvnw spring-boot:run
```

Windows

```bash
mvnw.cmd spring-boot:run
```

---

## ▶ Running the tests

```bash
./mvnw clean test
```

Generate JaCoCo report

```bash
./mvnw clean verify
```

---

## 📊 Continuous Integration

Every push automatically executes:

- Build
- Unit Tests
- JaCoCo Coverage
- SonarCloud Analysis
- Quality Gate

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

## 📈 Code Quality

This project uses:

- SonarCloud
- JaCoCo
- GitHub Actions

to ensure code quality and maintainability.

---

## 👨‍💻 Author

**Marcelo Bellato**

Senior QA Engineer

- LinkedIn: https://www.linkedin.com/in/marcelobellato
- GitHub: https://github.com/marcelo-bellato
