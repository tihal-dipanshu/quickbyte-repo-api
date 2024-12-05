package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessOwnerDTO {

    private String username;
    private String email;
    private String passwordHash; // Consider excluding this in real-world scenarios for security reasons
    private String contactNumber;

    // Constructors
    public CreateBusinessOwnerDTO() {}

    public CreateBusinessOwnerDTO(String username, String email, String passwordHash,
                            String contactNumber) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.contactNumber = contactNumber;
    }
}