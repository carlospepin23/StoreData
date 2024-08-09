package com.ironhack.midterm_project.DTO.department_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentNameDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
}