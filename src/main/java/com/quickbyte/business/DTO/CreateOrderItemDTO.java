package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderItemDTO {
    private Integer menuItemId;
    private Integer quantity;

    public CreateOrderItemDTO() {}

    public CreateOrderItemDTO(Integer menuItemId, Integer quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
                ", menuItemId=" + menuItemId +
                ", quantity=" + quantity +
                '}';
    }
}