package com.modsen.practise.service.impl;

import com.modsen.practise.dto.OrderDTO;
import com.modsen.practise.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderDTO> getAllOrders() {
        throw new UnsupportedOperationException("getAllOrders is not supported");
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        throw new UnsupportedOperationException("getOrderById is not supported");
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        throw new UnsupportedOperationException("createOrder is not supported");
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        throw new UnsupportedOperationException("updateOrder is not supported");
    }

    @Override
    public void deleteOrder(Long id) {
        throw new UnsupportedOperationException("deleteOrder is not supported");
    }
    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        throw new UnsupportedOperationException("getOrdersByUser is not supported");
    }
}