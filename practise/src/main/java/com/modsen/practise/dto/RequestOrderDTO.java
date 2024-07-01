package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderDTO {
    private long id;

    @NotNull(message = "User is mandatory")
    @Valid
    private RequestUserDTO user;

    @NotNull
    private List<RequestOrderItemDTO> orderItems;
}