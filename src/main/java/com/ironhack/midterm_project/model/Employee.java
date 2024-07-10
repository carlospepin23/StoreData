package com.ironhack.midterm_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The employee must have a name")
    private String name;

    public Employee(String name) {
        this.name = name;
    }

//    @ManyToOne
//    private Department department;

//    public Employee(String name, Department department) {
//        this.name = name;
//        this.department = department;
//    }
}