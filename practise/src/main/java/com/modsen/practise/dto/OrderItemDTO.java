package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private long id;

    @NotNull(message = "Order is mandatory")
    @Valid
    private OrderDTO order;

    @NotNull(message = "Product is mandatory")
    @Valid
    private ProductDTO product;

    @Min(value = 1, message = "Quantity of products must be at least 1")
    private int quantityOfProducts;
}