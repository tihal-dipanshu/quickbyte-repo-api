package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository extends SimpleJpaRepository<User, Integer> implements IUserRepository {

    private final EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        super(User.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }

    @Override
    public boolean existsByUsername(String username) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
        return count > 0;
    }

//    @Override
//    public List<User> findByUserRole(String role) {
//        return entityManager.createQuery(
//                        "SELECT u FROM User u WHERE u.userRole = :role", User.class)
//                .setParameter("role", role)
//                .getResultList();
//    }

    @Override
    public List<User> findByUsernameContainingOrEmailContainingOrFirstNameContainingOrLastNameContaining(
            String username, String email, String firstName, String lastName) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE " +
                                "LOWER(u.username) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                                "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                                "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
                                "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%'))",
                        User.class)
                .setParameter("search", username)
                .getResultList();
    }
}