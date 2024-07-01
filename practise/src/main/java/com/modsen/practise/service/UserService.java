package com.modsen.practise.service;

import com.modsen.practise.dto.RequestUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<RequestUserDTO> getAllUsers();
    Page<RequestUserDTO> getAllUsersByPage(PageRequest pageRequest);
    RequestUserDTO getUserById(Long id);
    Optional<RequestUserDTO> getUserByLogin(String login);
    Optional<RequestUserDTO> getUserByEmail(String email);
    RequestUserDTO createUser(RequestUserDTO requestUserDTO);
    RequestUserDTO updateUser(Long id, RequestUserDTO requestUserDTO);
    void deleteUser(Long id);
}