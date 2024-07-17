package com.ironhack.midterm_project.service.interfaces;

import com.ironhack.midterm_project.DTO.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentEmployeesDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentInventoryDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentNameDTO;
import com.ironhack.midterm_project.model.Department;

import java.util.List;

public interface IDepartmentService {
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