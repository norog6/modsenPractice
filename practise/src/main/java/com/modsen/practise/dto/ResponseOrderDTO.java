package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderDTO {
    private long id;

    @NotNull(message = "User is mandatory")
    @Valid
    private ResponseUserDTO user;

    @NotNull
    private List<ResponseOrderItemDTO> orderItems;
}