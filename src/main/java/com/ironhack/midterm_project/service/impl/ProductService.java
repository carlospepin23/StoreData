package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.model.Product;
import com.ironhack.midterm_project.repository.ProductRepository;
import com.ironhack.midterm_project.service.interfaces.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductsService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProductName(String name, Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return;
        product.setName(name);
        productRepository.save(product);
    }

    @Override
    public void updateProductPrice(Double price, Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return;
        product.setPrice(price);
        productRepository.save(product);
    }

    @Override
    public void updateProductStock(Integer stock, Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return;
        product.setStock(stock);
        productRepository.save(product);
    }

    @Override
    public void updateProductInformation(Product product, Integer id) {
        Product prevProduct = productRepository.findById(id).orElse(null);
        if (prevProduct == null) return;
        prevProduct.setName(product.getName());
        prevProduct.setPrice(product.getPrice());
        prevProduct.setStock(product.getStock());
        productRepository.save(prevProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
}
