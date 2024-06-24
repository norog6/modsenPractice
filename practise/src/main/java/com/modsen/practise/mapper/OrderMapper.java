package com.modsen.practise.mapper;

import com.modsen.practise.dto.OrderDTO;
import com.modsen.practise.entity.Order;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface OrderMapper {

    public OrderDTO toDto(Order order);
    public Order toEntity(OrderDTO orderDTO);
}
