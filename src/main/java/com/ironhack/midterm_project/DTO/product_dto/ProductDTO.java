package com.ironhack.midterm_project.DTO.product_dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    @NotBlank(message = "The product must have a name")
    private String name;
    @NotNull(message = "The product must have a price")
    @Min(value = 0, message = "The price must be greater than zero (0)")
    private Double price;
    @NotNull(message = "The product stock cannot be null")
    @Min(value = 0, message = "The stock must be equal or greater than zero (0)")
    private Integer stock;
//
//    private DepartmentDTO department;
//
//    public ProductDTO(String name, Double price, Integer stock) {
//        this.name = name;
//        this.price = price;
//        this.stock = stock;
//    }
}
