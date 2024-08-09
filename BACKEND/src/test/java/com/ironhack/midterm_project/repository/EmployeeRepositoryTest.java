//package com.ironhack.midterm_project.repository;
//
//import com.ironhack.midterm_project.model.Employee;
//import com.ironhack.midterm_project.model.Seller;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class EmployeeRepositoryTest {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    private Employee employee;
//    private Seller seller;
//
//    @BeforeEach
//    void setUp() {
//        employee = new Employee("Thomas Tank");
//        seller = new Seller("Gabriel Gonzales", "gago@example.com", 30);
//        employeeRepository.save(employee);
//        employeeRepository.save(seller);
//    }
//
//    @Test
//    void test(){
//
//    }
//
//    @Test
//    void testSaveEmployee() {
//        Employee newEmployee = new Employee("New Employee");
//        Employee savedEmployee = employeeRepository.save(newEmployee);
//        assertNotNull(savedEmployee.getId());
//        assertEquals("New Employee", savedEmployee.getName(), "The employee is not saved correctly");
//    }
//
//    @Test
//    void testSaveSeller() {
//        Seller newSeller = new Seller("New Seller", "new@example.com", 40);
//        Seller savedSeller = employeeRepository.save(newSeller);
//        assertNotNull(savedSeller.getId());
//        assertEquals("New Seller", savedSeller.getName(), "The seller is not saved correctly");
//    }
//
//    @Test
//    void testFindById() {
//        assertTrue(employeeRepository.findById(employee.getId()).isPresent(), "The employee is not found");
//        assertTrue(employeeRepository.findById(seller.getId()).isPresent(), "The seller is not found");
//    }
//
//    @Test
//    void testDeleteEmployee() {
//        employeeRepository.delete(employee);
//        assertFalse(employeeRepository.findById(employee.getId()).isPresent(), "The employee is not deleted");
//    }
//
//    @AfterEach
//    void tearDown() {
//        employeeRepository.deleteAll();
//    }
//}