package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByName(String name);
    void deleteAllByIdNot(Integer id);
}
