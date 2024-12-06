package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

public class CreateMenuItemDTO {
    @Setter
    @Getter
    private Integer categoryId;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String description;
    @Setter
    @Getter
    private BigDecimal price;
    @Setter
    @Getter
    private String imageUrl;

    public CreateMenuItemDTO() {}

    public CreateMenuItemDTO(int categoryId, String name, String description, BigDecimal price, String imageUrl) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CreateMenuItemDTO{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}