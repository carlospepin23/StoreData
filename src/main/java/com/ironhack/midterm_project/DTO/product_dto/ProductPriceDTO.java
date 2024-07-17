package com.ironhack.midterm_project.DTO.product_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductPriceDTO {
    @NotNull(message = "The price must not be null")
    @Min(value = 0, message = "The price must greater than zero (0)")
    private Double price;
}
