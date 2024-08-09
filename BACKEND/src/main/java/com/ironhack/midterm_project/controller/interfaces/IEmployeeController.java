package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDepartmentDTO;
import com.ironhack.midterm_project.DTO.employee_dto.EmployeeNameDTO;
import com.ironhack.midterm_project.model.Employee;

import java.util.List;

public interface IEmployeeController {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer id);
    void addNewEmployee(Employee employee);
    void updateEmployeeName(EmployeeNameDTO employeeNameDTO, Integer id);
    void updateEmployeeDepartment(EmployeeDepartmentDTO employeeDepartmentDTO, Integer id);
    void updateEmployeeInformation(EmployeeDTO employeeDTO, Integer id);
    void deleteEmployee(Integer id);
    void deleteAllEmployeesExceptId(Integer id);
}
