package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Integer> {
    Optional<Seller> findByEmail(String email);
    void deleteAllByIdNot(Integer id);
}
