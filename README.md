# QuickByte API - README

This README provides instructions on running the QuickByte Spring Boot application, testing it with Postman, and understanding its deployment pipeline to Heroku and database setup on Amazon RDS.

---

## Overview

QuickByte’s API is a Spring Boot application that automatically builds, tests, and deploys to Heroku whenever changes are pushed to the main branch. The deployment process is handled through GitHub Actions, enabling a seamless CI/CD pipeline. The backend database is hosted on Amazon RDS using MySQL, ensuring scalability and reliability.

---

## Prerequisites

- **Java Development Kit (JDK)** 11 or later
- **IntelliJ IDEA**
- **Postman**
- **Heroku CLI** (if deploying locally)
- **GitHub Repository Secrets**:
  - `HEROKU_API_KEY`: Your Heroku API key
  - `HEROKU_APP_NAME`: Name of your Heroku app
  - `HEROKU_EMAIL`: Your Heroku account email

---

## Running the Application Locally

1. Open the project in **IntelliJ IDEA**.
2. Locate the main application class (annotated with `@SpringBootApplication`).
3. Right-click on the main class and select **"Run"** or **"Debug"**.
4. The application will start, and you should see logs in the console.

---

## Testing with Postman

1. Open **Postman**.
2. Set the base URL to `http://localhost:8080` (or the port your application is running on).
3. Use the following example requests:

### Example Requests

#### Create User
- **Method**: `POST`  
- **URL**: `http://localhost:8080/api/users`  
- **Body (raw JSON)**:
  ```json
  {
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "1234567890"
  }
