package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.DataModels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    List<User> findAll();
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findByUsernameContainingOrEmailContainingOrFirstNameContainingOrLastNameContaining(
            String username, String email, String firstName, String lastName);
}