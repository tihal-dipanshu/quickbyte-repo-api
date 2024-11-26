package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByCategoryCategoryIdAndIsAvailableTrue(int categoryId);

    List<MenuItem> findByNameContainingIgnoreCase(String query);

    @Query("SELECT mi FROM MenuItem mi " +
            "JOIN OrderItem oi ON mi.itemId = oi.menuItem.itemId " +
            "GROUP BY mi.itemId " +
            "ORDER BY COUNT(oi.menuItem.itemId) DESC")
    List<MenuItem> findPopularItems();

    boolean existsById(int itemId);

    @Query("SELECT mi FROM MenuItem mi WHERE mi.itemId = :itemId")
    MenuItem findByItemId(int itemId);
}