package com.ironhack.midterm_project.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("SELLER")
public class Seller extends Employee {
    @NotNull(message = "The email must not be null")
    @Email(message = "The email must be valid")
    private String email;
    @NotNull(message = "The sales must not be null")
    private Integer sales;

    public Seller(String name, String email, Integer sales) {
        super(name);
        this.email = email;
        this.sales = sales;
    }

}