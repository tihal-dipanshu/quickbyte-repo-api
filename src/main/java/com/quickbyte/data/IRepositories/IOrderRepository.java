package com.quickbyte.data.IRepositories;

import com.quickbyte.data.DataModels.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser_UserId(Integer userId);
    List<Order> findOrdersByStatus(String status);

    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.orderId = :orderId")
    void updateStatusByOrderId(@Param("orderId") Integer orderId, @Param("status") String status);
}