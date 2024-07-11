package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.controller.dto.EmployeeNameDTO;
import com.ironhack.midterm_project.controller.interfaces.IEmployeeController;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.service.impl.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController implements IEmployeeController {

    @Autowired
    EmployeeService employeeService;

    //  ****************************************************  GET  ****************************************************
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    //  ***************************************************  POST  ****************************************************
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEmployee(@RequestBody @Valid Employee employee) {
        employeeService.addNewEmployee(employee);
    }

    //  ***************************************************  PATCH  ****************************************************
    @PatchMapping("/employees/{id}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeName(@RequestBody @Valid EmployeeNameDTO employeeNameDTO, @PathVariable Integer id) {
        employeeService.updateEmployeeName(employeeNameDTO, id);
    }

    //  ***************************************************  PUT  ****************************************************


    //  ***************************************************  DELETE  ****************************************************
    @DeleteMapping("/employees/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }
}
