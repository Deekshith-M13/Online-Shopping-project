# 🛒 Online Shopping Microservices Platform

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.0-brightgreen?style=for-the-badge&logo=spring)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4EA94B?style=for-the-badge&logo=mongodb&logoColor=white)
![Kafka](https://img.shields.io/badge/Apache%20Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Keycloak](https://img.shields.io/badge/Keycloak-4D4D4D?style=for-the-badge&logo=keycloak&logoColor=white)
![Netflix Eureka](https://img.shields.io/badge/Eureka-FF0000?style=for-the-badge&logo=netflix&logoColor=white)
![Resilience4j](https://img.shields.io/badge/Resilience4j-00C7B7?style=for-the-badge)
![Testcontainers](https://img.shields.io/badge/Testcontainers-2496ED?style=for-the-badge)

</div>

A cloud-native, production-grade **microservices-based e-commerce platform** built using Spring Boot, Spring Cloud, Kafka, Docker, MySQL, and MongoDB.

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
- [Security](#security)
- [Docker Deployment](#docker-deployment)

## 🎯 Overview

A complete **online shopping platform** built using **microservices architecture**, demonstrating:

- Service discovery
- API gateway routing
- Circuit breakers
- Event-driven communication with Kafka
- Distributed authentication using Keycloak
- MySQL + MongoDB hybrid storage

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
            │   (MongoDB)    │      │    (MySQL)     │      │    (MySQL)      │
            └────────────────┘      └───────┬────────┘      └────────────────┘
                                            │
                                    ┌───────▼────────┐
                                    │Notification Svc │
                                    │    (Kafka)     │
                                    └────────────────┘
                         
                    ┌────────────────────────────────────┐
                    │     Discovery Server (Eureka)      │
                    │          (Port 8761)               │
                    └────────────────────────────────────┘
```

## 🔧 Microservices

### 1️⃣ Product Service (MongoDB)
Manages product catalog with NoSQL database for flexibility.

**Key Responsibilities:**
- CRUD operations for products
- Product search and filtering
- Fast NoSQL storage for product data

### 2️⃣ Order Service (MySQL)
Handles order processing and orchestration.

**Key Responsibilities:**
- Order creation and management
- Inventory verification via WebClient
- Order status tracking
- Event publishing to Kafka

### 3️⃣ Inventory Service (MySQL)
Manages stock levels and availability.

**Key Responsibilities:**
- Stock management
- Availability checks via synchronous API
- Real-time inventory updates

### 4️⃣ Notification Service (Kafka Consumer)
Handles all notification delivery asynchronously.

**Key Responsibilities:**
- Listens to order events via Kafka
- Email/SMS notification processing
- Event-driven architecture

### 5️⃣ API Gateway
Routes and secures all incoming requests.

**Features:**
- Single entry point for all services
- OAuth2 authentication via Keycloak
- Load balancing and routing

### 6️⃣ Discovery Server (Eureka)
Service registry for dynamic service discovery.

**Features:**
- Service registration and health checks
- Client-side load balancing
- Automatic failover

## 💻 Technology Stack

### Core Technologies
- **Java 21**: Latest LTS version with modern language features
- **Spring Boot 3.x**: Microservices framework
- **Spring Cloud 2025.x**: Microservices patterns implementation

### Databases

| Service | Database |
|---------|----------|
| Product Service | **MongoDB** |
| Order Service | **MySQL** |
| Inventory Service | **MySQL** |

### Communication
- **WebClient**: Reactive HTTP client for inter-service communication
- **Apache Kafka**: Event streaming platform for async messaging

### Service Discovery & Gateway
- **Netflix Eureka**: Service registry
- **Spring Cloud Gateway**: API Gateway implementation

### Security
- **Keycloak**: Identity and access management
- **OAuth2**: Authentication and authorization
- **JWT**: Token-based security

### Resilience
- **Resilience4j**: Circuit breaker and fault tolerance
- **Time Limiter**: Request timeout management

### Testing
- **Testcontainers**: Integration testing with real dependencies
- **JUnit 5**: Testing framework
- **MockMvc**: REST API testing

### Observability
- **Spring Boot Actuator**: Application monitoring and health checks

### DevOps
- **Docker**: Containerization
- **Docker Compose**: Multi-container orchestration
- **Maven**: Build automation

## ✨ Key Features

### 1. Service Discovery Pattern
Dynamic service registration and discovery using Netflix Eureka eliminates hardcoded URLs and enables automatic load balancing.

```java
@LoadBalanced
@Bean
public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
}
```

### 2. Circuit Breaker Pattern
Prevents cascading failures using Resilience4j circuit breakers with configurable fallback mechanisms and automatic retries.

### 3. Event-Driven Architecture
Asynchronous communication between services using Kafka for loose coupling and improved scalability.

### 4. API Gateway Pattern
Single entry point for all API requests with centralized security, routing, and cross-cutting concerns.

### 5. Containerized Testing
Testcontainers for integration tests with real database instances (MySQL, MongoDB, Kafka), ensuring test reliability without local setup.

### 6. Hybrid Database Architecture
Uses MongoDB for flexible product catalog and MySQL for transactional data (orders, inventory).

## 🚀 Getting Started

### Prerequisites

- Java 21 or higher
- Docker and Docker Compose
- Maven 3.8+

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
- MySQL (Port 3306)
- Keycloak (Port 8181)

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
| Kafka | 9092 |
| MySQL | 3306 |
| MongoDB | 27017 |

## 🗄️ MySQL Configuration

MySQL is used for **Order Service** and **Inventory Service**.

### Docker Compose Configuration
```yaml
mysql:
  image: mysql:8
  container_name: mysql-db
  environment:
    MYSQL_ROOT_PASSWORD: root
    MYSQL_DATABASE: orders
  ports:
    - "3306:3306"
  restart: always
```

### Application Properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/orders
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## 🧪 Testing

The project uses Testcontainers for integration testing, providing isolated test environments with real dependencies.

### Run Tests
```bash
mvn test
```

### Test Features
- **Automated Container Management**: Docker containers start/stop automatically
- **Real Database Testing**: Tests run against actual MySQL and MongoDB instances
- **Kafka Testing**: Real Kafka broker for integration tests
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

### Actuator Endpoints
```
http://localhost:8081/actuator/health
http://localhost:8081/actuator/metrics
```

## 🔒 Security

The application uses Keycloak for OAuth2-based authentication and authorization.

### Configuration
```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8181/realms/spring-boot-microservices-realm
```

### Obtaining Access Token
1. Configure Keycloak realm
2. Create client application
3. Obtain JWT tokens for API authentication
4. Include token in Authorization header

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

### Stop Services
```bash
docker-compose down
```

## 🛠️ Configuration

Key configuration files:
- `application.properties`: Service-specific configuration
- `docker-compose.yml`: Infrastructure services
- `pom.xml`: Maven dependencies and build configuration


## 📝 License

This project is open source and available under the [MIT License](LICENSE).

## 👨‍💻 Author

**Deekshith M**
- GitHub: [@Deekshith-M13](https://github.com/Deekshith-M13)

