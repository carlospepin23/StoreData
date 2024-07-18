package com.ironhack.midterm_project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    @NotBlank(message = "The store must have a name")
    private String name;
    @NotBlank(message = "The store must have a location")
    private String location;


    @NotEmpty(message = "The store must have at least one department")
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Department> departments;

    public Store(String name, String location, List<Department> departments) {
        this.name = name;
        this.location = location;
        this.departments = departments;
        storeSetter();
    }

    public void storeSetter() {
        for (Department department : departments) {
            department.setStore(this);
        }
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", departments=" + departments +
                '}';
    }
}