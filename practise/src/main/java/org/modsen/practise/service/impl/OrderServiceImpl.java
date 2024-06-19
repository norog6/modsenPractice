package org.modsen.practise.service.impl;

import org.modsen.practise.dto.OrderDTO;
import org.modsen.practise.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private Map<Long, OrderDTO> orders = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<OrderDTO> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orders.get(id);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        orderDTO.setId(nextId++);
        orders.put(orderDTO.getId(), orderDTO);
        return orderDTO;
    }

    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDTO) {
        if (orders.containsKey(id)) {
            orderDTO.setId(id);
            orders.put(id, orderDTO);
            return orderDTO;
        }
        return null;
    }

    @Override
    public void deleteOrder(Long id) {
        orders.remove(id);
    }

}