package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.AppSetting;
import com.quickbyte.data.DataModels.ManageAppSetting;
import com.quickbyte.data.IRepositories.IAppSettingRepository;
import com.quickbyte.data.IRepositories.IManageAppSettingRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@Repository
@Primary
public class ManageAppSettingRepository extends SimpleJpaRepository<ManageAppSetting, Integer> implements IManageAppSettingRepository {

    private final EntityManager entityManager;

    public ManageAppSettingRepository(EntityManager entityManager) {
        super(ManageAppSetting.class, entityManager);
        this.entityManager = entityManager;
    }

    // Custom query methods can be added here if needed


    @Override
    public Optional<ManageAppSetting> findByOwner_OwnerId(Integer ownerId) {
        return entityManager.createQuery("SELECT a FROM ManageAppSetting a WHERE a.owner.ownerId = :ownerId", ManageAppSetting.class)
                .setParameter("ownerId", ownerId)
                .getResultList()
                .stream()
                .findFirst();
    }
}