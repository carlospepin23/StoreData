package com.ironhack.midterm_project.controller.dto.store_dto;

import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.controller.dto.product_dto.ProductDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @NotEmpty(message = "The store must have at least one department")
    private List<DepartmentDTO> departments;

}
