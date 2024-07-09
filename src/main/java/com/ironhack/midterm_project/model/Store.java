package com.ironhack.midterm_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String location;

    @OneToMany
    private List<Department> departments;


    public Store(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Store(String name, String location, List<Department> departments) {
        this.name = name;
        this.location = location;
        this.departments = departments;
    }
}