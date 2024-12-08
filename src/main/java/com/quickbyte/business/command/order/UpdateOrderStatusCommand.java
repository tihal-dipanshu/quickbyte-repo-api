package com.quickbyte.business.command.order;

import com.quickbyte.common.enums.OrderStatus;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.IRepositories.IOrderRepository;

public class UpdateOrderStatusCommand implements OrderCommand<Void> {
    private final IOrderRepository orderRepository;
    private final Integer orderId;
    private final String status;

    public UpdateOrderStatusCommand(IOrderRepository orderRepository, Integer orderId, String status) {
        this.orderRepository = orderRepository;
        this.orderId = orderId;
        this.status = status;
    }

    @Override
    public Void execute() {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));
        order.setStatus(OrderStatus.fromString(status));
        orderRepository.save(order);
        return null;
    }
}
