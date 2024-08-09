package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.DTO.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentEmployeesDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentInventoryDTO;
import com.ironhack.midterm_project.DTO.department_dto.DepartmentNameDTO;
import com.ironhack.midterm_project.DTO.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.DTO.product_dto.ProductDTO;
import com.ironhack.midterm_project.DTO.seller_dto.SellerDTO;
import com.ironhack.midterm_project.model.*;
import com.ironhack.midterm_project.repository.*;
import com.ironhack.midterm_project.service.interfaces.IDepartmentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional //makes sure method is completed or rolled back if there is an error
public class DepartmentService implements IDepartmentService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SellerService sellerService;

    @Autowired
    ProductService productService;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Integer id) {
        return exceptionMsgDepartment(id);
    }

    @Override
    public void addNewDepartment(Department department) {
        alreadyExists(department);
        employeesAndInventoryAlreadyExist(department);
        Optional<Store> storeOptional = storeRepository.findById(department.getStore().getId());
        if (storeOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Store with id " + department.getStore().getId() + " not found.");
        }
        department.departmentSetter();
        departmentRepository.save(department);

    }

    @Override
    public void updateDepartmentName(DepartmentNameDTO departmentNameDTO, Integer id) {
        Department department = exceptionMsgDepartment(id);
        department.setName(departmentNameDTO.getName());
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartmentEmployees(DepartmentEmployeesDTO departmentEmployeesDTO, Integer id) {
        exceptionMsgDepartment(id);
        employeeUpdate(departmentEmployeesDTO.getEmployees());
    }

    @Override
    public void updateDepartmentInventory(DepartmentInventoryDTO departmentInventoryDTO, Integer id) {
        exceptionMsgDepartment(id);
        inventoryUpdate(departmentInventoryDTO.getInventory());
    }

    @Override
    public void updateDepartmentInformation(DepartmentDTO departmentDTO, Integer id) {
        Department department = exceptionMsgDepartment(id);

        //NAME
        department.setName(departmentDTO.getName());
        //EMPLOYEES
        employeeUpdate(departmentDTO.getEmployees());
        //INVENTORY
        inventoryUpdate(departmentDTO.getInventory());

        departmentRepository.save(department);
    }

    @Override
    public void updateDepartmentInformation(DepartmentDTO departmentDTO, String name) {
        Department department = exceptionMsgDepartment(name);

        //NAME
        department.setName(departmentDTO.getName());
        //EMPLOYEES
        employeeUpdate(departmentDTO.getEmployees());
        //INVENTORY
        inventoryUpdate(departmentDTO.getInventory());

        departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Integer id) {
        exceptionMsgDepartment(id);
        departmentRepository.deleteById(id);

        storeRepository.flush();
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            if (store.getDepartments().isEmpty()) {
                storeRepository.delete(store);
            }
        }

    }

    @Override
    public void deleteAllDepartmentsExceptId(Integer id) {
        exceptionMsgDepartment(id);
        departmentRepository.deleteAllByIdNot(id);

        storeRepository.flush();
        List<Store> stores = storeRepository.findAll();
        for (Store store : stores) {
            if (store.getDepartments().isEmpty()) {
                storeRepository.delete(store);
            }
        }
    }

    private Department exceptionMsgDepartment(Integer id){
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No departments found.");

        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Department with id " + id + " not found.");

        return departmentOptional.get();
    }

    private Department exceptionMsgDepartment(String name){
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No departments found.");

        Optional<Department> departmentOptional = departmentRepository.findByName(name);
        if (departmentOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Department with name " + name + " not found.");

        return departmentOptional.get();
    }

    private void inventoryUpdate(List<ProductDTO> productsDTOs){
        for(ProductDTO productDTO : productsDTOs){
            productService.updateProductInformation(productDTO, productDTO.getName());
        }
    }

    private void employeeUpdate(List<EmployeeDTO> employeeDTOS){
        for (EmployeeDTO employeeDTO : employeeDTOS) {
            if (employeeDTO instanceof SellerDTO sellerDTO) {
                sellerService.updateSellerInformation(sellerDTO, sellerDTO.getName());
            } else {
                employeeService.updateEmployeeInformation(employeeDTO, employeeDTO.getName());
            }
        }
    }

    public void alreadyExists(Department department){
        Optional<Department> departmentOptional = departmentRepository.findByName(department.getName());
        if (departmentOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The department " + department.getName() + " already exists.");
        }
    }

    public void employeesAndInventoryAlreadyExist(Department department){
        for (Employee employee : department.getEmployees()) {
            employeeService.alreadyExists(employee);
            if (employee instanceof Seller seller) {sellerService.alreadyExists(seller);}
        }

        for (Product product : department.getInventory()) {
            productService.alreadyExists(product);
        }
    }


}
