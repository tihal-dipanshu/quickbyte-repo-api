package com.quickbyte.api.controllers;

import com.quickbyte.business.DTO.LoginRequestDTO;
import com.quickbyte.business.DTO.UserCreationRequestDTO;
import com.quickbyte.business.DTO.UserDTO;
import com.quickbyte.business.IService.IUserService;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import com.quickbyte.common.exceptions.InvalidCredentialsException;
import com.quickbyte.common.exceptions.UserNotFoundException;
import com.quickbyte.common.exceptions.UserAlreadyExistsException;
import com.quickbyte.common.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final IUserService userService;

    @Autowired
    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            UserDTO user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("User not found: " + e.getMessage()));
        }
    }

    /*@GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        List<UserDTO> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }*/

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable int userId) {
        try {
            UserDTO user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("User not found: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreationRequestDTO newUser) {
        try {
            UserDTO createdUser = userService.createUser(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponseCustom("User already exists: " + e.getMessage()));
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable int userId, @RequestBody UserDTO updateUser) {
        try {
            UserDTO updatedUser = userService.updateUser(userId, updateUser);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("User not found: " + e.getMessage()));
        } catch (InvalidInputException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("User not found: " + e.getMessage()));
        }
    }

    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> isUsernameTaken(@PathVariable String username) {
        boolean isTaken = userService.isUsernameTaken(username);
        return ResponseEntity.ok(isTaken);
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> isEmailTaken(@PathVariable String email) {
        boolean isTaken = userService.isEmailTaken(email);
        return ResponseEntity.ok(isTaken);
    }

    @GetMapping("/search") // http://localhost:8080/api/users/search?searchTerm=t
    public ResponseEntity<?> searchUsers(@RequestParam String searchTerm) {
        List<UserDTO> users = userService.searchUsers(searchTerm);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        try {
            UserDTO userDTO = userService.loginUser(loginRequest.getUsername(), loginRequest.getPasswordHash());
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponseCustom("User not found"));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseCustom("Invalid credentials"));
        }
    }
}
