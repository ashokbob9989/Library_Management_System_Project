# Library Management System

## Overview

This Library Management System is built using Java and Spring Boot, designed to efficiently manage books, authors, and publishers within a library. The project leverages an Oracle database for data storage and utilizes JDBC Template for database connectivity.

## Project Structure

### Models

**Definition:** Models represent the data structure of the application. They define the attributes and relationships between different entities.

- **Book**: Contains attributes like `id` (primary key), `name`, `price`, etc., to represent book details.
- **Author**: Contains attributes like `id` and `name` (composite key), `contact`, etc. This ensures unique identification in conjunction with the `id` of the Book.
- **Publisher**: Contains attributes like `id` and `name` (composite key), `contact`, etc. This also ensures unique identification in conjunction with the `id` of the Book.

### Repositories

**Definition:** Repositories are responsible for data access and interaction with the database. They provide methods for performing CRUD (Create, Read, Update, Delete) operations.

- **BookRepository**: Interface for accessing and managing `Book` entities in the database.
- **AuthorRepository**: Interface for accessing and managing `Author` entities.
- **PublisherRepository**: Interface for accessing and managing `Publisher` entities.

Each repository abstracts the underlying database operations, ensuring that the data layer is isolated from the rest of the application, promoting better separation of concerns.

### Services

**Definition:** Services encapsulate the business logic of the application. They act as an intermediary between the controllers and repositories, handling data manipulation and processing.

- **BookService**: Contains methods to manage business operations related to `Book` entities, such as validation and transaction handling.
- **AuthorService**: Manages operations related to `Author` entities, including data processing and business rules.
- **PublisherService**: Handles business logic for `Publisher` entities.

This separation facilitates easier testing and maintenance.

### Controllers

**Definition:** Controllers handle incoming HTTP requests and return responses. They define the API endpoints and manage the interaction between the user interface and the application’s business logic.

- **BookController**: Exposes RESTful endpoints for operations related to `Book` entities, including:
    - **POST**: Create a new book.
    - **GET**: Retrieve books (all, by ID, or by name).
    - **PUT**: Update an existing book.
    - **PATCH**: Partially update a book.
    - **DELETE**: Remove a book.

- **AuthorController**: Similar operations for managing `Author` entities.
- **PublisherController**: Similar operations for managing `Publisher` entities.

## Database Connectivity

The project connects to an Oracle database using JDBC Template. This allows efficient execution of SQL queries and provides an easy-to-use interface for database operations. Configuration details are specified in the application properties file.

## Swagger Integration

Swagger is integrated into the project to provide interactive API documentation. This allows developers to explore the available endpoints and understand the expected request/response formats without needing additional tools.

## Dependency Management

Dependencies are managed via `pom.xml`, which includes necessary libraries for Spring Boot, Oracle JDBC, and Swagger. This ensures that the project is modular and easy to maintain.

## Spring Boot Annotations

Spring Boot is used to simplify the development process through annotations like `@RestController`, `@Service`, `@Repository`, and `@Autowired`. These annotations enable auto-configuration and dependency injection, making it easier to create standalone applications with minimal setup.

## Getting Started

1. Clone the repository.
2. Set up your Oracle database and update the connection details in `application.properties`.
3. Run the application using your IDE or the command line.
4. Access the Swagger UI at `http://localhost:8080/swagger-ui/index.html#/` to explore the API.

## Conclusion

This Library Management System is designed to be a robust, scalable solution for managing library resources. Its architecture supports easy modifications and enhancements, making it a great foundation for future development.

For further information or contributions, please refer to the contributing guidelines in the repository.