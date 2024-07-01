package com.modsen.practise.service;


import com.modsen.practise.dto.RequestOrderItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderItemService {
    List<RequestOrderItemDTO> getAllOrderItems();
    Page<RequestOrderItemDTO> getAllOrderItemsByPage(PageRequest pageRequest);
    RequestOrderItemDTO getOrderItemById(Long id);
    RequestOrderItemDTO createOrderItem(RequestOrderItemDTO requestOrderItemDTO);
    void deleteOrderItem(Long id);
    RequestOrderItemDTO updateOrderItem(Long id, RequestOrderItemDTO requestOrderItemDTO);
}