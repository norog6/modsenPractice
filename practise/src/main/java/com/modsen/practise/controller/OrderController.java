package com.modsen.practise.controller;

import com.modsen.practise.dto.RequestOrderDTO;
import com.modsen.practise.service.OrderService;
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
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<RequestOrderDTO>> getAllOrders() {
        List<RequestOrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/page")
    public ResponseEntity<List<RequestOrderDTO>> getAllOrdersByPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                                    @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                                    @RequestParam(value = "sort", required = false) String sortField,
                                                                    @RequestParam(value = "direction", defaultValue = "asc") String sortDirection){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toLowerCase());
        Page<RequestOrderDTO> page = orderService.getAllOrdersByPage(PageRequest.of(offset, limit, Sort.by(direction, sortField)));
        List<RequestOrderDTO> orders = page.getContent();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestOrderDTO> getOrderById(@PathVariable @Min(0) Long id) {
        RequestOrderDTO order = orderService.getOrderById(id);
        return  order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RequestOrderDTO>> getOrdersByUser(@PathVariable @Min(0) Long userId) {
        List<RequestOrderDTO> orders = orderService.getOrdersByUser(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<RequestOrderDTO> createOrder(@RequestBody @Valid RequestOrderDTO requestOrderDto) {
        RequestOrderDTO createdOrder = orderService.createOrder(requestOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestOrderDTO> updateOrder(@PathVariable @Min(0) Long id, @RequestBody @Valid RequestOrderDTO order) {
        RequestOrderDTO updatedOrder = orderService.updateOrder(id, order);
        return updatedOrder != null ? ResponseEntity.ok(updatedOrder) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable @Min(0) Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}