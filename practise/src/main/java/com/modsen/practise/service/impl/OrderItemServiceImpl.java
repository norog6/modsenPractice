package com.modsen.practise.service.impl;

import com.modsen.practise.dto.CategoryDTO;
import com.modsen.practise.dto.OrderDTO;
import com.modsen.practise.dto.ProductDTO;
import com.modsen.practise.entity.Category;
import com.modsen.practise.entity.Order;
import com.modsen.practise.entity.OrderItem;
import com.modsen.practise.entity.Product;
import com.modsen.practise.mapper.OrderItemMapper;
import com.modsen.practise.repository.OrderItemRepository;
import com.modsen.practise.repository.OrderRepository;
import com.modsen.practise.repository.ProductRepository;
import com.modsen.practise.service.OrderItemService;
import com.modsen.practise.dto.OrderItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        if (orderItems.isEmpty()) {
            throw new ResourceNotFoundException("No orderItems found. ");
        }
        return orderItems.stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderItemDTO> getAllOrderItemsByPage(PageRequest pageRequest) {
        Page<OrderItem> orderItemPage = orderItemRepository.findAll(pageRequest);
        if (orderItemPage.isEmpty()) {
            throw new ResourceNotFoundException("No orderItems found.");
        }
        return orderItemPage.map(orderItemMapper::toDto);
    }

    @Override
    public OrderItemDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        return orderItemMapper.toDto(orderItem);
    }

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemMapper.toEntity(orderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(savedOrderItem);
    }

    @Override
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        orderItem.setQuantityOfProducts(orderItemDTO.getQuantityOfProducts());


        OrderDTO orderDTO = orderItemDTO.getOrder();
        if (orderDTO != null) {
            Order order = orderRepository.findById(orderDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderDTO.getId()));
            orderItem.setOrder(order);
        }

        ProductDTO productDTO = orderItemDTO.getProduct();
        if (productDTO != null) {
            Product product = productRepository.findById(productDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productDTO.getId()));
            orderItem.setProduct(product);
        }
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(savedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        orderItemRepository.delete(orderItem);
    }
}