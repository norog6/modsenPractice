package com.modsen.practise.service.impl;

import com.modsen.practise.dto.RequestUserDTO;
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
    public List<RequestUserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found");
        }
        return users.stream()
                .map(userMapper::toREQDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<RequestUserDTO> getAllUsersByPage(PageRequest pageRequest) {
        Page<User> userPage = userRepository.findAll(pageRequest);
        if (userPage.isEmpty()) {
            throw new ResourceNotFoundException("No users found.");
        }
        return userPage.map(userMapper::toREQDto);
    }

    @Override
    public RequestUserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toREQDto(user);
    }

    @Override
    public Optional<RequestUserDTO> getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .map(userMapper::toREQDto);
    }

    @Override
    public Optional<RequestUserDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toREQDto);
    }


    @Override
    public RequestUserDTO createUser(RequestUserDTO requestUserDTO) {
        User user = userMapper.toEntity(requestUserDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toREQDto(savedUser);
    }

    @Override
    public RequestUserDTO updateUser(Long id, RequestUserDTO requestUserDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setEmail(requestUserDTO.getEmail());
        user.setGender(requestUserDTO.getGender());
        user.setLogin(requestUserDTO.getLogin());
        user.setPassword(requestUserDTO.getPassword());
        user.setFirstName(requestUserDTO.getFirstName());
        user.setLastName(requestUserDTO.getLastName());
        user.setRoles(requestUserDTO.getRoles());
        user.setDateOfBirth(requestUserDTO.getDateOfBirth());
        User updatedUser = userRepository.save(user);
        return userMapper.toREQDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

}