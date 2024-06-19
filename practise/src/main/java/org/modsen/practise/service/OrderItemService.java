package org.modsen.practise.service;

import org.modsen.practise.dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDTO> getAllOrderItems();

    OrderItemDTO getOrderItemById(Long id);
    OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO);
    void deleteOrderItem(Long id);
    OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO);
}