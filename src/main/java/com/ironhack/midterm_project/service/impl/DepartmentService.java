package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentEmployeesDTO;
import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentInventoryDTO;
import com.ironhack.midterm_project.controller.dto.department_dto.DepartmentNameDTO;
import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.controller.dto.product_dto.ProductDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerDTO;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Product;
import com.ironhack.midterm_project.model.Seller;
import com.ironhack.midterm_project.repository.DepartmentRepository;
import com.ironhack.midterm_project.repository.EmployeeRepository;
import com.ironhack.midterm_project.repository.ProductRepository;
import com.ironhack.midterm_project.repository.SellerRepository;
import com.ironhack.midterm_project.service.interfaces.IDepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Department with id " + id + " not found.");
        return departmentOptional.get();
    }

    @Override
    @Transactional //makes sure method is completed or rolled back if there is an error
    public void addNewDepartment(Department department) {
        Optional<Department> departmentOptional = departmentRepository.findByName(department.getName());
        if (departmentOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A department with the name " + department.getName() + " already exists.");
        } else {
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
                    employeeRepository.save(employee);
                }
            }

            for (Product product : department.getInventory()) {
                Optional<Product> productOptional = productRepository.findByName(product.getName());
                if (productOptional.isPresent()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A product with the name " + product.getName() + " already exists.");
                } else {
                    productRepository.save(product);
                }
            }
            departmentRepository.save(department);
        }
    }

    @Override
    public void updateDepartmentName(DepartmentNameDTO departmentNameDTO, Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Department with id " + id + " not found.");
        Department department = departmentOptional.get();
        department.setName(departmentNameDTO.getName());
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartmentEmployees(DepartmentEmployeesDTO departmentEmployeesDTO, Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with id " + id + " not found.");

        for (EmployeeDTO employeeDTO : departmentEmployeesDTO.getEmployees()) {
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
    }


    @Override
    public void updateDepartmentInventory(DepartmentInventoryDTO departmentInventoryDTO, Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Department with id " + id + " not found.");

        for(ProductDTO productDTO : departmentInventoryDTO.getInventory()){
            Optional<Product> productOptional = productRepository.findByName(productDTO.getName());
            if (productOptional.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with name " + productDTO.getName() + " not found.");
            }

            Product product = productOptional.get();
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            productRepository.save(product);
        }
        Department department = departmentOptional.get();
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartmentInformation(DepartmentDTO departmentDTO, Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        if(department==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with id " + id + " not found.");

        //NAME
        department.setName(departmentDTO.getName());

        //EMPLOYEES
        for (EmployeeDTO employeeDTO : departmentDTO.getEmployees()) {
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
        for(ProductDTO productDTO : departmentDTO.getInventory()){
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

    @Override
    public void deleteDepartment(Integer id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Department with id " + id + " not found.");

        Department department = departmentOptional.get();
        if (department.getEmployees().isEmpty() || department.getInventory().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Department with id " + id + " cannot be deleted because it has employees or inventory.");

        departmentRepository.deleteById(id);
        for(Employee employee : department.getEmployees()){
            employeeRepository.delete(employee);
        }
        for(Product product : department.getInventory()){
            productRepository.delete(product);
        }

    }

    @Override
    public void deleteAllDepartments() {
//        List<Department> departments = departmentRepository.findAll();
//        for (Department department : departments) {
//
//            List<Employee> employeesList = new ArrayList<>(department.getEmployees());
//            for (Employee employee : employeesList) {
//                employeeRepository.deleteById(employee.getId());
//            }
//
//            List<Product> inventoriesList = new ArrayList<>(department.getInventory());
//            for (Product product : inventoriesList) {
//                productRepository.deleteById(product.getId());
//            }
//        }

        departmentRepository.deleteAll();
    }

}
