package com.ironhack.midterm_project.DTO.store_dto;

import com.ironhack.midterm_project.DTO.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.model.Department;
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

//    public StoreDTO(String name, String location, List<DepartmentDTO> departments) {
//        this.name = name;
//        this.location = location;
//        this.departments = departments;
//        storeIdSetter();
//    }
//
//    private void storeIdSetter() {
//        for (DepartmentDTO department : departments) {
//            department.setStore(this);
//        }
//    }

}
