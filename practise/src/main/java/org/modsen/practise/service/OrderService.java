package org.modsen.practise.service;
import org.modsen.practise.dto.OrderDTO;
import org.modsen.practise.dto.UserDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(Long id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(Long id, OrderDTO orderDTO);
    void deleteOrder(Long id);
//    List<OrderDTO> getOrdersByUser(UserDTO userDTO);
}