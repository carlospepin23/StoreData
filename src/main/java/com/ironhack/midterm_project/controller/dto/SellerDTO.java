package com.ironhack.midterm_project.controller.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerDTO {
    @Email(message = "The email must be valid")
    private String email;

    private Integer sales;
}
