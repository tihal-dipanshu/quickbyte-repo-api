package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessOwnerDTO {

    private Integer ownerId;
    private String username;
    private String email;
    private String passwordHash;
    private String contactNumber;
    private java.sql.Timestamp createdAt;

    public BusinessOwnerDTO() {}

    public BusinessOwnerDTO(Integer ownerId, String username, String email, String passwordHash,
                            String contactNumber, java.sql.Timestamp createdAt) {
        this.ownerId = ownerId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.contactNumber = contactNumber;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "BusinessOwnerDTO{" +
                "ownerId=" + ownerId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}