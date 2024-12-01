package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.AppSetting;
import com.quickbyte.data.IRepositories.IAppSettingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@Repository
public class AppSettingRepository extends SimpleJpaRepository<AppSetting, Integer> implements IAppSettingRepository {

    private final EntityManager entityManager;

    public AppSettingRepository(EntityManager entityManager) {
        super(AppSetting.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<AppSetting> findByOwnerId(int ownerId) {
        return entityManager.createQuery("SELECT a FROM AppSetting a WHERE a.ownerId = :ownerId", AppSetting.class)
                .setParameter("ownerId", ownerId)
                .getResultList()
                .stream()
                .findFirst();
    }
}
