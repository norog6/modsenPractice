package com.modsen.practise.service.impl;

import com.modsen.practise.dto.UserDTO;
import com.modsen.practise.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<UserDTO> getAllUsers() {
        throw new UnsupportedOperationException("getAllUsers is not supported");
    }

    @Override
    public UserDTO getUserById(Long id) {
        throw new UnsupportedOperationException("getUserById is not supported");
    }

    @Override
    public Optional<UserDTO> getUserByLogin(String login) {
        throw new UnsupportedOperationException("getUserByLogin is not supported");
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        throw new UnsupportedOperationException("createUser is not supported");
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        throw new UnsupportedOperationException("updateUser is not supported");
    }

    @Override
    public void deleteUser(Long id) {
        throw new UnsupportedOperationException("deleteUser is not supported");
    }
}