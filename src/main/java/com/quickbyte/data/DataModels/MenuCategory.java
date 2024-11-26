package com.quickbyte.data.DataModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "MenuCategories")
public class MenuCategory {
    // Getters and Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;

    @Setter
    @Getter
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Setter
    @Getter
    @Column(name = "Description")
    private String description;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive;

    @Setter
    @Getter
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menuItems;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}