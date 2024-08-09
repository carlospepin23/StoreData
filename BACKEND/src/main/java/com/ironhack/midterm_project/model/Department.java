package com.ironhack.midterm_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @NotBlank(message = "The department must have a name")
    private String name;

    @NotEmpty(message = "The department must have at least one employee")
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;

    @NotEmpty(message = "The department must have at least one product in inventory")
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> inventory;

    @NotNull(message = "The department must belong to a store")
    @JsonBackReference
    @ManyToOne @JoinColumn(name = "store_id")
    private Store store;

    public Department(String name, List<Employee> employees, List<Product> inventory) {
        this.name = name;
        this.employees = employees;
        this.inventory = inventory;
        departmentSetter();
    }

    public void departmentSetter() {
        for (Employee employee : this.employees) {
            employee.setDepartment(this);
        }
        for (Product product : this.inventory) {
            product.setDepartment(this);
        }
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", inventory=" + inventory +
                '}';
    }
}