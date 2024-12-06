package com.quickbyte.api.controllers;

//import com.quickbyte.business.DTO.OrderItemDTO;
import com.quickbyte.business.IService.IOrderItemService;
import com.quickbyte.common.exceptions.ErrorResponseCustom;
import com.quickbyte.common.exceptions.ResourceNotFoundException;
import com.quickbyte.common.exceptions.MenuItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemsController {
//
//    private final IOrderItemService orderItemService;
//
//    @Autowired
//    public OrderItemsController(IOrderItemService orderItemService) {
//        this.orderItemService = orderItemService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
//        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
//        return ResponseEntity.ok(orderItems);
//    }
//
//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<?> getOrderItemsByOrderId(@PathVariable Integer orderId) {
//        try {
//            List<OrderItemDTO> orderItems = orderItemService.getOrderItemsByOrderId(orderId);
//            return ResponseEntity.ok(orderItems);
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponseCustom("Order not found: " + e.getMessage()));
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createOrderItem(@RequestBody OrderItemDTO newOrderItem) {
//        try {
//            OrderItemDTO createdOrderItem = orderItemService.createOrderItem(newOrderItem);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
//        } catch (MenuItemNotFoundException | ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(new ErrorResponseCustom("Invalid input: " + e.getMessage()));
//        }
//    }
//
//    @PostMapping("/bulk")
//    public ResponseEntity<List<OrderItemDTO>> createOrderItems(@RequestBody List<OrderItemDTO> newOrderItems) {
//        List<OrderItemDTO> createdOrderItems = orderItemService.createOrderItems(newOrderItems);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItems);
//    }
//
//    @PutMapping("/{orderItemId}")
//    public ResponseEntity<?> updateOrderItem(@PathVariable Integer orderItemId, @RequestBody OrderItemDTO updateOrderItem) {
//        try {
//            OrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(orderItemId, updateOrderItem);
//            return ResponseEntity.ok(updatedOrderItem);
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponseCustom("Order item not found: " + e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/{orderItemId}")
//    public ResponseEntity<?> deleteOrderItem(@PathVariable Integer orderItemId) {
//        try {
//            orderItemService.deleteOrderItem(orderItemId);
//            return ResponseEntity.ok("Order item deleted successfully");
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponseCustom("Order item not found: " + e.getMessage()));
//        }
//    }
//
//    @DeleteMapping("/bulk")
//    public ResponseEntity<?> deleteOrderItems(@RequestBody List<Integer> orderItemIds) {
//        try {
//            orderItemService.deleteOrderItems(orderItemIds);
//            return ResponseEntity.ok("Order items deleted successfully");
//        } catch (ResourceNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new ErrorResponseCustom("Order items not found: " + e.getMessage()));
//        }
//    }
//
////    @GetMapping("/order/{orderId}/total")
////    public ResponseEntity<?> getOrderTotal(@PathVariable Integer orderId) {
////        try {
////            Double total = orderItemService.getOrderTotal(orderId);
////            return ResponseEntity.ok(total);
////        } catch (ResourceNotFoundException e) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND)
////                    .body(new ErrorResponseCustom("Order not found: " + e.getMessage()));
////        }
////    }
//
////    @GetMapping("/order/{orderId}/items-count")
////    public ResponseEntity<?> getOrderItemsCount(@PathVariable Integer orderId) {
////        try {
////            Integer count = orderItemService.getOrderItemsCount(orderId);
////            return ResponseEntity.ok(count);
////        } catch (ResourceNotFoundException e) {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND)
////                    .body(new ErrorResponseCustom("Order not found: " + e.getMessage()));
////        }
////    }


}