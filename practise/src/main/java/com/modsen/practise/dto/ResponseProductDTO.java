package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO {
    private long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    private String description;

    @Positive(message = "Price must be positive")
    private float price;

    @NotNull(message = "Category is mandatory")
    @Valid
    private ResponseCategoryDTO category;

}