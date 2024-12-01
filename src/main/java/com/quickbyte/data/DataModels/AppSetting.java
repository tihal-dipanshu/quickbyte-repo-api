package com.quickbyte.data.DataModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AppSettings")
public class AppSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SettingId")
    @Getter @Setter private int settingId;

    @Column(name = "OwnerId")
    @Getter @Setter private Integer ownerId;

    @Column(name = "LogoUrl", length = 255)
    @Getter @Setter private String logoUrl;

    @Column(name = "PrimaryColor", length = 7)
    @Getter @Setter private String primaryColor;

    @Column(name = "SecondaryColor", length = 7)
    @Getter @Setter private String secondaryColor;

    @Column(name = "Slogan", length = 255)
    @Getter @Setter private String slogan;
}