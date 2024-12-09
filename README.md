# QuickByte API - README

This README provides instructions on how to run the QuickByte Spring Boot application and test it using Postman.

---

## Prerequisites

- **Java Development Kit (JDK)** 11 or later
- **IntelliJ IDEA**
- **Postman**

---

## Running the Application

1. Open the project in **IntelliJ IDEA**.
2. Locate the main application class (usually annotated with `@SpringBootApplication`).
3. Right-click on the main class and select **"Run"** or **"Debug"**.
4. The application will start, and you should see log output in the console.

---

## Testing with Postman

1. Open **Postman**.
2. Import the QuickByte API collection (if available) or create a new collection.
3. Set the base URL to `http://localhost:8080` (or the port your application is running on).

---

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
