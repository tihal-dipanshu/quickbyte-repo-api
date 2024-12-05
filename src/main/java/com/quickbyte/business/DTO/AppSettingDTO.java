package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppSettingDTO {

    private Integer settingId;
    private Integer ownerId;
    private String businessName;
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private String slogan;

    // Default constructor
    public AppSettingDTO() {}

    // Constructor with all fields
    public AppSettingDTO(int settingId, int ownerId, String businessName, String logoUrl,
                         String primaryColor, String secondaryColor, String slogan) {
        this.settingId = settingId;
        this.ownerId = ownerId;
        this.businessName = businessName;
        this.logoUrl = logoUrl;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.slogan = slogan;
    }

    @Override
    public String toString() {
        return "AppSettingDTO{" +
                "settingId=" + settingId +
                ", ownerId=" + ownerId +
                ", businessName='" + businessName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", primaryColor='" + primaryColor + '\'' +
                ", secondaryColor='" + secondaryColor + '\'' +
                ", slogan='" + slogan + '\'' +
                '}';
    }
}