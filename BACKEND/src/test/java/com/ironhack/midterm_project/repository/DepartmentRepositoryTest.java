//package com.ironhack.midterm_project.repository;
//
//import com.ironhack.midterm_project.model.Department;
//import com.ironhack.midterm_project.model.Employee;
//import com.ironhack.midterm_project.model.Product;
//import com.ironhack.midterm_project.model.Seller;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class DepartmentRepositoryTest {
//
//    @Autowired
//    private DepartmentRepository departmentRepository;
//    @Autowired
//    private EmployeeRepository employeeRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    private Department department;
//
//    @BeforeEach
//    void setUp() {
//
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee("Thomas Tank"));
//        employees.add(new Seller("Titan Ortiz","tortiz@hotmail.com",12));
//        employeeRepository.saveAll(employees);
//
//        List<Product> products = new ArrayList<>();
//        products.add(new Product("Aspirin", 5.0, 10));
//        productRepository.saveAll(products);
//
//        department = new Department("Pharmacy",
//                employees,
//                products);
//
//        departmentRepository.save(department);
//    }
//
//    @Test
//    void test(){
//
//    }
//
//    @Test
//    void testSaveDepartment() {
//       departmentRepository.deleteAll();
//       employeeRepository.deleteAll();
//       productRepository.deleteAll();
//
//       List<Employee> employees = new ArrayList<>();
//       employees.add(new Employee("Juan James"));
//       employeeRepository.saveAll(employees);
//
//       List<Product> products = new ArrayList<>();
//         products.add(new Product("Tire", 200.0, 17));
//       productRepository.saveAll(products);
//
//       Department newDepartment = new Department("Automotive",
//               employees,
//               products);
//
//        Department savedDepartment = departmentRepository.save(newDepartment);
//        assertNotNull(savedDepartment.getId());
//        assertEquals("Automotive", savedDepartment.getName(), "The department is not saved correctly");
//    }
//
//    @Test
//    void testFindById() {
//        assertTrue(departmentRepository.findById(department.getId()).isPresent(), "The department is not found");
//    }
//
//    @Test
//    void testDeleteDepartment() {
//        departmentRepository.delete(department);
//        assertFalse(departmentRepository.findById(department.getId()).isPresent(), "The department is not deleted");
//    }
//
//    @AfterEach
//    void tearDown() {
//        departmentRepository.deleteAll();
//        employeeRepository.deleteAll();
//        productRepository.deleteAll();
//
//    }
//}