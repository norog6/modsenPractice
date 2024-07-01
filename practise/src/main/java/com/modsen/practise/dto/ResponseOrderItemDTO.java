package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderItemDTO {
    private long id;


    private ResponseProductDTO product;


    private int quantityOfProducts;
}