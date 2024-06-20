package com.modsen.practise.service.impl;

import com.modsen.practise.service.OrderItemService;
import com.modsen.practise.dto.OrderItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        throw new UnsupportedOperationException("getAllOrderItems is not supported");
    }

    @Override
    public OrderItemDTO getOrderItemById(Long id) {
        throw new UnsupportedOperationException("getOrderItemById is not supported");
    }

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        throw new UnsupportedOperationException("createOrderItem is not supported");
    }

    @Override
    public void deleteOrderItem(Long id) {
        throw new UnsupportedOperationException("deleteOrderItem is not supported");
    }

    @Override
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        throw new UnsupportedOperationException("updateOrderItem is not supported");
    }
}