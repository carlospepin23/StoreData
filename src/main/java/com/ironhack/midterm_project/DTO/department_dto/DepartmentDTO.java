package com.ironhack.midterm_project.DTO.department_dto;

import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DepartmentDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotEmpty(message = "The department must have at least one employee")
    private List<EmployeeDTO> employees;
    @NotEmpty(message = "The department must have at least one product in inventory")
    private List<ProductDTO> inventory;
//
//    private StoreDTO store;
//
//    public DepartmentDTO(String name, List<EmployeeDTO> employees, List<ProductDTO> inventory) {
//        this.name = name;
//        this.employees = employees;
//        this.inventory = inventory;
//        departmentIdSetter();
//    }
//
//    private void departmentIdSetter() {
//        for (EmployeeDTO employee : this.employees) {
//            employee.setDepartment(this);
//        }
//        for (ProductDTO product : this.inventory) {
//            product.setDepartment(this);
//        }
//    }
}
