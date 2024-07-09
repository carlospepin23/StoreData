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
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "The store must have a name")
    private String name;
    @NotBlank(message = "The store must have a location")
    private String location;

    @NotEmpty(message = "The store must have at least one department")
    @OneToMany
    private List<Department> departments;


//    public Store(String name, String location) {
//        this.name = name;
//        this.location = location;
//    }

    public Store(String name, String location, List<Department> departments) {
        this.name = name;
        this.location = location;
        this.departments = departments;
    }
}