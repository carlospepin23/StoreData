package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByName(String name);
}
