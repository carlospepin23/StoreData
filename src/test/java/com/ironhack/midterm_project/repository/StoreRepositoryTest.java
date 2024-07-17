package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProductRepository productRepository;

    private Store store;

    @BeforeEach
    void setUp() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Juan"));
        employees.add(new Seller("Pedro","pedro@gmail.com",53));

        List<Product> products = new ArrayList<>();
        products.add(new Product("Milk", 1.5, 50));
        products.add(new Product("Coca-Cola", 2.5,10));
        products.add(new Product("Ice", 0.25,100));

        List<Department> departments = new ArrayList<>();
        departments.add(new Department("Food",
                employees,
                products));

        store = new Store("Hugo-Market", "San Juan", departments);

        storeRepository.save(store);
        departmentRepository.saveAll(departments);
        employeeRepository.saveAll(employees);
        productRepository.saveAll(products);
    }

    @Test
    void test(){

    }

//    @Test
//    void testSaveStore() {
//        storeRepository.deleteAll();
//        departmentRepository.deleteAll();
//        employeeRepository.deleteAll();
//        productRepository.deleteAll();
//
//        employeeRepository.save(new Seller("Willy","wonka@yahoo.com",72));
//
//        productRepository.save(new Product("Chocolate", 0.99,253));
//
//        departmentRepository.save(new Department("Sweets",
//                employeeRepository.findAll(),
//                productRepository.findAll()));
//
//        Store newStore = new Store("Mini-Market", "Cuba", departmentRepository.findAll());
//        Store savedStore = storeRepository.save(newStore);
//        assertNotNull(savedStore.getId());
//        assertEquals("Mini-Market", savedStore.getName(), "The store is not saved correctly");
//    }
//
//    @Test
//    void testFindById() {
//        assertTrue(storeRepository.findById(store.getId()).isPresent(), "The store is not found");
//    }
//
//    @Test
//    void testDeleteStore() {
//        storeRepository.delete(store);
//        assertFalse(storeRepository.findById(store.getId()).isPresent(), "The store is not deleted");
//    }
//
//    @AfterEach
//    void tearDown() {
//        storeRepository.deleteAll();
//        departmentRepository.deleteAll();
//        employeeRepository.deleteAll();
//        productRepository.deleteAll();
//    }
}