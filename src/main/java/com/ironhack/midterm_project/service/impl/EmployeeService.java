package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeNameDTO;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Seller;
import com.ironhack.midterm_project.repository.DepartmentRepository;
import com.ironhack.midterm_project.repository.EmployeeRepository;
import com.ironhack.midterm_project.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with id " + id + " not found.");
        return employeeOptional.get();

    }

    @Override
    public void addNewEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findByName(employee.getName());
        if (employeeOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An employee with the name " + employee.getName() + " already exists.");
        }

        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeName(EmployeeNameDTO employeeNameDTO, Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with id " + id + " not found.");
        Employee employee = employeeOptional.get();
        employee.setName(employeeNameDTO.getName());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeInformation(EmployeeDTO employeeDTO, Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with id " + id + " not found.");
        Employee employee = employeeOptional.get();
        employee.setName(employeeDTO.getName());
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with id " + id + " not found.");
        employeeRepository.deleteById(id);
    }

    @Override
    public void deleteAllEmployees() {
//        List<Employee> employees = employeeRepository.findAll();
//        if (employees.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "There are no employees to delete.");
//
//        for (Employee employee : employees) {
//            List<Department> departmentsByEmployeeId=departmentRepository.findAllByEmployeesEmployeeId(employee.getId());
//            for (Department department : departmentsByEmployeeId) {
//                department.getEmployees().remove(employee);
//                departmentRepository.save(department);
//            }
//
//        }
        employeeRepository.deleteAll();

    }
}
