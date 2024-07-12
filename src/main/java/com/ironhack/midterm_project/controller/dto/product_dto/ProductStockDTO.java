package com.ironhack.midterm_project.controller.dto.product_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductStockDTO {
    @NotNull(message = "The stock must not be null")
    @Min(value = 0, message = "The stock must greater or equal than zero (0)")
    private Integer stock;
}
