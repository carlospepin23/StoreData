package com.ironhack.midterm_project.DTO.department_dto;

import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentEmployeesDTO {
    @NotEmpty(message = "The department must have at least one employee")
    private List<EmployeeDTO> employees;
}
