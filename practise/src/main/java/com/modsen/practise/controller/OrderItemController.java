package com.modsen.practise.controller;

import com.modsen.practise.dto.RequestOrderItemDTO;
import com.modsen.practise.service.OrderItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<List<RequestOrderItemDTO>> getAllOrderItems() {
        List<RequestOrderItemDTO> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/page")
    public ResponseEntity<List<RequestOrderItemDTO>> getAllOrderItemsByPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                                            @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                            @RequestParam(value = "sort", required = false) String sortField,
                                                                            @RequestParam(value = "direction", defaultValue = "asc") String sortDirection){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toLowerCase());
        Page<RequestOrderItemDTO> page = orderItemService.getAllOrderItemsByPage(PageRequest.of(offset, limit, Sort.by(direction, sortField)));
        List<RequestOrderItemDTO> orderItems = page.getContent();
        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestOrderItemDTO> getOrderItemById(@PathVariable @Min(0) Long id) {
        RequestOrderItemDTO orderItem = orderItemService.getOrderItemById(id);
        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public ResponseEntity<RequestOrderItemDTO> createOrderItem(@RequestBody @Valid RequestOrderItemDTO requestOrderItemDto) {
        RequestOrderItemDTO createdOrderItem = orderItemService.createOrderItem(requestOrderItemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable @Min(0) Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestOrderItemDTO> updateOrderItem(@PathVariable @Min(0) Long id, @RequestBody @Valid RequestOrderItemDTO requestOrderItemDto) {
        RequestOrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(id, requestOrderItemDto);
        return ResponseEntity.ok(updatedOrderItem);
    }
}