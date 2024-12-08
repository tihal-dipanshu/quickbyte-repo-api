package com.quickbyte.business.command.order;

import com.quickbyte.business.DTO.OrderDTO;
import com.quickbyte.business.DTO.OrderItemDTO;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.DataModels.OrderItem;
import com.quickbyte.data.IRepositories.IOrderRepository;

import java.util.stream.Collectors;

public class GetOrderByIdCommand implements OrderCommand<OrderDTO> {
    private final IOrderRepository orderRepository;
    private final Integer orderId;

    public GetOrderByIdCommand(IOrderRepository orderRepository, Integer orderId) {
        this.orderRepository = orderRepository;
        this.orderId = orderId;
    }

    @Override
    public OrderDTO execute() {
        return orderRepository.findById(orderId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));
    }

    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getUser().getUserId(),
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getStatus().toString(),
                order.getEstimatedPickupTime(),
                order.getOrderItems().stream()
                        .map(this::convertToOrderItemDTO)
                        .collect(Collectors.toList())
        );
    }

    private OrderItemDTO convertToOrderItemDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getOrderItemId(),
                orderItem.getMenuItem().getItemId(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                orderItem.getSubtotal()
        );
    }
}
