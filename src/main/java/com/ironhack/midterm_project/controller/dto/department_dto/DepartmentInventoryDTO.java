package com.ironhack.midterm_project.controller.dto.department_dto;

import com.ironhack.midterm_project.controller.dto.product_dto.ProductDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentInventoryDTO {
    @NotEmpty(message = "The department must have at least one product in inventory")
    private List<ProductDTO> inventory;
}
