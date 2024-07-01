package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderDTO {
    private long id;


    private ResponseUserDTO user;


    private List<ResponseOrderItemDTO> orderItems;
}