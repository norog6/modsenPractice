package com.modsen.practise.service.impl;

import com.modsen.practise.dto.RequestOrderDTO;
import com.modsen.practise.dto.RequestOrderItemDTO;
import com.modsen.practise.entity.Order;
import com.modsen.practise.entity.OrderItem;
import com.modsen.practise.entity.Product;
import com.modsen.practise.entity.User;
import com.modsen.practise.mapper.OrderItemMapper;
import com.modsen.practise.mapper.OrderMapper;
import com.modsen.practise.repository.OrderItemRepository;
import com.modsen.practise.repository.OrderRepository;
import com.modsen.practise.repository.ProductRepository;
import com.modsen.practise.repository.UserRepository;
import com.modsen.practise.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ProductRepository productRepository;

    @Override
    public List<RequestOrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAllOrders();
        if (orders.isEmpty()) {
            throw new ResourceNotFoundException("No orders found");
        }

        return orders.stream()
                .map(orderMapper::toREQDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RequestOrderDTO> getAllOrdersByPage(PageRequest pageRequest) {
        Page<Order> orderPage = orderRepository.findAll(pageRequest);
        if (orderPage.isEmpty()) {
            throw new ResourceNotFoundException("No orders found.");
        }
        return orderPage.map(orderMapper::toREQDto);
    }

    @Override
    public RequestOrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toREQDto(order);
    }

    @Override
    public RequestOrderDTO createOrder(RequestOrderDTO requestOrderDTO) {
        User user = userRepository.findById(requestOrderDTO.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestOrderDTO.getUser().getId()));

        Order order = orderMapper.toEntity(requestOrderDTO);
        order.setUser(user);

        List<OrderItem> orderItems = new ArrayList<>();
        for (RequestOrderItemDTO itemDTO : requestOrderDTO.getOrderItems()) {
            Product product = productRepository.findById(itemDTO.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantityOfProducts(itemDTO.getQuantityOfProducts());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        orderItemRepository.saveAll(orderItems);
        return orderMapper.toREQDto(savedOrder);
    }

    @Override
    public RequestOrderDTO updateOrder(Long id, RequestOrderDTO requestOrderDTO) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        User user = userRepository.findById(requestOrderDTO.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + requestOrderDTO.getUser().getId()));

        Order order = orderMapper.toEntity(requestOrderDTO);
        order.setId(existingOrder.getId());
        order.setUser(user);
        Order updatedOrder = orderRepository.save(order);
        return orderMapper.toREQDto(updatedOrder);
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        orderRepository.delete(order);
    }

    @Override
    public List<RequestOrderDTO> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(orderMapper::toREQDto)
                .collect(Collectors.toList());
    }
}