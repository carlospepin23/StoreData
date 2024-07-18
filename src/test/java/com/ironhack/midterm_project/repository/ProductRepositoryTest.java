//package com.ironhack.midterm_project.repository;
//
//import com.ironhack.midterm_project.model.Product;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class ProductRepositoryTest {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    private Product product;
//
//    @BeforeEach
//    void setUp() {
//        product = new Product("Test Product", 9.3, 46);
//        productRepository.save(product);
//    }
//
//
//    @Test
//    void testSaveProduct() {
//        Product newProduct = new Product("New Product", 30.0, 150);
//        Product savedProduct = productRepository.save(newProduct);
//        assertNotNull(savedProduct.getId());
//        assertEquals("New Product", savedProduct.getName(), "The product is not saved correctly");
//    }
//
//    @Test
//    void testFindById() {
//        assertTrue(productRepository.findById(product.getId()).isPresent(), "The product is not found");
//    }
//
//    @Test
//    void testDeleteProduct() {
//        productRepository.delete(product);
//        assertFalse(productRepository.findById(product.getId()).isPresent(), "The product is not deleted");
//    }
//
//    @AfterEach
//    void tearDown() {
//        productRepository.deleteAll();
//    }
//}