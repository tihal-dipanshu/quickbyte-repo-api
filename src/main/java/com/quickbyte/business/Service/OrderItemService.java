package com.quickbyte.business.Service;

//import com.quickbyte.business.DTO.OrderItemDTO;
import com.quickbyte.business.IService.IOrderItemService;
import com.quickbyte.common.exceptions.InvalidInputException;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.data.DataModels.Order;
import com.quickbyte.data.DataModels.OrderItem;
import com.quickbyte.data.DataModels.MenuItem;
//import com.quickbyte.data.IRepositories.IOrderRepository;
import com.quickbyte.data.IRepositories.IMenuItemRepository;
import com.quickbyte.data.IRepositories.IOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemService implements IOrderItemService {

//    private final IOrderRepository orderRepository;
//    private final IMenuItemRepository menuItemRepository;
//    private final IOrderItemRepository orderItemRepository;
//
//    @Autowired
//    public OrderItemService(IOrderRepository orderRepository, IMenuItemRepository menuItemRepository, IOrderItemRepository orderItemRepository) {
//        this.orderRepository = orderRepository;
//        this.menuItemRepository = menuItemRepository;
//        this.orderItemRepository = orderItemRepository;
//    }
//
//    @Override
//    public List<OrderItemDTO> getAllOrderItems() {
//        return orderItemRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<OrderItemDTO> getOrderItemsByOrderId(Integer orderId) {
//        return orderItemRepository.findByOrder_OrderId(orderId).stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public OrderItemDTO createOrderItem(OrderItemDTO dto) {
//        Order order = orderRepository.findById(dto.getOrderId())
//                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + dto.getOrderId()));
//
//        MenuItem menuItem = menuItemRepository.findById(dto.getMenuItemId())
//                .orElseThrow(() -> new ResourceNotFoundException("No menu item found for ID: " + dto.getMenuItemId()));
//
//        OrderItem orderItem = convertToEntity(dto);
//        orderItem.setMenuItem(menuItem);
//        orderItem.setOrder(order);
//
//        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//        return convertToDTO(savedOrderItem);
//    }
//
//    @Override
//    public List<OrderItemDTO> createOrderItems(List<OrderItemDTO> orderItems) {
//        List<OrderItem> items = orderItems.stream()
//                .map(this::convertToEntity)
//                .collect(Collectors.toList());
//
//        List<OrderItem> savedItems = orderItemRepository.saveAll(items);
//        return savedItems.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public OrderItemDTO updateOrderItem(Integer orderItemId, OrderItemDTO dto) {
//        OrderItem existingOrderItem = orderItemRepository.findById(orderItemId)
//                .orElseThrow(() -> new ResourceNotFoundException("No order item found for ID: " + orderItemId));
//
//        updateFields(existingOrderItem, dto);
//
//        Order updatedOrder = orderRepository.findById(dto.getOrderId())
//                .orElseThrow(() -> new ResourceNotFoundException("No order found for ID: " + dto.getOrderId()));
//
//        Menu item = menuRepo.findByID(dto.getMenuID())
//                .orElseThrow(() -> new ResourceNotFoundException("No menu item found for ID: " + dto.getMenuID()));
//
//        existing.setOrders(updated);
//        existing.setMenu(item);
//
//        OrderItems savedItems = itemsRepo.save(existing);
//        return convertToDto(savedItems);
//    }
//
//    @Override
//    public void delete(Integer id) {
//        if (!itemsRepo.existsByID(id)) throw new ResourceNotFoundException("No item found with ID: " + id);
//        itemsRepo.deleteByID(id);
//    }
//
//    private void validateInput(ItemsDto dto) {
//        if (dto.getName() == null || dto.getName().trim().isEmpty()) throw new InvalidInputException("Name cannot be empty");
//        if (dto.getPrice() == null || dto.getPrice().compareTo(BigDecimal.ZERO) <= 0)
//            throw new InvalidInputException("Price must be greater than zero");
//    }
//
//    private void updateFields(Items existing, ItemsDto dto) {
//        existing.setName(dto.getName());
//        existing.setDescription(dto.getDescription());
//        existing.setPrice(dto.getPrice());
//        existing.setImageUrl(dto.getImageUrl());
//        existing.setAvailable(dto.isAvailable());
//    }
//
//    private MenuItems convertToEntity(MenuItemsDto dto) {
//        MenuItems item = new MenuItems();
//        updateFields(item, dto);
//        return item;
//    }
//
//    private MenuItemsDto convertToDto(MenuItems item) {
//        // Conversion logic here...
//    }
}