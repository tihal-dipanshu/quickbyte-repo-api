package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class MenuItemRepository extends SimpleJpaRepository<MenuItem, Integer> implements IMenuItemRepository {

    private final EntityManager entityManager;

    public MenuItemRepository(EntityManager entityManager) {
        super(MenuItem.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<MenuItem> findByCategoryCategoryIdAndIsAvailableTrue(int categoryId) {
        return entityManager.createQuery(
                        "SELECT mi FROM MenuItem mi WHERE mi.category.categoryId = :categoryId AND mi.isAvailable = true",
                        MenuItem.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

    @Override
    public List<MenuItem> findByNameContainingIgnoreCase(String query) {
        return entityManager.createQuery(
                        "SELECT mi FROM MenuItem mi WHERE LOWER(mi.name) LIKE LOWER(CONCAT('%', :query, '%'))",
                        MenuItem.class)
                .setParameter("query", query)
                .getResultList();
    }

    @Override
    public List<MenuItem> findPopularItems() {
        return entityManager.createQuery(
                        "SELECT mi FROM MenuItem mi " +
                                "JOIN OrderItem oi ON mi.itemId = oi.menuItem.itemId " +
                                "GROUP BY mi.itemId " +
                                "ORDER BY COUNT(oi.menuItem.itemId) DESC",
                        MenuItem.class)
                .setMaxResults(10)
                .getResultList();
    }

    @Override
    public boolean existsById(int itemId) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(mi) FROM MenuItem mi WHERE mi.itemId = :itemId", Long.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public MenuItem findByItemId(int itemId) {
        return entityManager.createQuery(
                        "SELECT mi FROM MenuItem mi WHERE mi.itemId = :itemId", MenuItem.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    @Override
    public List<MenuItem> getAllItems() {
        return entityManager.createQuery(
                        "SELECT mi FROM MenuItem mi", MenuItem.class)
                .getResultList();
    }
}