package com.ironhack.midterm_project.controller.dto.seller_dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerEmailDTO {
    @NotNull(message = "The email must not be null")
    @Email(message = "The email must be valid")
    private String email;
}