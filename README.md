# delivery-hexagonal-tutorial
Illustrating the benefits of Hexagonal design using a small "purchases" service

## Description
This is a Spring Boot example application demonstrating hexagonal (ports & adapters) architecture principles using a simple in-memory "purchases" domain. The project focuses on separating the domain (core) from the adapters (web, persistence, mappers) so you can see how incoming and outgoing ports are wired to implementations.

## Features
- REST API for creating and retrieving purchase requests
- Clear separation between application, domain, and infra (Hexagonal architecture)
- MapStruct for DTO/entity/domain mapping
- In-memory repository (simple adapter) to keep the example lightweight
- Maven build configuration
- JUnit 5 tests with Spring Boot Test (example tests included)

## Requirements
- Java 17 or higher
- Maven 3.6 or higher

## Building the Application
Run a full build (compiles generated MapStruct sources):

```bash
mvn clean install
```

## Running the Application
Start the Spring Boot application:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default.

## Testing the Application
Run all tests:

```bash
mvn test
```

## API Endpoints
This example exposes two primary endpoints that exercise the hexagonal ports and adapters:

- `POST /purchase-requests` - Create a new purchase request
  - Request body: JSON representation of a PurchaseRequestDto
  - Response body: PurchaseResponseDto (contains created id and any calculated values)

- `GET /purchases` - Retrieve all stored purchase requests
  - Response body: JSON object with a `purchases` array containing PurchaseRequestDto entries

### Example: Create a purchase request
Example request body (fields vary slightly depending on the generated DTOs in `src/main/java`):

```bash
curl -X POST http://localhost:8080/purchase-requests \
  -H "Content-Type: application/json" \
  -d '{"product": "pizza mozerella","sku": "2001","quantity": 10}'
```

Example response (HTTP 200):

```json
{
  "id": "dab2e0a0-9d62-427b-9148-5474404cd6a7",
  "price": 499.90
}
```

### Example: Get stored purchases

```bash
curl http://localhost:8080/purchases
```

Example response (HTTP 200):

```json
{
  "purchases": [
    {
      "id": "53d974d1-8ea7-4d6e-a531-39d04da49c28",
      "product": "pizza mozerella",
      "sku": "2001",
      "quantity": 10,
      "price": 499.90
    },
    {
      "id": "7418331b-ba50-4ccd-9652-e0d1f96975a2",
      "product": "pizza mozerella",
      "sku": "2001",
      "quantity": 10,
      "price": 499.90
    }
  ]
}
```

Note: The exact JSON shape of DTOs is defined in `src/main/java/com/tutorial/purchases/application/models` and mapped to domain models using MapStruct. The repository is an in-memory adapter (`PurchaseRequestRepository`) for demo purposes only.

## Why this project
The goal is to demonstrate how to structure code with hexagonal architecture:
- Domain logic and interfaces (ports) live in `domain/` and are independent from Spring or persistence.
- Adapters implementing ports live in `infra/` and `application/` (controllers, mappers).
- This makes the core domain easier to test and swap adapters (e.g., replace in-memory repo with a DB) without changing domain code.

## Next steps / ideas (non-exhaustive)
A few ways to extend the tutorial to illustrate additional hexagonal concerns:
- Add persistence adapter using a real database (JPA/H2) and a migration script
- Add an external payment adapter as an outgoing port (mockable in tests)
- Introduce more complex domain services (discounts, promotions, inventory checks)
- Add an event publisher adapter to publish purchase-created events


## Troubleshooting
- If MapStruct generated sources are missing, run `mvn clean install` to force generation.
- The example uses an in-memory repository; restarting the app clears stored purchases.


---

This README reflects the current code in this repository which implements a small purchases service to showcase hexagonal design.
