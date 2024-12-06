package com.quickbyte.data.DataModels;

import com.quickbyte.common.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = true)
    private User user;

    @Column(name = "OrderDate", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "TotalAmount", nullable = false)
    private BigDecimal totalAmount;

    @NonNull
    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "EstimatedPickupTime")
    private LocalDateTime estimatedPickupTime;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}