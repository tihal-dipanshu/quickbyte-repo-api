package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.BusinessOwner;
import com.quickbyte.data.IRepositories.IBusinessOwnerRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@Repository
public class BusinessOwnerRepository extends SimpleJpaRepository<BusinessOwner, Integer> implements IBusinessOwnerRepository {

    private final EntityManager entityManager;

    public BusinessOwnerRepository(EntityManager entityManager) {
        super(BusinessOwner.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<BusinessOwner> findFirstByOrderByOwnerIdAsc() {
        return entityManager.createQuery("SELECT bo FROM BusinessOwner bo", BusinessOwner.class)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst();
    }
}
