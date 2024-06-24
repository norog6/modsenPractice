package com.modsen.practise.service;


import com.modsen.practise.dto.OrderItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> getAllOrderItems();
    Page<OrderItemDTO> getAllOrderItemsByPage(PageRequest pageRequest);
    OrderItemDTO getOrderItemById(Long id);
    OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO);
    void deleteOrderItem(Long id);
    OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO);
}