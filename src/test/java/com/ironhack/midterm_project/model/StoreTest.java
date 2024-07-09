//package com.ironhack.midterm_project.model;
//
//import com.ironhack.midterm_project.repository.DepartmentRepository;
//import com.ironhack.midterm_project.repository.EmployeeRepository;
//import com.ironhack.midterm_project.repository.ProductRepository;
//import com.ironhack.midterm_project.repository.StoreRepository;
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
//class StoreTest {
//
//    @Autowired
//    StoreRepository storeRepository;
//    @Autowired
//    DepartmentRepository departmentRepository;
//    @Autowired
//    EmployeeRepository employeeRepository;
//    @Autowired
//    ProductRepository productRepository;
//
////    @BeforeEach
////    void setUp() {
////        Employee e1 = new Employee("Juan");
////        Employee e2 = new Seller("Pedro","pedro@gmail.com",53);
////        employeeRepository.save(e1);
////        employeeRepository.save(e2);
////
////        List<Employee> employees = new ArrayList<>();
////        employees.add(e1);
////        employees.add(e2);
////
////        Product p1 = new Product("Coca-Cola", 2.5,10);
////        Product p2 = new Product("Bread", 1.0,100);
////        productRepository.save(p1);
////        productRepository.save(p2);
////
////        List<Product> products = new ArrayList<>();
////        products.add(p1);
////        products.add(p2);
////
////        Department d1 = new Department("Food",employees,products);
////        departmentRepository.save(d1);
////
////        List<Department> departments = new ArrayList<>();
////        departments.add(d1);
////
////        Store store = new Store("Hugo-Market","San Juan",departments);
////        storeRepository.save(store);
////    }
//
//    @Test
//    void storeIsSavedCorrectly() {
//        List<Store> stores = storeRepository.findAll();
//        assertEquals(1, stores.size(), "Expected one store to be saved");
//
//        Store retrievedStore = stores.get(0);
//        assertEquals("Hugo-Market", retrievedStore.getName(), "Store name does not match");
//        assertEquals("San Juan", retrievedStore.getLocation(), "Store location does not match");
//        assertNotNull(retrievedStore.getDepartments(), "Departments should not be null");
//        assertEquals(1, retrievedStore.getDepartments().size(), "Expected one department in the store");
//    }
//
//    @AfterEach
//    void tearDown() {
//        storeRepository.deleteAll();
//        departmentRepository.deleteAll();
//        employeeRepository.deleteAll();
//        productRepository.deleteAll();
//    }
//}