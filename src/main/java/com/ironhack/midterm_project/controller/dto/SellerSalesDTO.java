package com.ironhack.midterm_project.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerSalesDTO {
    @NotNull(message = "The sales must not be null")
    private Integer sales;
}
