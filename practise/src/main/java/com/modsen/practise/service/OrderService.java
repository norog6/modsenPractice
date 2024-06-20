package com.modsen.practise.service;
import com.modsen.practise.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
    List<OrderDTO> getOrdersByUser(Long userId);
}