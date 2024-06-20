package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private long id;

    @NotNull(message = "User is mandatory")
    @Valid
    private UserDTO user;

    @NotEmpty(message = "Order must have at least one item")
    @Valid
    private List<OrderItemDTO> orderItems;
}