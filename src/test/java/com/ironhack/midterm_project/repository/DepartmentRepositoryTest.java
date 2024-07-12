package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Product;
import com.ironhack.midterm_project.model.Seller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProductRepository productRepository;

    private Department department;

    @BeforeEach
    void setUp() {
        employeeRepository.save(new Employee("Thomas Tank"));
        employeeRepository.save(new Seller("Titan Ortiz","tortiz@hotmail.com",12));
        productRepository.save(new Product("Aspirin", 5.0, 10));

        department = new Department("Pharmacy",
                employeeRepository.findAll(),
                productRepository.findAll());

        departmentRepository.save(department);
    }

    @Test
    void test(){

    }

    @Test
    void testSaveDepartment() {
        departmentRepository.deleteAll();
        employeeRepository.deleteAll();
        productRepository.deleteAll();

       employeeRepository.save(new Employee("Juan James"));
       productRepository.save(new Product("Tire", 200.0, 17));
       Department newDepartment = new Department("Automotive",
               employeeRepository.findAll(),
               productRepository.findAll());

        Department savedDepartment = departmentRepository.save(newDepartment);
        assertNotNull(savedDepartment.getId());
        assertEquals("Automotive", savedDepartment.getName(), "The department is not saved correctly");
    }

    @Test
    void testFindById() {
        assertTrue(departmentRepository.findById(department.getId()).isPresent(), "The department is not found");
    }

    @Test
    void testDeleteDepartment() {
        departmentRepository.delete(department);
        assertFalse(departmentRepository.findById(department.getId()).isPresent(), "The department is not deleted");
    }

    @AfterEach
    void tearDown() {
        departmentRepository.deleteAll();
        employeeRepository.deleteAll();
        productRepository.deleteAll();

    }
}