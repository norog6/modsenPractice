package com.modsen.practise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCategoryDTO {
    private long id;


    private String name;

}