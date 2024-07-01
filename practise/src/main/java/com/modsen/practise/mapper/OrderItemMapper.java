package com.modsen.practise.mapper;

import com.modsen.practise.dto.RequestOrderItemDTO;
import com.modsen.practise.dto.ResponseOrderItemDTO;
import com.modsen.practise.entity.OrderItem;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductMapper.class})
public interface OrderItemMapper {
    public RequestOrderItemDTO toREQDto(OrderItem orderItem);
    public ResponseOrderItemDTO toRESDto(OrderItem orderItem);
    public OrderItem toEntity(RequestOrderItemDTO requestOrderItemDTO);
    public OrderItem toEntity(ResponseOrderItemDTO responseOrderItemDTO);

}