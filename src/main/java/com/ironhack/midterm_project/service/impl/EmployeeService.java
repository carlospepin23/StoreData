package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDepartmentDTO;
import com.ironhack.midterm_project.DTO.employee_dto.EmployeeNameDTO;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Seller;
import com.ironhack.midterm_project.repository.DepartmentRepository;
import com.ironhack.midterm_project.repository.EmployeeRepository;
import com.ironhack.midterm_project.service.interfaces.IEmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        return exceptionMsgEmployee(id);
    }

    @Override
    public void addNewEmployee(Employee employee) {
        alreadyExists(employee);
        Optional<Department> departmentOptional = departmentRepository.findById(employee.getDepartment().getId());
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id " + employee.getDepartment().getId() + " not found.");
        }

        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeName(EmployeeNameDTO employeeNameDTO, Integer id) {
        Employee employee = exceptionMsgEmployee(id);
        employee.setName(employeeNameDTO.getName());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeDepartment(EmployeeDepartmentDTO employeeDepartmentDTO, Integer id) {
        Employee employee = exceptionMsgEmployee(id);
        Optional<Department> departmentOptional = departmentRepository.findById(employeeDepartmentDTO.getDepartment().getId());
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id " + employeeDepartmentDTO.getDepartment().getId() + " not found.");
        }

        employee.setDepartment(departmentOptional.get());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeInformation(EmployeeDTO employeeDTO, Integer id) {
        Employee employee = exceptionMsgEmployee(id);
        employee.setName(employeeDTO.getName());
        Optional<Department> departmentOptional = departmentRepository.findById(employeeDTO.getDepartment().getId());
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id " + employeeDTO.getDepartment().getId() + " not found.");
        }
        employee.setDepartment(departmentOptional.get());
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeInformation(EmployeeDTO employeeDTO, String name) {
        Employee employee = exceptionMsgEmployee(name);
        employee.setName(employeeDTO.getName());
        Optional<Department> departmentOptional = departmentRepository.findById(employeeDTO.getDepartment().getId());
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id " + employeeDTO.getDepartment().getId() + " not found.");
        }
        employee.setDepartment(departmentOptional.get());
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        if(employeeRepository.findAll().size()==1){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Cannot delete the only employee in the database.");
        }

        exceptionMsgEmployee(id);
        employeeRepository.deleteById(id);

        departmentRepository.flush();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            if (department.getEmployees().isEmpty()) {
                departmentRepository.delete(department);
            }
        }
    }

    @Override
    public void deleteAllEmployeesExceptId(Integer id) {
        exceptionMsgEmployee(id);
        employeeRepository.deleteAllByIdNot(id);

        departmentRepository.flush();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            if (department.getEmployees().isEmpty()) {
                departmentRepository.delete(department);
            }
        }
    }

    private Employee exceptionMsgEmployee(Integer id){
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No employees found.");

        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with id " + id + " not found.");

        return employeeOptional.get();
    }

    private Employee exceptionMsgEmployee(String name){
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No employees found.");

        Optional<Employee> employeeOptional = employeeRepository.findByName(name);
        if (employeeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Employee with name " + name + " not found.");

        return employeeOptional.get();
    }

    public void alreadyExists(Employee employee){
        Optional<Employee> employeeOptional = employeeRepository.findByName(employee.getName());
        if (employeeOptional.isPresent()) {
            if(employee instanceof Seller seller){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The seller " + seller.getName() + " already exists.");
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The employee " + employee.getName() + " already exists.");
        }
    }

}
