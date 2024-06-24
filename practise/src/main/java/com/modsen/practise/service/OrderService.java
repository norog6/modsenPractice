package com.modsen.practise.service;
import com.modsen.practise.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    Page<OrderDTO> getAllOrdersByPage(PageRequest pageRequest);
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
    List<OrderDTO> getOrdersByUser(Long userId);
}