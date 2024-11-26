package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.MenuCategory;
import com.quickbyte.data.IRepositories.IMenuCategoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

import java.util.List;

@Repository
public class MenuCategoryRepository extends SimpleJpaRepository<MenuCategory, Integer> implements IMenuCategoryRepository {

    private final EntityManager entityManager;

    public MenuCategoryRepository(EntityManager entityManager) {
        super(MenuCategory.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<MenuCategory> findByIsActiveTrue() {
        return entityManager.createQuery(
                        "SELECT mc FROM MenuCategory mc WHERE mc.isActive = true", MenuCategory.class)
                .getResultList();
    }

    @Override
    public boolean existsById(int categoryId) {
        Long count = entityManager.createQuery(
                        "SELECT COUNT(mc) FROM MenuCategory mc WHERE mc.categoryId = :categoryId", Long.class)
                .setParameter("categoryId", categoryId)
                .getSingleResult();
        return count > 0;
    }

    // You can add more custom methods here if needed
}