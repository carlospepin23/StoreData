package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.DTO.employee_dto.EmployeeNameDTO;
import com.ironhack.midterm_project.DTO.seller_dto.SellerDTO;
import com.ironhack.midterm_project.DTO.seller_dto.SellerEmailDTO;
import com.ironhack.midterm_project.DTO.seller_dto.SellerSalesDTO;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Seller;
import com.ironhack.midterm_project.repository.DepartmentRepository;
import com.ironhack.midterm_project.repository.SellerRepository;
import com.ironhack.midterm_project.service.interfaces.ISellerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SellerService implements ISellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSellerById(Integer id) {
        return exceptionMsgSeller(id);
    }

    @Override
    public void updateSellerName(EmployeeNameDTO sellerNameDTO, Integer id) {
        Seller seller = exceptionMsgSeller(id);
        seller.setName(sellerNameDTO.getName());
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerEmail(SellerEmailDTO sellerEmailDTO, Integer id) {
        Seller seller = exceptionMsgSeller(id);
        seller.setEmail(sellerEmailDTO.getEmail());
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerSales(SellerSalesDTO sellerSalesDTO, Integer id) {
        Seller seller = exceptionMsgSeller(id);
        seller.setSales(sellerSalesDTO.getSales());
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerInformation(SellerDTO sellerDTO, Integer id) {
        Seller seller = exceptionMsgSeller(id);
        seller.setName(sellerDTO.getName());
        seller.setEmail(sellerDTO.getEmail());
        seller.setSales(sellerDTO.getSales());

        Department currentDepartment = seller.getDepartment();

        // Check if the current department will be left without employees
        if (currentDepartment.getEmployees().size() == 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The current department cannot be left without employees");
        }

        // Fetch the new department entity using the ID from SellerDTO
        Optional<Department> departmentOptional = departmentRepository.findById(sellerDTO.getDepartment().getId());
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id " + sellerDTO.getDepartment().getId() + " not found.");
        }

        // Update the seller's department
        seller.setDepartment(departmentOptional.get());
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerInformation(SellerDTO sellerDTO, String email) {
        Seller seller = exceptionMsgSeller(email);
        seller.setName(sellerDTO.getName());
        seller.setEmail(sellerDTO.getEmail());
        seller.setSales(sellerDTO.getSales());

        Department currentDepartment = seller.getDepartment();

        // Check if the current department will be left without employees
        if (currentDepartment.getEmployees().size() == 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The current department cannot be left without employees");
        }

        // Fetch the new department entity using the ID from SellerDTO
        Optional<Department> departmentOptional = departmentRepository.findById(sellerDTO.getDepartment().getId());
        if (departmentOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department with id " + sellerDTO.getDepartment().getId() + " not found.");
        }

        // Update the seller's department
        seller.setDepartment(departmentOptional.get());
        sellerRepository.save(seller);
    }

    @Override
    public void deleteSeller(Integer id) {
        exceptionMsgSeller(id);
        sellerRepository.deleteById(id);
    }

    @Override
    public void deleteAllSellersExceptId(Integer id) {
        exceptionMsgSeller(id);
        sellerRepository.deleteAllByIdNot(id);
    }

    private Seller exceptionMsgSeller(Integer id){
        List<Seller> sellers = sellerRepository.findAll();
        if (sellers.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No sellers found.");

        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with id " + id + " not found.");

        return sellerOptional.get();
    }

    private Seller exceptionMsgSeller(String name){
        List<Seller> sellers = sellerRepository.findAll();
        if (sellers.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No sellers found.");

        Optional<Seller> sellerOptional = sellerRepository.findByName(name);
        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with name " + name + " not found.");

        return sellerOptional.get();
    }

//    private Seller exceptionMsgSeller(String email){
//        List<Seller> sellers = sellerRepository.findAll();
//        if (sellers.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "No sellers found.");
//
//        Optional<Seller> sellerOptional = sellerRepository.findByEmail(email);
//        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "Seller with email " + email + " not found.");
//
//        return sellerOptional.get();
//    }

    public void alreadyExists(Seller seller){
        Optional<Seller> sellerOptional = sellerRepository.findByEmail(seller.getEmail());
        if (sellerOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The seller " + seller.getName() + " already exists.");
        }
    }
}
