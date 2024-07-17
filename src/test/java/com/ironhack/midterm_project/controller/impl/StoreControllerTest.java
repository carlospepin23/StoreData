package com.ironhack.midterm_project.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midterm_project.model.*;
import com.ironhack.midterm_project.repository.DepartmentRepository;
import com.ironhack.midterm_project.repository.EmployeeRepository;
import com.ironhack.midterm_project.repository.ProductRepository;
import com.ironhack.midterm_project.repository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class StoreControllerTest {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    Store store;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        List<Employee> employees = new ArrayList<>();
        employees.add(new Seller("Gus Fring","gfring@gmail.com",53000));

        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product("Chicken", 1.5, 999));
        inventory.add(new Product("Coca-Cola", 2.0,100));
        inventory.add(new Product("Fries", 0.5,100));

        List<Department> departments = new ArrayList<>();
        departments.add(new Department("Kitchen",
                employees,
                inventory));

        store=new Store("Charly's Chicken", "Campo Rico, Carolina, PR", departments);

        storeRepository.save(store);
        departmentRepository.saveAll(departments);
        employeeRepository.saveAll(employees);
        productRepository.saveAll(inventory);
    }

    @AfterEach
    void tearDown() {
        Optional<Store>storeOptional=storeRepository.findByName("Charly's Chicken");
        assertTrue(storeOptional.isPresent());
        Store store=storeOptional.get();

        storeRepository.deleteById(store.getId());

    }

    @Test
    void getAllStores_validRequest_allCourses() throws Exception{
        MvcResult mvcResult = mockMvc.perform(get("/api/stores"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Colmado A-Peso"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Hugo-Market"));

    }

    @Test
    void getStoreById_validRequest_correctCourse() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/stores/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Colmado A-Peso"));

    }

    @Test
    void addNewStore_validBody_storeAdded() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Matias"));

        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product("Gatorade", 2.0, 100));
        inventory.add(new Product("Celsius", 3.0,100));

        List<Department> departments = new ArrayList<>();
        departments.add(new Department("Main Lobby",
                employees,
                inventory));

        store=new Store("Planet Fitness", "Hato Rey", departments);

        String body = objectMapper.writeValueAsString(store);

        mockMvc.perform(post("/api/stores").content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(storeRepository.findAll().toString().contains("Planet Fitness"));
        Optional<Store>storeOptional=storeRepository.findByName("Planet Fitness");
        assertTrue(storeOptional.isPresent());
        storeRepository.deleteById(storeOptional.get().getId());

    }

//    @Test
//    void updateStoreDepartments() {
//    }
//
//    @Test
//    void updateStoreInformation() {
//    }
//
//    @Test
//    void deleteStore() {
//    }
//
//    @Test
//    void deleteAllStores() {
//    }
//
//    @Test
//    void getAllStores() {
//    }
//
//    @Test
//    void getStoreById() {
//    }
//
//    @Test
//    void addNewStore() {
//    }
//
//    @Test
//    void updateStoreName() {
//    }
//
//    @Test
//    void updateStoreLocation() {
//    }
//
//    @Test
//    void testUpdateStoreDepartments() {
//    }
//
//    @Test
//    void testUpdateStoreInformation() {
//    }
//
//    @Test
//    void testDeleteStore() {
//    }
//
//    @Test
//    void testDeleteAllStores() {
//    }
}