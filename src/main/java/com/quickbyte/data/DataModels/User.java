package com.quickbyte.data.DataModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer userId;

    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "Email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String xxx;

    @Column(name = "FirstName", length = 50)
    private String firstName;

    @Column(name = "LastName", length = 50)
    private String lastName;

    @Column(name = "PhoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "IsActive")
    private Boolean isActive;

    @Column(name = "LoyaltyPoints", nullable = false)
    private int loyaltyPoints;

    @Column(name = "CardNumber", length = 19)
    private String cardNumber;

    @Column(name = "ExpiryMonth")
    private Integer expiryMonth;

    @Column(name = "ExpiryYear")
    private Integer expiryYear;

    @Column(name = "IsDefaultCard")
    private Boolean isDefaultCard;

    @Column(name = "CVV")
    private Integer CVV;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (isActive == null) {
            isActive = true;
        }
    }

    public User(String username, String email, String xxx, String firstName, String lastName, String phoneNumber, int loyaltyPoints, String cardNumber, int expiryMonth, int expiryYear, Boolean isDefaultCard, Integer CVV) {
        this.username = username;
        this.email = email;
        this.xxx = xxx;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isActive = true;
        this.loyaltyPoints = loyaltyPoints;
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.CVV = CVV;
        this.isDefaultCard = isDefaultCard;
    }
}