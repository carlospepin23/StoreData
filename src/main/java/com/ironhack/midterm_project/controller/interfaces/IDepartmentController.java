package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentEmployeesDTO;
import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentInventoryDTO;
import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentNameDTO;
import com.ironhack.midterm_project.model.Department;

import java.util.List;

public interface IDepartmentController {
    List<Department> getAllDepartments();
    Department getDepartmentById(Integer id);
    void addNewDepartment(Department department);
    void updateDepartmentName(DepartmentNameDTO departmentNameDTO, Integer id);
    void updateDepartmentEmployees(DepartmentEmployeesDTO departmentEmployeesDTO, Integer id);
    void updateDepartmentInventory(DepartmentInventoryDTO departmentInventoryDTO, Integer id);
        void updateDepartmentInformation(DepartmentDTO departmentDTO, Integer id);
    void deleteDepartment(Integer id);
    void deleteAllDepartments();

}

