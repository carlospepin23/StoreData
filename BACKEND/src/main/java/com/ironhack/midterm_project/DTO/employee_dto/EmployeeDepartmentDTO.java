package com.ironhack.midterm_project.DTO.employee_dto;

import com.ironhack.midterm_project.DTO.department_dto.DepartmentDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDepartmentDTO {
    @NotNull(message = "The department cannot be null")
    private DepartmentDTO department;
}
