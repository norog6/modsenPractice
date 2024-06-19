package org.modsen.practise.service.impl;

import org.modsen.practise.dto.OrderItemDTO;
import org.modsen.practise.service.OrderItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    private Map<Long, OrderItemDTO> orderItems = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        return new ArrayList<>(orderItems.values());
    }

    @Override
    public OrderItemDTO getOrderItemById(Long id) {
        return orderItems.get(id);
    }

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        orderItemDTO.setId(nextId++);
        orderItems.put(orderItemDTO.getId(), orderItemDTO);
        return orderItemDTO;
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItems.remove(id);
    }

    @Override
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        if (orderItems.containsKey(id)) {
            orderItemDTO.setId(id);
            orderItems.put(id, orderItemDTO);
            return orderItemDTO;
        } else {
            throw new IllegalArgumentException("Order item not found with ID: " + id);
        }
    }
}