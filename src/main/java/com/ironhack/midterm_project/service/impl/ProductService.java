package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.controller.dto.product_dto.ProductDTO;
import com.ironhack.midterm_project.controller.dto.product_dto.ProductNameDTO;
import com.ironhack.midterm_project.controller.dto.product_dto.ProductPriceDTO;
import com.ironhack.midterm_project.controller.dto.product_dto.ProductStockDTO;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Product;
import com.ironhack.midterm_project.repository.ProductRepository;
import com.ironhack.midterm_project.service.interfaces.IProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with id " + id + " not found.");
        return productOptional.get();
    }

    @Override
    public void addNewProduct(Product product) {
        Optional<Product> productOptional = productRepository.findByName(product.getName());
        if (productOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A product with the name " + product.getName() + " already exists.");
        }

        productRepository.save(product);
    }

    @Override
    public void updateProductName(ProductNameDTO productNameDTO, Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with id " + id + " not found.");
        product.setName(productNameDTO.getName());
        productRepository.save(product);
    }

    @Override
    public void updateProductPrice(ProductPriceDTO productPriceDTO, Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with id " + id + " not found.");
        product.setPrice(productPriceDTO.getPrice());
        productRepository.save(product);
    }

    @Override
    public void updateProductStock(ProductStockDTO productStockDTO, Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with id " + id + " not found.");
        product.setStock(productStockDTO.getStock());
        productRepository.save(product);
    }

    @Override
    public void updateProductInformation(ProductDTO productDTO, Integer id) {
        Product prevProduct = productRepository.findById(id).orElse(null);
        if (prevProduct == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with id " + id + " not found.");
        prevProduct.setName(productDTO.getName());
        prevProduct.setPrice(productDTO.getPrice());
        prevProduct.setStock(productDTO.getStock());
        productRepository.save(prevProduct);
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with id " + id + " not found.");
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllProduct() {
        productRepository.deleteAll();
    }
}
