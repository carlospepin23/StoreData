package com.ironhack.midterm_project.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    @ManyToOne
    private Department department;

//    public Employee(String name, Department department) {
//        this.name = name;
//        this.department = department;
//    }
}