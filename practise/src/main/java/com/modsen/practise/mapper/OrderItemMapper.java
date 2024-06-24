package com.modsen.practise.mapper;

import com.modsen.practise.dto.OrderItemDTO;
import com.modsen.practise.entity.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductMapper.class})
public interface OrderItemMapper {
    public OrderItemDTO toDto(OrderItem orderItem);
    public OrderItem toEntity(OrderItemDTO orderItemDTO);
}