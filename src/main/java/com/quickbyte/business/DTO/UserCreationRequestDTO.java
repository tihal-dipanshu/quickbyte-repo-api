package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCreationRequestDTO {
    // Getters and setters
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    // Default constructor
    public UserCreationRequestDTO() {}

    // Constructor with all fields
    public UserCreationRequestDTO(String username, String email, String password,
                                  String firstName, String lastName, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

}
