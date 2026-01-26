# event-driven architecture

A hands-on learning project demonstrating **event-driven architecture** using
Spring Boot, AWS Lambda, queues (SQS), Docker, and GitHub Actions.

---

## 🧠 What This Project Demonstrates

- REST API using Spring Boot
- Event-driven architecture
- Queue-based processing (SQS)
- AWS Lambda (HTTP + Queue trigger)
- Adapter pattern for messaging
- Reusable common library
- Dockerized local development
- CI/CD using GitHub Actions

---

## 🏗️ Architecture Overview

Client  
⬇  
**Spring Boot API** (Dockerized)  
⬇  
**Queue** (Amazon SQS / LocalStack)  
⬇  
**AWS Lambda**


---

## 🧩 Modules

| Module | Description |
|------|------------|
| api-service | Spring Boot REST API |
| lambda-functions | AWS Lambda handlers |
| common-lib | Shared utilities and interfaces |
| docker | Local Docker setup |
| .github | CI/CD pipelines |

---

## 🚀 How to Run Locally

### Prerequisites
- Java 21
- Maven
- Docker

### Run API
```bash
cd api-service
mvn spring-boot:run
