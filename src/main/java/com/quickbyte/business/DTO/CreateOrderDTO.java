package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateOrderDTO {
    private Integer userId;
    private List<CreateOrderItemDTO> orderItems;

    public CreateOrderDTO() {}

    public CreateOrderDTO(Integer userId, List<CreateOrderItemDTO> orderItems) {
        this.userId = userId;
        this.orderItems = orderItems;
    }
}