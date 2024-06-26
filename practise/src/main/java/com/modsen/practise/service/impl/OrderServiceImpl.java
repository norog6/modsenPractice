package com.modsen.practise.service.impl;


import com.modsen.practise.dto.CategoryDTO;
import com.modsen.practise.dto.OrderDTO;
import com.modsen.practise.dto.OrderItemDTO;
import com.modsen.practise.entity.*;
import com.modsen.practise.mapper.OrderItemMapper;
import com.modsen.practise.mapper.OrderMapper;
import com.modsen.practise.repository.OrderItemRepository;
import com.modsen.practise.repository.OrderRepository;
import com.modsen.practise.repository.UserRepository;
import com.modsen.practise.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found");
        }
        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDTO> getAllOrdersByPage(PageRequest pageRequest) {
        Page<Order> orderPage = orderRepository.findAll(pageRequest);
        if (orderPage.isEmpty()) {
            throw new ResourceNotFoundException("No orders found.");
        }
        return orderPage.map(orderMapper::toDto);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        User user = userRepository.findById(orderDTO.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderDTO.getUser().getId()));

        Order order = orderMapper.toEntity(orderDTO);
        order.setUser(user);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        User user = userRepository.findById(orderDTO.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderDTO.getUser().getId()));

        Order order = orderMapper.toEntity(orderDTO);
        order.setId(existingOrder.getId());
        order.setUser(user);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDTO> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Optional<List<Order>> orders = orderRepository.findByUserId(userId);
        return orders.map(orderList -> orderList.stream()
                        .map(orderMapper::toDto)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new ResourceNotFoundException("Orders not found for user with id: " + userId));
    }
}