package com.quickbyte.business.DTO;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Integer orderId;
    private Integer userId;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;
    private LocalDateTime estimatedPickupTime;
    private List<OrderItemDTO> orderItems;

    public OrderDTO() {}

    public OrderDTO(Integer orderId, Integer userId, LocalDateTime orderDate, BigDecimal totalAmount,
                    String status, LocalDateTime estimatedPickupTime, List<OrderItemDTO> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.estimatedPickupTime = estimatedPickupTime;
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                ", estimatedPickupTime=" + estimatedPickupTime +
                ", orderItems=" + orderItems +
                '}';
    }
}