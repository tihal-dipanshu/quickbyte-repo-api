package com.quickbyte.business.command.order;

import com.quickbyte.business.DTO.OrderDTO;
import com.quickbyte.business.DTO.OrderItemDTO;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.DataModels.OrderItem;
import com.quickbyte.data.IRepositories.IOrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GetOrdersByUserIdCommand implements OrderCommand<List<OrderDTO>> {
    private final IOrderRepository orderRepository;
    private final Integer userId;

    public GetOrdersByUserIdCommand(IOrderRepository orderRepository, Integer userId) {
        this.orderRepository = orderRepository;
        this.userId = userId;
    }

    @Override
    public List<OrderDTO> execute() {
        return orderRepository.findByUser_UserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
