package com.modsen.practise.controller;

import com.modsen.practise.dto.UserDTO;
import com.modsen.practise.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/page")
    public ResponseEntity<List<UserDTO>> getAllUsersByPage(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
                                                           @RequestParam(value = "limit", defaultValue = "10") @Min(1) @Max(100) Integer limit,
                                                           @RequestParam(value = "sort", required = false) String sortField,
                                                           @RequestParam(value = "direction", defaultValue = "asc") String sortDirection){
        Sort.Direction direction = Sort.Direction.fromString(sortDirection.toLowerCase());
        Page<UserDTO> page = userService.getAllUsersByPage(PageRequest.of(offset, limit, Sort.by(direction, sortField)));
        List<UserDTO> users = page.getContent();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable @Min(0) Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDto) {
        UserDTO createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable @Min(0) Long id, @RequestBody @Valid UserDTO userDto) {
        UserDTO updatedUser = userService.updateUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Min(0) Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}