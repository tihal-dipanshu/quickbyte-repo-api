package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.IRepositories.IManageMenuItemRepository;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

@Repository
@Primary
public class ManageMenuItemRepository extends SimpleJpaRepository<MenuItem, Integer> implements IManageMenuItemRepository {

    private final EntityManager entityManager;

    public ManageMenuItemRepository(EntityManager entityManager) {
        super(MenuItem.class, entityManager);
        this.entityManager = entityManager;
    }
}