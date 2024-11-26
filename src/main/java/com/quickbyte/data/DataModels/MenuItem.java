package com.quickbyte.data.DataModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "MenuItems")
public class MenuItem {

    // Getters and Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemId")
    private int itemId;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "CategoryId", nullable = true)
    private MenuCategory category;

    @Setter
    @Getter
    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Setter
    @Getter
    @Column(name = "Description")
    private String description;

    @Setter
    @Getter
    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Setter
    @Getter
    @Column(name = "ImageUrl")
    private String imageUrl;

    @Column(name = "IsAvailable", nullable = false)
    private boolean isAvailable;

    @Setter
    @Getter
    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

}