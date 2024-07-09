package com.ironhack.midterm_project.repository;

import com.ironhack.midterm_project.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
}
