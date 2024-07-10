package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Product;
import com.ironhack.midterm_project.repository.DepartmentRepository;
import com.ironhack.midterm_project.service.interfaces.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void addNewDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override //PATCH
    public void updateDepartmentName(String name, Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) return;
        department.setName(name);
        departmentRepository.save(department);
    }

    @Override //PATCH
    public void updateDepartmentEmployees(List<Employee> employees, Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) return;
        department.setEmployees(employees);
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartmentInventory(List<Product> inventory, Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if (department == null) return;
        department.setInventory(inventory);
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartmentInformation(Department department, Integer id) {
        Department prevDepartment = departmentRepository.findById(id).orElse(null);
        if (prevDepartment == null) return;
        prevDepartment.setName(department.getName());
        prevDepartment.setEmployees(department.getEmployees());
        prevDepartment.setInventory(department.getInventory());
        departmentRepository.save(prevDepartment);
    }

    @Override
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }
}
