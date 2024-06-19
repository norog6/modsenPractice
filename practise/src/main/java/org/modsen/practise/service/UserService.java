package org.modsen.practise.service;
import org.modsen.practise.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
}