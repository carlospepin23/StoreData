package com.ironhack.midterm_project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @NotBlank(message = "The product must have a name")
    private String name;
    @NotNull(message = "The product must have a price")
    @Min(value = 0, message = "The price must be greater than zero (0)")
    private Double price;
    @NotNull(message = "The product stock cannot be null")
    @Min(value = 0, message = "The stock must be equal or greater than zero (0)")
    private Integer stock;

    @JsonBackReference
    @ManyToOne @JoinColumn(name = "department_id")
    private Department department;

    public Product(String name, Double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}
