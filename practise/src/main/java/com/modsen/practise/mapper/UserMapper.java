package com.modsen.practise.mapper;

import com.modsen.practise.dto.RequestUserDTO;
import com.modsen.practise.dto.ResponseUserDTO;
import com.modsen.practise.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public RequestUserDTO toREQDto(User user);
    public ResponseUserDTO toRESDto(User user);
    public User toEntity(RequestUserDTO requestUserDTO);
    public User toEntity(ResponseUserDTO requestUserDTO);
}
