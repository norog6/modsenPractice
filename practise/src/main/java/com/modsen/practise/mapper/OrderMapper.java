package com.modsen.practise.mapper;

import com.modsen.practise.dto.RequestOrderDTO;
import com.modsen.practise.dto.ResponseOrderDTO;
import com.modsen.practise.entity.Order;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface OrderMapper {
    public RequestOrderDTO toREQDto(Order order);
    public ResponseOrderDTO toRESDto(Order order);
    public Order toEntity(RequestOrderDTO requestOrderDTO);
    public Order toEntity(ResponseOrderDTO requestOrderDTO);
}
