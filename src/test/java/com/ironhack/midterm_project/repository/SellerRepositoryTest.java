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
//class SellerRepositoryTest {
//
//    @Autowired
//    SellerRepository sellerRepository;
//    Seller seller;
//
//    @BeforeEach
//    void setUp() {
//        seller = new Seller("Thomas Tank","tt@bing.com",16);
//        sellerRepository.save(seller);
//    }
//
//    @Test
//    void testSaveSeller() {
//        Seller newSeller = new Seller("New Seller", "new@seller.com", 20);
//        Seller savedSeller = sellerRepository.save(newSeller);
//        assertNotNull(savedSeller.getId());
//        assertEquals("New Seller", savedSeller.getName());
//    }
//
//    @Test
//    void testFindById() {
//        assertTrue(sellerRepository.findById(seller.getId()).isPresent());
//    }
//
//    @Test
//    void testDeleteSeller() {
//        sellerRepository.delete(seller);
//        assertFalse(sellerRepository.findById(seller.getId()).isPresent());
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//}