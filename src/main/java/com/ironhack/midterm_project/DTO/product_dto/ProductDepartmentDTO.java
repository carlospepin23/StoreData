package com.ironhack.midterm_project.DTO.product_dto;

import com.ironhack.midterm_project.DTO.department_dto.DepartmentDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDepartmentDTO {
    @NotNull(message = "The department cannot be null")
    private DepartmentDTO department;
}