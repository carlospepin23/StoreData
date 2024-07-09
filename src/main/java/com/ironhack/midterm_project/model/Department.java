package com.ironhack.midterm_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotBlank(message = "The department must have a name")
    private String name;

    @NotEmpty(message = "The department must have at least one employee")
    @OneToMany
    private List<Employee> employees;

    @NotEmpty(message = "The department must have at least one product in inventory")
    @OneToMany
    private List<Product> inventory;

    public Department(String name, List<Employee> employees, List<Product> inventory) {
        this.name = name;
        this.employees = employees;
        this.inventory = inventory;
    }
}