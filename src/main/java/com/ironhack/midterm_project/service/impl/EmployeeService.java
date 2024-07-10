package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.repository.EmployeeRepository;
import com.ironhack.midterm_project.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        return employeeOptional.orElse(null);
    }

    @Override
    public void addNewEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeName(String name, Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) return;
        Employee employee = employeeOptional.get();
        employee.setName(name);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeInformation(Employee employee, Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) return;
        employee.setName(employee.getName());
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) return;
        employeeRepository.deleteById(id);
    }
}
