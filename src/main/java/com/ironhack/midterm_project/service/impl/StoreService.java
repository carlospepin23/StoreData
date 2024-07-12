package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.controller.dto.product_dto.ProductDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerDTO;
import com.ironhack.midterm_project.controller.dto.store_dto.StoreDTO;
import com.ironhack.midterm_project.controller.dto.store_dto.StoreDepartmentsDTO;
import com.ironhack.midterm_project.controller.dto.store_dto.StoreLocationDTO;
import com.ironhack.midterm_project.controller.dto.store_dto.StoreNameDTO;
import com.ironhack.midterm_project.model.*;
import com.ironhack.midterm_project.repository.*;
import com.ironhack.midterm_project.service.interfaces.IStoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements IStoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Integer id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if(storeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Store with id " + id + " not found.");
        return storeOptional.get();

    }

    @Override
    @Transactional // ensures the method is completed or rolled back if there is an error
    public void addNewStore(Store store) {
        Optional<Store> storeOptional = storeRepository.findByName(store.getName());
        if (storeOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store with name " + store.getName() + " already exists.");
        } else {
            for (Department department : store.getDepartments()) {
                Optional<Department> departmentOptional = departmentRepository.findByName(department.getName());
                if (departmentOptional.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with name " + department.getName() + " already exists.");
                } else {
                    // Before saving the department, ensure its employees and products don't already exist
                    for (Employee employee : department.getEmployees()) {
                        Optional<Employee> employeeOptional = employeeRepository.findByName(employee.getName());
                        if (employeeOptional.isPresent()) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An employee with the name " + employee.getName() + " already exists.");
                        } else {
                            // Check if the employee is a Seller and if an email already exists
                            if (employee instanceof Seller seller) {
                                if (sellerRepository.findByEmail(seller.getEmail()).isPresent()) {
                                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A seller with the email " + seller.getEmail() + " already exists.");
                                }
                            }
                        }
                    }
                    for (Product product : department.getInventory()) {
                        Optional<Product> productOptional = productRepository.findByName(product.getName());
                        if (productOptional.isPresent()) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A product with the name " + product.getName() + " already exists.");
                        }
                    }
                    // Save each employee and product before saving the department
                    for(Employee employee : department.getEmployees()) {
                        employeeRepository.save(employee);
                    }
                    for(Product product : department.getInventory()) {
                        productRepository.save(product);
                    }

                    departmentRepository.save(department);
                }
            }
            storeRepository.save(store);
        }
    }

    @Override
    public void updateStoreName(StoreNameDTO storeNameDTO, Integer id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Store with id " + id + " not found.");
        Store store = storeOptional.get();
        store.setName(storeNameDTO.getName());
        storeRepository.save(store);
    }

    @Override
    public void updateStoreLocation(StoreLocationDTO storeLocationDTO, Integer id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Store with id " + id + " not found.");
        Store store = storeOptional.get();
        store.setLocation(storeLocationDTO.getLocation());
        storeRepository.save(store);
    }

    @Override
    public void updateStoreDepartments(StoreDepartmentsDTO storeDepartmentsDTO, Integer id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store with id " + id + " not found.");
        }
        updateStoreDepartmentsHelper(storeDepartmentsDTO.getDepartments());

        Store store = storeOptional.get();
        storeRepository.save(store);
    }

    @Override
    public void updateStoreInformation(StoreDTO storeDTO, Integer id) {
        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Store with id " + id + " not found.");
        }

        Store store = storeOptional.get();

        //NAME
        store.setName(storeDTO.getName());

        //LOCATION
        store.setLocation(storeDTO.getLocation());

        //DEPARTMENTS
        updateStoreDepartmentsHelper(storeDTO.getDepartments());

        storeRepository.save(store);
    }

    @Override //FIX
    public void deleteStore(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public void deleteAllStores() {
        storeRepository.deleteAll();
    }

    private void updateStoreDepartmentsHelper(List<DepartmentDTO> storeDepartmentsDTO){
        for(DepartmentDTO departmentDTO : storeDepartmentsDTO) {
            Optional<Department> departmentOptional = departmentRepository.findByName(departmentDTO.getName());
            if (departmentOptional.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with name " + departmentDTO.getName() + " not found.");
            }

            Department department = departmentOptional.get();
            //NAME
            department.setName(departmentDTO.getName());
            //EMPLOYEES
            for(EmployeeDTO employeeDTO : departmentDTO.getEmployees()) {
                Optional<Employee> employeeOptional = employeeRepository.findByName(employeeDTO.getName());
                if (employeeOptional.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with name " + employeeDTO.getName() + " not found.");
                }

                Employee employee = employeeOptional.get();
                //modify the employee, in this case the employee does not have more attributes

                if (employee instanceof Seller seller) {

                    SellerDTO sellerDTO = (SellerDTO) employeeDTO;
                    seller.setEmail(sellerDTO.getEmail());
                    seller.setSales(sellerDTO.getSales());
                }

                employeeRepository.save(employee);
            }
            //INVENTORY
            for(ProductDTO productDTO : departmentDTO.getInventory()) {
                Optional<Product> productOptional = productRepository.findByName(productDTO.getName());
                if (productOptional.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with name " + productDTO.getName() + " not found.");
                }

                Product product = productOptional.get();
                product.setPrice(productDTO.getPrice());
                product.setStock(productDTO.getStock());

                productRepository.save(product);
            }

            departmentRepository.save(department);

        }
    }
}
