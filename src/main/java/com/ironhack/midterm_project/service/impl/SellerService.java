package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Seller;
import com.ironhack.midterm_project.repository.SellerRepository;
import com.ironhack.midterm_project.service.interfaces.ISellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return sellerOptional.orElse(null);
    }

    @Override
    public void addNewSeller(Seller seller) {
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerName(String name, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) return;
        Seller seller = sellerOptional.get();
        seller.setName(name);
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerEmail(String email, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) return;
        Seller seller = sellerOptional.get();
        seller.setEmail(email);
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerSales(Integer sales, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) return;
        Seller seller = sellerOptional.get();
        seller.setSales(sales);
        sellerRepository.save(seller);
    }

    @Override
    public void updateSellerInformation(Seller seller, Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) return;
        seller.setName(seller.getName());
        seller.setEmail(seller.getEmail());
        seller.setSales(seller.getSales());
        sellerRepository.save(seller);
    }

    @Override
    public void deleteSeller(Integer id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        if (sellerOptional.isEmpty()) return;
        sellerRepository.deleteById(id);
    }
}
