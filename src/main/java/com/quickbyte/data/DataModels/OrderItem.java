package com.quickbyte.data.DataModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "OrderItems")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderItemId")
    private int orderItemId;

    @ManyToOne
    @JoinColumn(name = "OrderId", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ItemId", nullable = false)
    private MenuItem menuItem;

    @Column(name = "Quantity", nullable = false)
    private int quantity;

    @NonNull
    @Column(name = "UnitPrice", nullable = false)
    private BigDecimal unitPrice = BigDecimal.ZERO;

    @NonNull
    @Column(name = "Subtotal", nullable = false)
    private BigDecimal subtotal = BigDecimal.ZERO;

}