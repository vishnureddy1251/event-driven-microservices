# event-driven architecture

Student Grading System (Event-Driven)

This project is a hands-on learning implementation of an event-driven architecture for managing student grades.
It demonstrates how modern backend systems decouple user requests from processing logic using queues and serverless computing.

The system is built using Spring Boot, AWS SQS, AWS Lambda, Docker, and GitHub Actions.

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


# 🚀 Event-Driven Microservices

A production-ready event-driven microservices application built with Spring Boot, AWS SQS, and LocalStack.

## 📋 Features

**REST API - 13 endpoints for grade management**
**Event-Driven Architecture** - Asynchronous message processing
**AWS SQS Integration** - Cloud message queuing
**LocalStack Support** - Local AWS development
**Docker Ready** - Containerized deployment
**CI/CD Pipeline** - Automated testing and deployment

## 🏗️ Architecture
```
Client → Spring Boot API → AWS SQS → Event Processor → Actions
```

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.2.10**
- **AWS SDK for SQS**
- **Maven**
- **Docker & Docker Compose**
- **LocalStack**
- **GitHub Actions**

## 🚀 Quick Start

### Prerequisites

- Java 21
- Maven 3.6+
- Docker Desktop

### 1. Clone Repository
```bash
git clone https://github.com/YOUR_USERNAME/event-driven-microservices.git
cd event-driven-microservices
```

### 2. Start LocalStack
```bash
docker-compose up -d
```

### 3. Build Application
```bash
mvn clean install
```

### 4. Run Application
```bash
mvn spring-boot:run
```

### 5. Test API
```bash
curl http://localhost:8080/api/grades
```

## 📚 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/grades` | Health check |
| POST | `/api/grades/submit` | Submit a grade |
| GET | `/api/grades/students` | Get all students |
| GET | `/api/grades/students/{id}` | Get student by ID |
| GET | `/api/grades/stats` | Get statistics |
| POST | `/api/grades/process-all` | Process SQS events |
| GET | `/api/grades/top` | Get top students (A/B) |
| GET | `/api/grades/failing` | Get failing students (F) |
| GET | `/api/grades/search?name=X` | Search by name |

## 🧪 Example Usage

### Submit a Grade
```bash
curl -X POST http://localhost:8080/api/grades/submit \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","subject":"Math","score":95}'
```

### Process Events from SQS
```bash
curl -X POST http://localhost:8080/api/grades/process-all
```

### Get Statistics
```bash
curl http://localhost:8080/api/grades/stats
```

## 🐳 Docker

### Build Image
```bash
docker build -t event-driven-microservices .
```

### Run Container
```bash
docker run -p 8080:8080 event-driven-microservices
```

## 🧪 Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn verify
```

## 📊 CI/CD Pipeline

GitHub Actions automatically:

- ✅ Builds the application
- ✅ Runs all tests
- ✅ Creates Docker image
- ✅ Runs security scans
- ✅ Integration tests with LocalStack

## 🏛️ Project Structure
```
event-driven-microservices/
├── src/
│   ├── main/
│   │   ├── java/com/eventdrivenmicroservices/api/
│   │   │   ├── config/          # AWS configuration
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── service/         # Business logic
│   │   │   ├── model/           # Domain models
│   │   │   └── event/           # Event definitions
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── .github/
│   └── workflows/
│       └── ci-cd.yml            # GitHub Actions pipeline
├── docker-compose.yml           # LocalStack setup
├── Dockerfile                   # Container definition
└── pom.xml                      # Maven configuration
```

## 📝 License

MIT License

## 👤 Author

Vishnu

---

