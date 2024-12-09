package com.quickbyte.business.Service;

import com.quickbyte.business.DTO.CreateOrderDTO;
import com.quickbyte.business.DTO.OrderDTO;
import com.quickbyte.business.IService.IOrderService;
import com.quickbyte.business.command.order.*;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import com.quickbyte.data.IRepositories.IOrderItemRepository;
import com.quickbyte.data.IRepositories.IOrderRepository;
import com.quickbyte.data.IRepositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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

    public <T> T executeCommand(OrderCommand<T> command) {
        return command.execute();
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return executeCommand(new GetAllOrdersCommand(orderRepository));
    }

    @Override
    public OrderDTO getOrderById(Integer orderId) {
        return executeCommand(new GetOrderByIdCommand(orderRepository, orderId));
    }

    @Override
    public OrderDTO createOrderWithItems(CreateOrderDTO createOrderDTO) {
        return executeCommand(new CreateOrderWithItemsCommand(orderRepository, userRepository, menuItemRepository, createOrderDTO));
    }

    @Override
    public void deleteOrder(Integer orderId) {
        executeCommand(new DeleteOrderCommand(orderRepository, orderId));
    }

    @Override
    public OrderDTO updateOrder(Integer orderId, OrderDTO orderDTO) {
        return executeCommand(new UpdateOrderCommand(orderRepository, orderId, orderDTO, menuItemRepository));
    }

    @Override
    public void updateOrderStatus(Integer orderId, String status) {
        executeCommand(new UpdateOrderStatusCommand(orderRepository, orderId, status));
    }

    @Override
    public List<OrderDTO> getOrdersByUserId(Integer userId) {
        return executeCommand(new GetOrdersByUserIdCommand(orderRepository, userId));
    }
}