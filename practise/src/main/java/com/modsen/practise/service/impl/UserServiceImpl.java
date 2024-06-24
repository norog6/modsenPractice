package com.modsen.practise.service.impl;

import com.modsen.practise.dto.UserDTO;
import com.modsen.practise.entity.User;
import com.modsen.practise.mapper.UserMapper;
import com.modsen.practise.repository.UserRepository;
import com.modsen.practise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDTO> getAllUsersByPage(PageRequest pageRequest) {
        Page<User> userPage = userRepository.findAll(pageRequest);
        if (userPage.isEmpty()) {
            throw new ResourceNotFoundException("No users found.");
        }
        return userPage.map(userMapper::toDto);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public Optional<UserDTO> getUserByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return Optional.ofNullable(user).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return Optional.ofNullable(user).map(userMapper::toDto);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        throw new UnsupportedOperationException("updateUser is not supported");
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

}