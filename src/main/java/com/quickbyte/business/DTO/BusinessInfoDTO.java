package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

public class BusinessInfoDTO {

    @Getter @Setter private int ownerId;
    @Getter @Setter private String businessName;
    @Getter @Setter private String logoUrl;
    @Getter @Setter private String slogan;
    @Getter @Setter private String primaryColor;
    @Getter @Setter private String secondaryColor;

    public BusinessInfoDTO() {}

    public BusinessInfoDTO(String businessName, String logoUrl, String slogan,
                           String primaryColor, String secondaryColor, int ownerId) {
        this.ownerId = ownerId;
        this.businessName = businessName;
        this.logoUrl = logoUrl;
        this.slogan = slogan;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;

    }
}