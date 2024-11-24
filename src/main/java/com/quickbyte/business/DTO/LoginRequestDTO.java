package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDTO {
    // Getters and setters
    private String username;
    private String passwordHash;

    // Default constructor
    public LoginRequestDTO() {}

    // Constructor with all fields
    public LoginRequestDTO(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

}