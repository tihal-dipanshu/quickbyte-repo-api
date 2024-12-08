package com.quickbyte.business.command.order;

import com.quickbyte.business.DTO.CreateOrderDTO;
import com.quickbyte.business.DTO.CreateOrderItemDTO;
import com.quickbyte.business.DTO.OrderDTO;
import com.quickbyte.business.DTO.OrderItemDTO;
import com.quickbyte.common.enums.OrderStatus;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.DataModels.OrderItem;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import com.quickbyte.data.IRepositories.IOrderRepository;
import com.quickbyte.data.IRepositories.IUserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class CreateOrderWithItemsCommand implements OrderCommand<OrderDTO> {
    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IMenuItemRepository menuItemRepository;
    private final CreateOrderDTO createOrderDTO;

    public CreateOrderWithItemsCommand(IOrderRepository orderRepository,
                                       IUserRepository userRepository,
                                       IMenuItemRepository menuItemRepository,
                                       CreateOrderDTO createOrderDTO) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.createOrderDTO = createOrderDTO;
    }

    @Override
    public OrderDTO execute() {
        User user = userRepository.findById(createOrderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("No user found for ID: " + createOrderDTO.getUserId()));

        Order order = convertToEntity(createOrderDTO);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CreateOrderItemDTO item : createOrderDTO.getOrderItems()) {
            MenuItem menuItem = menuItemRepository.findById(item.getMenuItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("No menu item found for ID: " + item.getMenuItemId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(item.getQuantity());
            BigDecimal subtotal = menuItem.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(subtotal);

            order.addOrderItem(orderItem);
        }

        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.Pending);
        order.setEstimatedPickupTime(LocalDateTime.now().plusMinutes(30));
        Order savedOrder = orderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    private Order convertToEntity(CreateOrderDTO dto) {
        Order order = new Order();
        return order;
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
                orderItem.getMenuItem().getPrice(),
                orderItem.getMenuItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
        );
    }
}
