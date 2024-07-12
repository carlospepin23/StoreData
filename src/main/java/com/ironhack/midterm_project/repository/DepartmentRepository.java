package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    Optional<Department> findByName(String name);
}
