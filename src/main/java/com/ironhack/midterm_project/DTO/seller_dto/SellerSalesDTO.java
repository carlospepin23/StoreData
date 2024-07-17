package com.ironhack.midterm_project.DTO.seller_dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerSalesDTO {
    @NotNull(message = "The sales must not be null")
    @Min(value = 0, message = "The sales must equal or greater than zero (0)")
    private Integer sales;
}
