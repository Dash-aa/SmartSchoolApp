# SmartSchool Project Architecture

## Overview

The SmartSchool project is built using a layered architecture approach. Each layer has its own responsibility, which simplifies system maintenance, testing, and scalability.

Main project packages:
- `controller` — handles HTTP requests and interacts with the web interface;
- `service` — implements business logic;
- `repository` — manages data access;
- `dto` — objects for transferring data from forms;
- `model` — domain models of the system.

## Architectural Decisions

### Use of MVC Pattern

The project follows the MVC approach:
- **Model** — classes `User`, `Announcement`, `Role`;
- **View** — HTML templates;
- **Controller** — `MainController`, which processes requests and returns views.

This approach allows separation of request processing logic from data presentation.

### Separation of Business Logic into Services

Authentication, registration, and announcement logic are not implemented directly in the controller. Instead, separate services are used:
- `AuthService`
- `AnnouncementService`

This decision simplifies testing, code reuse, and project maintenance.

### Separation of Data Access

User data is managed through the `UserRepository` interface and its implementation `InMemoryUserRepository`.

This allows:
- separating business logic from data storage implementation;
- replacing in-memory storage with a database in the future without modifying services.

### Use of DTO for Forms

The following DTO classes are used for handling user input:
- `LoginForm`
- `RegisterForm`

This prevents direct usage of domain models in forms and allows separate validation of input data.

## Advantages of the Chosen Architecture

- clear separation of responsibilities between components;
- easier testing of individual parts of the system;
- improved maintainability and scalability;
- more convenient project documentation.