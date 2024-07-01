package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderItemDTO {
    private long id;

    @NotNull(message = "Product is mandatory")
    @Valid
    private ResponseProductDTO product;

    @Min(value = 1, message = "Quantity of products must be at least 1")
    private int quantityOfProducts;
}