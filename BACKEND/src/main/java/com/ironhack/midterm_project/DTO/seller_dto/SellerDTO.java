package com.ironhack.midterm_project.DTO.seller_dto;

import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SellerDTO extends EmployeeDTO {
    @NotNull(message = "The name must not be null")
    @Email(message = "The email must be valid")
    private String email;
    @NotNull(message = "The sales must not be null")
    @Min(value = 0, message = "The sales must equal or greater than zero (0)")
    private Integer sales;
}
