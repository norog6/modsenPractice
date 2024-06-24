package com.modsen.practise.service;

import com.modsen.practise.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> getAllUsers();
    Page<UserDTO> getAllUsersByPage(PageRequest pageRequest);
    UserDTO getUserById(Long id);
    Optional<UserDTO> getUserByLogin(String login);
    Optional<UserDTO> getUserByEmail(String email);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}