package com.ironhack.midterm_project.service.interfaces;

import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Product;

import java.util.List;

public interface IProductsService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    void addNewProduct(Product product);
    void updateProductName(String name, Integer id);
    void updateProductPrice(Double price, Integer id);
    void updateProductStock(Integer stock, Integer id);
    void updateProductInformation(Product product, Integer id);
    void deleteProduct(Integer id);

}