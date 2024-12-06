package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.IRepositories.IOrderRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderRepository extends SimpleJpaRepository<Order, Integer> implements IOrderRepository {

    private final EntityManager entityManager;

    public OrderRepository(EntityManager entityManager) {
        super(Order.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> findByUser_UserId(Integer userId) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.user.userId = :userId", Order.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public void updateStatusByOrderId(Integer orderId, String status) {
        entityManager.createQuery(
                        "UPDATE Order o SET o.status = :status WHERE o.orderId = :orderId")
                .setParameter("status", status)
                .setParameter("orderId", orderId)
                .executeUpdate();
    }

    @Override
    public List<Order> findOrdersByStatus(String status) {
        return entityManager.createQuery(
                        "SELECT o FROM Order o WHERE o.status = :status", Order.class)
                .setParameter("status", status)
                .getResultList();
    }
}