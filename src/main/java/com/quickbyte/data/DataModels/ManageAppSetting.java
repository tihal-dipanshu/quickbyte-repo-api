package com.quickbyte.data.DataModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AppSettings")
@Getter
@Setter
public class ManageAppSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SettingId")
    private Integer settingId;

    @ManyToOne
    @JoinColumn(name = "OwnerId", nullable = true)
    private BusinessOwner owner;

    @Column(name = "BusinessName", length = 100)
    private String businessName;

    @Column(name = "LogoUrl", length = 255)
    private String logoUrl;

    @Column(name = "PrimaryColor", length = 7)
    private String primaryColor;

    @Column(name = "SecondaryColor", length = 7)
    private String secondaryColor;

    @Column(name = "Slogan", length = 255)
    private String slogan;
}