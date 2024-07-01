package com.modsen.practise.service;
import com.modsen.practise.dto.RequestOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderService {
    List<RequestOrderDTO> getAllOrders();
    Page<RequestOrderDTO> getAllOrdersByPage(PageRequest pageRequest);
    RequestOrderDTO getOrderById(Long id);
    RequestOrderDTO createOrder(RequestOrderDTO requestOrderDTO);
    RequestOrderDTO updateOrder(Long id, RequestOrderDTO requestOrderDTO);
    void deleteOrder(Long id);
    List<RequestOrderDTO> getOrdersByUser(Long userId);
}