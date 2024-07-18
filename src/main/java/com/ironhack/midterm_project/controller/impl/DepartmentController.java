package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.DTO.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentEmployeesDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentInventoryDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentNameDTO;
import com.ironhack.midterm_project.controller.interfaces.IDepartmentController;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.service.impl.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DepartmentController implements IDepartmentController {

    @Autowired
    DepartmentService departmentService;

    //  ****************************************************  GET  ****************************************************
    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable Integer id) {
        return departmentService.getDepartmentById(id);
    }

    //  ***************************************************  POST  ****************************************************
    @PostMapping("/departments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewDepartment(@RequestBody @Valid Department department) {
        departmentService.addNewDepartment(department);
    }

    //  ***************************************************  PATCH  ****************************************************
    @PatchMapping("/departments/{id}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDepartmentName(@RequestBody @Valid DepartmentNameDTO departmentNameDTO, @PathVariable Integer id) {
        departmentService.updateDepartmentName(departmentNameDTO, id);
    }

    @PatchMapping("/departments/{id}/employees")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDepartmentEmployees(@RequestBody @Valid DepartmentEmployeesDTO departmentEmployeesDTO, @PathVariable Integer id) {
        departmentService.updateDepartmentEmployees(departmentEmployeesDTO, id);
    }

    @PatchMapping("/departments/{id}/inventory")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDepartmentInventory(@RequestBody @Valid DepartmentInventoryDTO departmentInventoryDTO, @PathVariable Integer id) {
        departmentService.updateDepartmentInventory(departmentInventoryDTO, id);
    }

    //  ***************************************************  PUT  ****************************************************
    @PutMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDepartmentInformation(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable Integer id) {
        departmentService.updateDepartmentInformation(departmentDTO, id);
    }

    //  ***************************************************  DELETE  ****************************************************
    @DeleteMapping("/departments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
    }

    @DeleteMapping("/departments/all/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllDepartmentsExceptId(@PathVariable Integer id) {
        departmentService.deleteAllDepartmentsExceptId(id);
    }
}
