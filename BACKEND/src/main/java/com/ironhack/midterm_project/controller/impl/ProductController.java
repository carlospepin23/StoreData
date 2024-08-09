package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.DTO.product_dto.*;
import com.ironhack.midterm_project.controller.interfaces.IProductController;
import com.ironhack.midterm_project.model.Product;
import com.ironhack.midterm_project.service.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController implements IProductController {

    @Autowired
    ProductService productService;

    //  ****************************************************  GET  ****************************************************
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    //  ***************************************************  POST  ****************************************************
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewProduct(@RequestBody @Valid Product product) {
        productService.addNewProduct(product);
    }

    //  ***************************************************  PATCH  ****************************************************
    @PatchMapping("/products/{id}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductName(@RequestBody @Valid ProductNameDTO productNameDTO, @PathVariable Integer id) {
        productService.updateProductName(productNameDTO, id);
    }

    @PatchMapping("/products/{id}/price")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductPrice(@RequestBody @Valid ProductPriceDTO productPriceDTO, @PathVariable Integer id) {
        productService.updateProductPrice(productPriceDTO, id);
    }

    @PatchMapping("/products/{id}/stock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductStock(@RequestBody @Valid ProductStockDTO productStockDTO, @PathVariable Integer id) {
        productService.updateProductStock(productStockDTO, id);
    }

    @PatchMapping("/products/{id}/department")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductDepartment(@RequestBody @Valid ProductDepartmentDTO productDepartmentDTO, @PathVariable Integer id) {
        productService.updateProductDepartment(productDepartmentDTO,id);
    }

    //  ***************************************************  PUT  ****************************************************
    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductInformation(@RequestBody @Valid ProductDTO productDTO, @PathVariable Integer id) {
        productService.updateProductInformation(productDTO, id);
    }

    //  ***************************************************  DELETE  ****************************************************
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @DeleteMapping("/products/all/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllProductsExceptId(@PathVariable Integer id) {
        productService.deleteAllProductsExceptId(id);
    }
}
