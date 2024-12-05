package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.BusinessOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBusinessOwnerRepository extends JpaRepository<BusinessOwner, Integer> {
    Optional<BusinessOwner> findFirstByOrderByOwnerIdAsc();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}