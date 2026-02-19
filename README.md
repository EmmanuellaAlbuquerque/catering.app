# Catering App

System for searching, hiring, and managing catering services for events.

## Technologies

*   Java 21
*   Spring Boot (Web, Data JPA, Validation)
*   MySQL
*   Flyway
*   JSP & JSTL
*   Maven

## Prerequisites

*   Java JDK 21
*   MySQL Server

## Configuration

1.  **Database:**
    *   Create a MySQL database named `catering_db`.
    *   Check `src/main/resources/application.properties`. Default configuration:
        *   URL: `jdbc:mysql://localhost:3306/catering_db`
        *   User: `root`
        *   Password: (empty)
    *   Adjust credentials in `application.properties` as needed.

## Key Features

*   **Event Provider Management:** Registration, editing, and viewing of providers.
*   **Image Upload:** Support for uploading images related to providers.
