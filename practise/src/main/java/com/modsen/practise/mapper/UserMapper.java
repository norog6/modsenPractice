package com.modsen.practise.mapper;

import com.modsen.practise.dto.UserDTO;
import com.modsen.practise.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserDTO toDto(User user);

    public User toEntity(UserDTO userDTO);
}
