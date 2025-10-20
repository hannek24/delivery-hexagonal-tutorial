# delivery-hexagonal-tutorial
Illustrating the benefits of Hexagonal design

## Description
This is a Spring Boot application demonstrating hexagonal architecture principles.

## Features
- REST API with HelloWorldController
- Maven build configuration
- JUnit 5 tests with Spring Boot Test

## Requirements
- Java 17 or higher
- Maven 3.6 or higher

## Building the Application
```bash
mvn clean install
```

## Running the Application
```bash
mvn spring-boot:run
```

The application will start on port 8080.

## Testing the Application
Run all tests:
```bash
mvn test
```

## API Endpoints
- `GET /hello` - Returns "Hello World!"

### Example
```bash
curl http://localhost:8080/hello
```

Response:
```
Hello World!
```
