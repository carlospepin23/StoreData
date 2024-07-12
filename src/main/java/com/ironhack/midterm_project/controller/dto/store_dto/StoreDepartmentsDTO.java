package com.ironhack.midterm_project.controller.dto.store_dto;

import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreDepartmentsDTO {
    @NotEmpty(message = "The store must have at least one department")
    private List<DepartmentDTO> departments;
}
