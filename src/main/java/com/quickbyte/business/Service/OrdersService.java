package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.CreateOrderDTO;
import com.quickbyte.business.DTO.CreateOrderItemDTO;
import com.quickbyte.business.DTO.OrderDTO;
import com.quickbyte.business.DTO.OrderItemDTO;
import com.quickbyte.business.IService.IOrderService;
import com.quickbyte.common.enums.OrderStatus;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.MenuItem;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.DataModels.OrderItem;
import com.quickbyte.data.DataModels.User;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import com.quickbyte.data.IRepositories.IOrderItemRepository;
import com.quickbyte.data.IRepositories.IOrderRepository;
import com.quickbyte.data.IRepositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdersService implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserRepository userRepository;
    private final IMenuItemRepository menuItemRepository;
    private final IOrderItemRepository orderItemRepository;

    @Autowired
    public OrdersService(IOrderRepository orderRepository, IUserRepository userRepository,
                         IMenuItemRepository menuItemRepository, IOrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));
    }

    @Override
    public OrderDTO createOrderWithItems(CreateOrderDTO createOrderDTO) {
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

    @Override
    public void deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));
        orderRepository.delete(order);
    }

    @Override
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));

        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setTotalAmount(orderDTO.getTotalAmount());
//        existingOrder.setStatus(OrderStatus.valueOf(orderDTO.getStatus()));
        existingOrder.setStatus(OrderStatus.fromString(orderDTO.getStatus()));


        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDTO(updatedOrder);
    }

    @Override
    public void updateOrderStatus(Integer orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + orderId));
//        order.setStatus(OrderStatus.valueOf(status));
        order.setStatus(OrderStatus.fromString(status));
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Integer userId) {
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
                        .map(this::convertToDTO)
                        .collect(Collectors.toList())
        );
    }

    private Order convertToEntity(CreateOrderDTO dto) {
        Order order = new Order();
//        order.setOrderDate(LocalDateTime.now());
//        order.setTotalAmount(dto.getTotalAmount());
//        order.setStatus(OrderStatus.valueOf(dto.getStatus()));
//        order.setStatus(OrderStatus.fromString(dto.getStatus()));
        return order;
    }

    private OrderItemDTO convertToDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getOrderItemId(),
                orderItem.getMenuItem().getItemId(),
                orderItem.getQuantity(),
                orderItem.getMenuItem().getPrice(),
                orderItem.getMenuItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))
        );
    }

    private OrderItem convertToEntity(OrderItemDTO dto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(dto.getOrderItemId());
        orderItem.setQuantity(dto.getQuantity());
        return orderItem;
    }

}