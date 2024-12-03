package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserDTO {
    // Getters and setters
    private Integer userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private Boolean isActive;
    private int LoyaltyPoints;
    private String CardNumber;
    private int ExpiryMonth;
    private int ExpiryYear;
    private int CVV;
    private Boolean IsDefaultCard;

    // Default constructor
    public UserDTO() {}

    // Constructor with all fields
    public UserDTO(Integer userId, String username, String email, String firstName, String lastName,
                   String phoneNumber, LocalDateTime createdAt, Boolean isActive, int LoyaltyPoints, String CardNumber, int ExpiryMonth, int ExpiryYear, Boolean IsDefaultCard, int CVV) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.isActive = isActive;
        this.LoyaltyPoints = LoyaltyPoints;
        this.CardNumber = CardNumber;
        this.ExpiryMonth = ExpiryMonth;
        this.ExpiryYear = ExpiryYear;
        this.CVV = CVV;
        this.IsDefaultCard = IsDefaultCard;
    }

    // toString method for easy logging/debugging
    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                ", LoyaltyPoints=" + LoyaltyPoints +
                ", CardNumber='" + CardNumber + '\'' +
                ", ExpiryMonth=" + ExpiryMonth +
                ", ExpiryYear=" + ExpiryYear +
                ", CVV=" + CVV +
                ", IsDefaultCard=" + IsDefaultCard +
                '}';
    }
}