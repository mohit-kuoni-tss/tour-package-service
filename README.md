# Tour & Travel Management System (Reactive)

## Project Overview
This is a high-performance, reactive Spring Boot microservice for managing Tour Packages. It uses Spring WebFlux for non-blocking I/O and Oracle R2DBC for reactive database connectivity. The system is designed with a clean layered architecture, ensuring maintainability, scalability, and adherence to enterprise standards.

## Tech Stack
- **Java 25**: Cutting-edge version for enterprise performance.
- **Spring Boot 4.0.1**: Latest version for building production-ready applications.
- **Spring WebFlux**: Reactive web framework for non-blocking REST APIs.
- **SpringDoc OpenAPI (Swagger)**: Automated API documentation.
- **Oracle Database**: Enterprise-grade relational database.
- **Oracle R2DBC Driver**: Reactive driver for asynchronous database access.
- **Project Reactor**: Core library for reactive programming (Mono and Flux).
- **Jakarta Validation**: Robust input validation.
- **SLF4J**: Standard logging facade.

## Architecture Explanation
The project follows a **Clean Layered Architecture** under the package `com.kuoni.tumlare.tourpackageservice`:
1.  **Controller Layer**: Handles HTTP requests, performs input validation, and delegates to the service layer.
2.  **Service Layer**: Contains business logic. It orchestrates operations and interacts with repositories. It never blocks.
3.  **Repository Layer**: Handles data persistence using `ReactiveCrudRepository`.
4.  **Entity Layer**: Represents the database schema.
5.  **DTO Layer**: Ensures data encapsulation and separates the API contract from the internal domain model.
6.  **Mapper Layer**: Manual mapping between Entities and DTOs to avoid overhead and maintain full control.
7.  **Exception Layer**: Centralized error handling using `@RestControllerAdvice`.

*Note: Project Lombok is used to reduce boilerplate code (getters, setters, constructors, builders, logging) while maintaining clean and readable code.*

## Database Schema Initialization
Unlike JDBC with JPA/Hibernate (`ddl-auto: update`), R2DBC does not automatically create tables by default. However, this project is configured with a `ConnectionFactoryInitializer` (see `R2dbcConfig.java`) that executes `src/main/resources/schema.sql` on startup. 

This ensures that the `TOUR_PACKAGE_TTS` table is created automatically if it doesn't already exist in your Oracle database.

## API Endpoints & Documentation

### Swagger UI
Once the application is running, you can access the interactive API documentation at:
- Swagger UI: `http://localhost:8080/swagger-ui.html` (Redirects to the WebFlux UI)
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

The API documentation provides detailed information about endpoints, request/response models, and allows for direct testing of the APIs.

### Create Tour Package
- **URL**: `POST /api/tours`
- **Body**:
  ```json
  {
    "name": "Grand Europe Tour",
    "description": "14 days tour across 5 countries",
    "location": "Europe",
    "price": 2500.00,
    "durationDays": 14,
    "availableSlots": 20
  }
  ```
- **Response**: `201 Created`

### Get Tour by ID
- **URL**: `GET /api/tours/{id}`
- **Response**: `200 OK`

### Get All Tours (Reactive Streaming)
- **URL**: `GET /api/tours`
- **Produces**: `application/json`
- **Response**: `200 OK` (Flux of Tour Packages)

### Batch Create Tour Packages
- **URL**: `POST /api/tours/batch`
- **Body**: (Array of Create objects)
  ```json
  [
    {
      "name": "Tour 1",
      "location": "Loc 1",
      "price": 100.0,
      "durationDays": 5,
      "availableSlots": 10
    },
    {
      "name": "Tour 2",
      "location": "Loc 2",
      "price": 200.0,
      "durationDays": 7,
      "availableSlots": 15
    }
  ]
  ```
- **Response**: `201 Created` (Flux of created packages)

### Update Tour
- **URL**: `PUT /api/tours/{id}`
- **Body**: (Similar to Create)
- **Response**: `200 OK`

### Delete Tour (Soft Delete)
- **URL**: `DELETE /api/tours/{id}`
- **Response**: `204 No Content`

## How to Run Locally

### Prerequisites
- Java 25 installed.
- Maven installed.
- Access to an Oracle Database (or use the provided connection details).

### Steps
1.  Clone the repository.
2.  Update `src/main/resources/application.yml` with your local Oracle credentials if different from the provided ones.
3.  Run the application using Maven:
    ```bash
    mvn spring-boot:run
    ```

### Batch Data Generation
A specialized integration test `BatchCreationTest.java` is available to seed the database with 1000 records. This test is **disabled by default** using `@Disabled` to prevent accidental data bloat during standard builds.

To run the batch generation manually:
1. Open `src/test/java/com/kuoni/tumlare/tourpackageservice/BatchCreationTest.java`.
2. Comment out or remove the `@Disabled` annotation.
3. Run the test via your IDE or use Maven:
   ```bash
   mvn test -Dtest=BatchCreationTest
   ```
Note: Remember to revert the `@Disabled` annotation after use to keep standard builds clean.

## Oracle DB Setup
Ensure the following table exists in your Oracle database:

```sql
CREATE TABLE TOUR_PACKAGE_TTS (
    ID NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NAME VARCHAR2(255) NOT NULL,
    DESCRIPTION VARCHAR2(1000),
    LOCATION VARCHAR2(255) NOT NULL,
    PRICE NUMBER(19, 2) NOT NULL,
    DURATION_DAYS NUMBER(10),
    AVAILABLE_SLOTS NUMBER(10),
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ACTIVE NUMBER(1) DEFAULT 1
);
```

## R2DBC Explanation
R2DBC (Reactive Relational Database Connectivity) brings reactive programming to relational databases. Unlike JDBC, which is blocking, R2DBC allows for fully non-blocking database operations, which is essential for maximizing resource utilization in high-concurrency environments.

## Reactive vs Blocking
- **Blocking (Servlet API)**: Each request is tied to a single thread. When performing I/O (like a DB query), the thread waits (blocks) until the operation completes, wasting resources.
- **Reactive (WebFlux)**: Requests are handled by a small number of event-loop threads. I/O operations are asynchronous. When a DB query is made, the thread is released to handle other requests, and a callback is triggered when the data is ready. This leads to much higher throughput and resilience.
