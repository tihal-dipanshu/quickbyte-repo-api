package com.quickbyte.business.command.order;

import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.IRepositories.IOrderRepository;

public class DeleteOrderCommand implements OrderCommand<Void> {
    private final IOrderRepository orderRepository;
    private final Integer orderId;

    public DeleteOrderCommand(IOrderRepository orderRepository, Integer orderId) {
        this.orderRepository = orderRepository;
        this.orderId = orderId;
    }

    @Override
    public Void execute() {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));
        orderRepository.delete(order);
        return null;
    }
}
