package com.ironhack.midterm_project.service.interfaces;

import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Product;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAllDepartments();
    Department getDepartmentById(Integer id);
    void addNewDepartment(Department department);
    void updateDepartmentName(String name, Integer id);
    void updateDepartmentEmployees(List<Employee> employees, Integer id);
    void updateDepartmentInventory(List<Product> inventory, Integer id);
    void updateDepartmentInformation(Department department, Integer id);
    void deleteDepartment(Integer id);

}