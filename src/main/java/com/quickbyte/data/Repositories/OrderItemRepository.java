package com.quickbyte.data.Repositories;

import com.quickbyte.data.DataModels.OrderItem;
import com.quickbyte.data.IRepositories.IOrderItemRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import jakarta.persistence.EntityManager;
import java.util.List;

@Repository
public class OrderItemRepository extends SimpleJpaRepository<OrderItem, Integer> implements IOrderItemRepository {

    private final EntityManager entityManager;

    public OrderItemRepository(EntityManager entityManager) {
        super(OrderItem.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public List<OrderItem> findByOrder_OrderId(Integer orderId) {
        return entityManager.createQuery(
                        "SELECT oi FROM OrderItem oi WHERE oi.order.orderId = :orderId", OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

}