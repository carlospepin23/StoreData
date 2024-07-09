package com.ironhack.midterm_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany
    private List<Employee> employees;

    @OneToMany
    private List<Product> inventory;

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, List<Employee> employees, List<Product> inventory) {
        this.name = name;
        this.employees = employees;
        this.inventory = inventory;
    }
}