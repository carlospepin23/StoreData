package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeNameDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerEmailDTO;
//import com.ironhack.midterm_project.controller.dto.seller_dto.SellerNameDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerSalesDTO;
import com.ironhack.midterm_project.model.Seller;
import com.ironhack.midterm_project.repository.SellerRepository;
import com.ironhack.midterm_project.service.interfaces.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService implements ISellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller getSellerById(Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if(sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with id " + id + " not found.");
        return sellerOptional.get();
    }

//    @Override
//    public void addNewSeller(Seller seller) {
//        sellerRepository.save(seller);
//    }

    @Override
    public void updateSellerName(EmployeeNameDTO sellerNameDTO, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with id " + id + " not found.");
        Seller seller = sellerOptional.get();
        seller.setName(sellerNameDTO.getName());
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerEmail(SellerEmailDTO sellerEmailDTO, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with id " + id + " not found.");
        Seller seller = sellerOptional.get();
        seller.setEmail(sellerEmailDTO.getEmail());
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerSales(SellerSalesDTO sellerSalesDTO, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with id " + id + " not found.");
        Seller seller = sellerOptional.get();
        seller.setSales(sellerSalesDTO.getSales());
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerInformation(SellerDTO sellerDTO, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with id " + id + " not found.");
        Seller seller = sellerOptional.get();
        seller.setName(sellerDTO.getName());
        seller.setEmail(sellerDTO.getEmail());
        seller.setSales(sellerDTO.getSales());
        sellerRepository.save(seller);
    }

    @Override
    public void deleteSeller(Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Seller with id " + id + " not found.");
        sellerRepository.deleteById(id);
    }

    @Override
    public void deleteAllSellers() {
        sellerRepository.deleteAll();
    }
}
