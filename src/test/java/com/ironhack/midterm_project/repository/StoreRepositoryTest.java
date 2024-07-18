package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    StoreRepository storeRepository;

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
    }

    @AfterEach
    void tearDown() {
        storeRepository.deleteAll();
    }

//    @Test
//    void test(){
//
//    }

    @Test
    void testSaveStore() {

        List<Employee> employees = new ArrayList<>();
        Seller seller = new Seller("Willy","wonka@yahoo.com",72);
        employees.add(seller);

        List<Product> products = new ArrayList<>();
        Product product = new Product("Chocolate", 0.99,253);
        products.add(product);


        List<Department> departments = new ArrayList<>();
        Department department= new Department("Sweets", employees, products);
        departments.add(department);

        Store newStore = new Store("Mini-Market", "Cuba", departments);
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

}