package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.controller.dto.EmployeeNameDTO;
import com.ironhack.midterm_project.model.Employee;

import java.util.List;

public interface IEmployeeController {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Integer id);
    void addNewEmployee(Employee employee);
    void updateEmployeeName(EmployeeNameDTO employeeNameDTO, Integer id);
    void deleteEmployee(Integer id);
}
