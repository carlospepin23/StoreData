package com.ironhack.midterm_project.controller.interfaces;

import com.ironhack.midterm_project.controller.dto.SellerDTO;
import com.ironhack.midterm_project.controller.dto.SellerEmailDTO;
import com.ironhack.midterm_project.controller.dto.SellerNameDTO;
import com.ironhack.midterm_project.controller.dto.SellerSalesDTO;
import com.ironhack.midterm_project.model.Seller;

import java.util.List;

public interface ISellerController {
    List<Seller> getAllSellers();
    Seller getSellerById(Integer id);
    void addNewSeller(Seller seller);
        void updateSellerName(SellerNameDTO sellerNameDTO, Integer id);
    void updateSellerEmail(SellerEmailDTO sellerEmailDTO, Integer id);
    void updateSellerSales(SellerSalesDTO sellerSalesDTO, Integer id);
    void updateSellerInformation(SellerDTO sellerDTO, Integer id);
    void deleteSeller(Integer id);
}
