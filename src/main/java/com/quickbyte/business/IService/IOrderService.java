package com.quickbyte.business.IService;

import com.quickbyte.business.DTO.CreateOrderDTO;
import com.quickbyte.business.DTO.OrderDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Integer orderId);
    OrderDTO createOrderWithItems(CreateOrderDTO orderDTO);
    void deleteOrder(Integer orderId);
    OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO);
    void updateOrderStatus(Integer orderId, String status);
}