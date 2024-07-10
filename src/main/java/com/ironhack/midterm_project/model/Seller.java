package com.ironhack.midterm_project.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("SELLER")
public class Seller extends Employee {
    @Email(message = "The email must be valid")
    private String email;

    private Integer sales;

    public Seller(String name, String email, Integer sales) {
        super(name);
        this.email = email;
        this.sales = sales;
    }

}