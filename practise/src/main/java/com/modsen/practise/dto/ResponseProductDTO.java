package com.modsen.practise.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductDTO {
    private long id;


    private String name;

    private String description;


    private float price;


    private ResponseCategoryDTO category;

}