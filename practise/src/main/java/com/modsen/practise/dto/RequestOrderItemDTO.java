package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderItemDTO {
    private long id;

    @NotNull(message = "Product is mandatory")
    @Valid
    private RequestProductDTO product;

    @Min(value = 1, message = "Quantity of products must be at least 1")
    private int quantityOfProducts;
}