package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Optional<Employee> findByName(String name);
    void deleteAllByIdNot(Integer id);
}
