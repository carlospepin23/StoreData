package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.DTO.product_dto.ProductDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductNameDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductPriceDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductStockDTO;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Product;
import com.ironhack.midterm_project.repository.DepartmentRepository;
import com.ironhack.midterm_project.repository.ProductRepository;
import com.ironhack.midterm_project.service.interfaces.IProductsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService implements IProductsService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return exceptionMsgProduct(id);
    }

    @Override
    public void addNewProduct(Product product) {
        alreadyExists(product);
        Optional<Department> departmentOptional = departmentRepository.findById(product.getDepartment().getId());
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id " + product.getDepartment().getId() + " not found.");
        }

        productRepository.save(product);
    }

    @Override
    public void updateProductName(ProductNameDTO productNameDTO, Integer id) {
        Product product = exceptionMsgProduct(id);
        product.setName(productNameDTO.getName());
        productRepository.save(product);
    }

    @Override
    public void updateProductPrice(ProductPriceDTO productPriceDTO, Integer id) {
        Product product = exceptionMsgProduct(id);
        product.setPrice(productPriceDTO.getPrice());
        productRepository.save(product);
    }

    @Override
    public void updateProductStock(ProductStockDTO productStockDTO, Integer id) {
        Product product = exceptionMsgProduct(id);
        product.setStock(productStockDTO.getStock());
        productRepository.save(product);
    }

    @Override
    public void updateProductInformation(ProductDTO productDTO, Integer id) {
        Product product = exceptionMsgProduct(id);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        productRepository.save(product);
    }

    @Override
    public void updateProductInformation(ProductDTO productDTO, String name) {
        Product product = exceptionMsgProduct(name);
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        if(productRepository.findAll().size()==1) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Cannot delete the only product in the database.");

        exceptionMsgProduct(id);
        productRepository.deleteById(id);

        departmentRepository.flush();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            if (department.getInventory().isEmpty()) {
                departmentRepository.delete(department);
            }
        }
    }

    @Override
    public void deleteAllProductsExceptId(Integer id) {
        exceptionMsgProduct(id);
        productRepository.deleteAllByIdNot(id);

        departmentRepository.flush();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            if (department.getInventory().isEmpty()) {
                departmentRepository.delete(department);
            }
        }

    }

    private Product exceptionMsgProduct(Integer id){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No products found.");

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with id " + id + " not found.");

        return productOptional.get();
    }

    private Product exceptionMsgProduct(String name){
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No products found.");

        Optional<Product> productOptional = productRepository.findByName(name);
        if (productOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Product with name " + name + " not found.");

        return productOptional.get();
    }
    public void alreadyExists(Product product){
        Optional<Product> productOptional = productRepository.findByName(product.getName());
        if (productOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product " + product.getName() + " already exists.");
        }
    }
}
