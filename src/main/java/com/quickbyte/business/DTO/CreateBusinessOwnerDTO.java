package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBusinessOwnerDTO {

    private String username;
    private String email;
    private String passwordHash;
    private String contactNumber;

    public CreateBusinessOwnerDTO() {}

    public CreateBusinessOwnerDTO(String username, String email, String passwordHash,
                            String contactNumber) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "CreateBusinessOwnerDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}