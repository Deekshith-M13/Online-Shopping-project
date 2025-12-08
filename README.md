# 🛒 Online Shopping Microservices Platform

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.0-brightgreen?style=for-the-badge&logo=spring)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Keycloak](https://img.shields.io/badge/Keycloak-4D4D4D?style=for-the-badge&logo=keycloak&logoColor=white)
![Netflix Eureka](https://img.shields.io/badge/Eureka-FF0000?style=for-the-badge&logo=netflix&logoColor=white)
![Resilience4j](https://img.shields.io/badge/Resilience4j-00C7B7?style=for-the-badge)
![Zipkin](https://img.shields.io/badge/Zipkin-FF6B6B?style=for-the-badge)

</div>

A comprehensive microservices-based e-commerce platform built with Spring Boot, demonstrating modern cloud-native architecture patterns and best practices.

## 📋 Table of Contents

- [Overview](#overview)
- [Architecture](#architecture)
- [Microservices](#microservices)
- [Technology Stack](#technology-stack)
- [Key Features](#key-features)
- [Getting Started](#getting-started)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Monitoring & Observability](#monitoring--observability)

## 🎯 Overview

This project implements a scalable online shopping platform using microservices architecture. It showcases enterprise-grade patterns including service discovery, API gateway, circuit breakers, distributed tracing, and event-driven communication.

## 🏗️ Architecture

```
                                    ┌─────────────────┐
                                    │   API Gateway   │
                                    │   (Port 8080)   │
                                    └────────┬────────┘
                                             │
                    ┌────────────────────────┼────────────────────────┐
                    │                        │                        │
            ┌───────▼────────┐      ┌───────▼────────┐      ┌───────▼────────┐
            │ Product Service│      │ Order Service  │      │Inventory Service│
            │  (Port 8080)   │      │  (Port 8081)   │      │  (Port 8082)   │
            └────────────────┘      └───────┬────────┘      └────────────────┘
                                            │
                                    ┌───────▼────────┐
                                    │Notification Svc │
                                    │  (Kafka)       │
                                    └────────────────┘
                         
                    ┌────────────────────────────────────┐
                    │     Discovery Server (Eureka)      │
                    │          (Port 8761)               │
                    └────────────────────────────────────┘
```

### Core Components

- **API Gateway**: Single entry point for all client requests with routing and security
- **Service Discovery**: Netflix Eureka for dynamic service registration and discovery
- **Circuit Breaker**: Resilience4j for fault tolerance and graceful degradation
- **Event Bus**: Apache Kafka for asynchronous inter-service communication
- **Distributed Tracing**: Zipkin for request tracing across microservices

## 🔧 Microservices

### 1. Product Service
Manages product catalog and inventory listings.

**Key Responsibilities:**
- CRUD operations for products
- Product search and filtering
- MongoDB for data persistence

### 2. Order Service
Handles order processing and orchestration.

**Key Responsibilities:**
- Order creation and management
- Inventory verification via synchronous calls
- Order status tracking
- Event publishing to Kafka

### 3. Inventory Service
Manages stock levels and availability.

**Key Responsibilities:**
- Stock management
- Availability checks
- Real-time inventory updates

### 4. Notification Service
Handles all notification delivery.

**Key Responsibilities:**
- Listens to order events via Kafka
- Email/SMS notification processing
- Event-driven architecture

### 5. API Gateway
Routes and secures all incoming requests.

**Features:**
- Request routing to appropriate services
- OAuth2 authentication via Keycloak
- Load balancing

### 6. Discovery Server
Service registry for dynamic service discovery.

**Features:**
- Service registration and health checks
- Client-side load balancing
- Automatic failover

## 💻 Technology Stack

### Core Technologies
- **Java 21**: Latest LTS version with modern language features
- **Spring Boot 3.x**: Microservices framework
- **Spring Cloud**: Microservices patterns implementation

### Databases
- **MongoDB**: NoSQL database for Product Service

### Communication
- **WebClient**: Reactive HTTP client for inter-service communication
- **Apache Kafka**: Event streaming platform

### Service Discovery & Gateway
- **Netflix Eureka**: Service registry
- **Spring Cloud Gateway**: API Gateway implementation

### Security
- **Keycloak**: Identity and access management
- **OAuth2**: Authentication and authorization

### Resilience
- **Resilience4j**: Circuit breaker and fault tolerance
- **Time Limiter**: Request timeout management

### Testing
- **Testcontainers**: Integration testing with real dependencies
- **JUnit 5**: Testing framework
- **MockMvc**: REST API testing

### Observability
- **Spring Boot Actuator**: Application monitoring
- **Zipkin**: Distributed tracing
- **Spring Cloud Sleuth**: Trace ID generation

### DevOps
- **Docker**: Containerization
- **Docker Compose**: Multi-container orchestration
- **Maven**: Build automation

## ✨ Key Features

### 1. Service Discovery Pattern
Dynamic service registration and discovery using Netflix Eureka eliminates hardcoded URLs and enables automatic load balancing.

```java
@LoadBalanced
public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
}
```

### 2. Circuit Breaker Pattern
Prevents cascading failures using Resilience4j circuit breakers with configurable fallback mechanisms.

### 3. Event-Driven Architecture
Asynchronous communication between services using Kafka for loose coupling and improved scalability.

### 4. API Gateway Pattern
Single entry point for all API requests with centralized security, routing, and cross-cutting concerns.

### 5. Containerized Testing
Testcontainers for integration tests with real database instances, ensuring test reliability without local setup.

### 6. Distributed Tracing
End-to-end request tracing across all microservices for debugging and performance monitoring.

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Docker and Docker Compose
- Maven 3.8+
- (Optional) Keycloak for security features

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/Deekshith-M13/Online-Shopping-project.git
cd Online-Shopping-project
```

2. **Start infrastructure services**
```bash
docker-compose up -d
```

This starts:
- Zookeeper (Port 2181)
- Kafka (Port 9092)
- MongoDB (Port 27017)
- Keycloak (Port 8181)
- Zipkin (Port 9411)

3. **Build all services**
```bash
mvn clean install
```

4. **Start Discovery Server**
```bash
cd discovery-server
mvn spring-boot:run
```

5. **Start API Gateway**
```bash
cd api-gateway
mvn spring-boot:run
```

6. **Start Microservices**

In separate terminals:
```bash
cd product-service && mvn spring-boot:run
cd order-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
cd notification-service && mvn spring-boot:run
```

### Service Ports

| Service | Port |
|---------|------|
| API Gateway | 8080 |
| Product Service | 8080 |
| Order Service | 8081 |
| Inventory Service | 8082 |
| Discovery Server | 8761 |
| Keycloak | 8181 |
| Zipkin | 9411 |
| Kafka | 9092 |

## 🧪 Testing

The project uses Testcontainers for integration testing, providing isolated test environments with real dependencies.

### Run Tests
```bash
mvn test
```

### Test Features
- **Automated Container Management**: Docker containers start/stop automatically
- **Real Database Testing**: Tests run against actual MongoDB instances
- **Clean State**: Fresh database for each test run
- **CI/CD Ready**: Works in any environment with Docker

## 📚 API Documentation

### Product Service

**Create Product**
```http
POST /api/product
Content-Type: application/json
Authorization: Bearer <token>

{
  "name": "Product Name",
  "description": "Product Description",
  "price": 99.99
}
```

**Get All Products**
```http
GET /api/product
Authorization: Bearer <token>
```

### Order Service

**Place Order**
```http
POST /api/order
Content-Type: application/json
Authorization: Bearer <token>

{
  "orderLineItemsList": [
    {
      "skuCode": "SKU123",
      "quantity": 2
    }
  ]
}
```

### Inventory Service

**Check Stock**
```http
GET /api/inventory?skuCode=SKU123&skuCode=SKU456
Authorization: Bearer <token>
```

## 📊 Monitoring & Observability

### Eureka Dashboard
```
http://localhost:8761
```
View all registered services and their health status.

### Circuit Breaker Health
```
http://localhost:8081/actuator/circuitbreakers
```
Monitor circuit breaker states and metrics.

### Distributed Tracing
```
http://localhost:9411
```
Access Zipkin UI for request tracing and latency analysis.

## 🔒 Security

The application uses Keycloak for OAuth2-based authentication and authorization.

### Configuration
```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8181/realms/spring-boot-microservices-realm
```

### Obtaining Access Token
Configure Keycloak realm and obtain JWT tokens for API authentication.

## 🐳 Docker Deployment

### Build Docker Images
```bash
# Build individual service
cd product-service
mvn clean package
docker build -t product-service .

# Or use Docker Compose for all services
docker-compose build
```

### Run with Docker Compose
```bash
docker-compose up -d
```

## 🛠️ Configuration

Key configuration files:
- `application.properties`: Service-specific configuration
- `docker-compose.yml`: Infrastructure services
- `pom.xml`: Maven dependencies and build configuration

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Deekshith M**
- GitHub: [@Deekshith-M13](https://github.com/Deekshith-M13)

