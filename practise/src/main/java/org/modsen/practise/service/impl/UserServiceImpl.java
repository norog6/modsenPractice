package org.modsen.practise.service.impl;

import org.modsen.practise.dto.UserDTO;
import org.modsen.practise.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private Map<Long, UserDTO> users = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<UserDTO> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return users.get(id);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTO.setId(nextId++);
        users.put(userDTO.getId(), userDTO);
        return userDTO;
    }
}