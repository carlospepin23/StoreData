package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.DTO.product_dto.ProductDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductNameDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductPriceDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductStockDTO;
import com.ironhack.midterm_project.model.Product;

import java.util.List;

public interface IProductController {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    void addNewProduct(Product product);
    void updateProductName(ProductNameDTO productNameDTO, Integer id);
    void updateProductPrice(ProductPriceDTO productPriceDTO, Integer id);
    void updateProductStock(ProductStockDTO productStockDTO, Integer id);
    void updateProductInformation(ProductDTO productDTO, Integer id);
    void deleteProduct(Integer id);
    void deleteAllProductsExceptId(Integer id);
}
