package com.quickbyte.business.command.order;

import com.quickbyte.business.DTO.OrderDTO;
import com.quickbyte.business.DTO.OrderItemDTO;
import com.quickbyte.common.enums.OrderStatus;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.DataModels.OrderItem;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import com.quickbyte.data.IRepositories.IOrderRepository;

import java.util.stream.Collectors;

public class UpdateOrderCommand implements OrderCommand<OrderDTO> {
    private final IOrderRepository orderRepository;
    private final Integer orderId;
    private final OrderDTO orderDTO;
    private final IMenuItemRepository menuItemRepository;

    public UpdateOrderCommand(IOrderRepository orderRepository, Integer orderId, OrderDTO orderDTO, IMenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.orderId = orderId;
        this.orderDTO = orderDTO;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public OrderDTO execute() {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));

        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setStatus(OrderStatus.fromString(orderDTO.getStatus()));

        existingOrder.getOrderItems().clear();

        java.math.BigDecimal totalAmount = java.math.BigDecimal.ZERO;

        for (OrderItemDTO itemDTO : orderDTO.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItemRepository.findById(itemDTO.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Menu item not found: " + itemDTO.getMenuItemId())));
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setUnitPrice(itemDTO.getUnitPrice());

            java.math.BigDecimal subtotal = itemDTO.getUnitPrice().multiply(new java.math.BigDecimal(itemDTO.getQuantity()));
            orderItem.setSubtotal(subtotal);

            totalAmount = totalAmount.add(subtotal);

            existingOrder.addOrderItem(orderItem);
        }

        existingOrder.setTotalAmount(totalAmount);

        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDTO(updatedOrder);
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