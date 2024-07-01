package com.modsen.practise.service.impl;

import com.modsen.practise.dto.RequestProductDTO;
import com.modsen.practise.dto.RequestOrderItemDTO;
import com.modsen.practise.entity.OrderItem;
import com.modsen.practise.entity.Product;
import com.modsen.practise.mapper.OrderItemMapper;
import com.modsen.practise.repository.OrderItemRepository;
import com.modsen.practise.repository.OrderRepository;
import com.modsen.practise.repository.ProductRepository;
import com.modsen.practise.service.OrderItemService;
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
    public List<RequestOrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        if (orderItems.isEmpty()) {
            throw new ResourceNotFoundException("No orderItems found. ");
        }
        return orderItems.stream()
                .map(orderItemMapper::toREQDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RequestOrderItemDTO> getAllOrderItemsByPage(PageRequest pageRequest) {
        Page<OrderItem> orderItemPage = orderItemRepository.findAll(pageRequest);
        if (orderItemPage.isEmpty()) {
            throw new ResourceNotFoundException("No orderItems found.");
        }
        return orderItemPage.map(orderItemMapper::toREQDto);
    }

    @Override
    public RequestOrderItemDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        return orderItemMapper.toREQDto(orderItem);
    }

    @Override
    public RequestOrderItemDTO createOrderItem(RequestOrderItemDTO requestOrderItemDTO) {
        OrderItem orderItem = orderItemMapper.toEntity(requestOrderItemDTO);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toREQDto(savedOrderItem);
    }

    @Override
    public RequestOrderItemDTO updateOrderItem(Long id, RequestOrderItemDTO requestOrderItemDTO) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        orderItem.setQuantityOfProducts(requestOrderItemDTO.getQuantityOfProducts());

        RequestProductDTO requestProductDTO = requestOrderItemDTO.getProduct();
        if (requestProductDTO != null) {
            Product product = productRepository.findById(requestProductDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + requestProductDTO.getId()));
            orderItem.setProduct(product);
        }
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toREQDto(savedOrderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));
        orderItemRepository.delete(orderItem);
    }
}