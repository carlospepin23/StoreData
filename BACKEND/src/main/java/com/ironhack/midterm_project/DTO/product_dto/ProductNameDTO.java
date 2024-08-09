package com.ironhack.midterm_project.DTO.product_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductNameDTO {
    @NotBlank(message = "The name must not be blank")
    private String name;
}