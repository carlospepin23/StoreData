package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        employeeRepository.save(new Employee("Juan"));
        employeeRepository.save(new Seller("Pedro","pedro@gmail.com",53));

        productRepository.save(new Product("Coca-Cola", 2.5,10));
        productRepository.save(new Product("Bread", 1.0,100));

        departmentRepository.save(new Department("Food",
                employeeRepository.findAll(),
                productRepository.findAll()));

        store = new Store("Hugo-Market",
                "San Juan",
                departmentRepository.findAll());
        storeRepository.save(store);
    }

    @Test
    void testSaveStore() {
        storeRepository.deleteAll();
        departmentRepository.deleteAll();
        employeeRepository.deleteAll();
        productRepository.deleteAll();

        employeeRepository.save(new Seller("Willy","wonka@yahoo.com",72));

        productRepository.save(new Product("Chocolate", 0.99,253));

        departmentRepository.save(new Department("Sweets",
                employeeRepository.findAll(),
                productRepository.findAll()));

        Store newStore = new Store("Mini-Market", "Cuba", departmentRepository.findAll());
        Store savedStore = storeRepository.save(newStore);
        assertNotNull(savedStore.getId());
        assertEquals("Mini-Market", savedStore.getName(), "The store is not saved correctly");
    }

    @Test
    void testFindById() {
        assertTrue(storeRepository.findById(store.getId()).isPresent(), "The store is not found");
    }

    @Test
    void testDeleteStore() {
        storeRepository.delete(store);
        assertFalse(storeRepository.findById(store.getId()).isPresent(), "The store is not deleted");
    }

    @AfterEach
    void tearDown() {
        storeRepository.deleteAll();
        departmentRepository.deleteAll();
        employeeRepository.deleteAll();
        productRepository.deleteAll();
    }
}