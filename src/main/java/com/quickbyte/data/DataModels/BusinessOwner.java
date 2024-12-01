package com.quickbyte.data.DataModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
@Table(name = "BusinessOwners")
public class BusinessOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OwnerId")
    @Getter @Setter private int ownerId;

    @Column(name = "Username", nullable = false, length = 50, unique = true)
    @Getter @Setter private String username;

    @Column(name = "Email", nullable = false, length = 100, unique = true)
    @Getter @Setter private String email;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    @Getter @Setter private String passwordHash;

    @Column(name = "BusinessName", nullable = false, length = 100)
    @Getter @Setter private String businessName;

    @Column(name = "ContactNumber", length = 20)
    @Getter @Setter private String contactNumber;

    @Column(name = "CreatedAt")
    @Getter @Setter private Timestamp createdAt;
}
