package com.modsen.practise.controller;

import com.modsen.practise.service.OrderItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import com.modsen.practise.dto.OrderItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        List<OrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable @Min(0) Long id) {
        OrderItemDTO orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody @Valid OrderItemDTO orderItemDto) {
        OrderItemDTO createdOrderItem = orderItemService.createOrderItem(orderItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable @Min(0) Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable @Min(0) Long id, @RequestBody @Valid OrderItemDTO orderItemDto) {
        OrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDto);
        return ResponseEntity.ok(updatedOrderItem);
    }
}