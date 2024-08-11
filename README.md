# Task Management System

This repository contains a simple Task Management System developed using Java 17+, Spring Boot, and Spring Security. The system allows users to create, edit, delete, and view tasks. It also supports user authentication and authorization using JWT tokens. The application is built with a focus on ease of use, robust error handling, and comprehensive documentation.

## Features

- **User Authentication & Authorization**: Secure login with JWT tokens.
- **Task Management**: 
  - Create, edit, delete, and view tasks.
  - Assign tasks to other users.
  - Change task statuses (e.g., "Pending", "In Progress", "Completed").
  - Set task priority (e.g., "High", "Medium", "Low").
- **Commenting System**: Leave comments on tasks.
- **Filtering & Pagination**: Retrieve tasks with support for filtering by author, assignee, and status, as well as pagination.
- **Error Handling**: Comprehensive error messages and validation.
- **Swagger UI**: API documentation with Swagger UI, available without authentication for easy access.

## Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **JWT for Authentication**
- **MySQL** 
- **Docker Compose** for setting up the development environment
- **OpenAPI & Swagger** for API documentation

## Prerequisites

- **Java 17+**
- **Maven**
- **Docker & Docker Compose**
- **MySQL**

## Installation & Setup

1. **Clone the repository**:
    ```bash
    git clone https://github.com/Stas1414/Effective_Mobile_TZ.git
    cd Effective_Mobile_TZ
    ```

2. **Build the application**:
    ```bash
    mvn clean install
    ```

3. **Build the Docker image**:
    ```bash
    docker build -t task-management .
    ```

4. **Run the application using Docker Compose**:
    ```bash
    docker-compose up --build
    ```

5. **Access the application**:
   - API Base URL: `http://localhost:8080/api`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

## API Endpoints

The API provides various endpoints for managing tasks, users, and comments. Below are some of the key endpoints:

### Authentication

- `POST /auth/register`: Register a new user.
- `POST /auth/login`: Login and receive a JWT token.

### Tasks 

- `POST /api/tasks/create`: Create a new task.
- `PATCH /api/tasks/{taskId}`: Update an existing task.
- `DELETE /api/tasks/{taskId}`: Delete a specific task.

### Comments 

- `POST /api/comments/{taskId}`: Create a comment for a specific task.
- `GET /api/comments/{taskId}`: Retrieve all comments from a specific task.
- `PATCH /api/comments/{commentId}`: Update a specific comment.
- `DELETE /api/comments/{commentId}`: Delete a specific comment.

### Users 

- `GET /users/implementation`: Retrieve all tasks assigned for implementation.
- `GET /users/created`: Retrieve all tasks created by the user.
- `GET /users/{userId}/implementation`: Retrieve tasks assigned to a specific user for implementation.
- `GET /users/{userId}/created`: Retrieve tasks created by a specific user.
- `PATCH /users/{taskId}`: Change the status of a specific task.
## Running Tests

To run the test suite:

```bash
mvn test
