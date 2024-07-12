package com.ironhack.midterm_project.controller.interfaces;

//import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeDTO;
import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeNameDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerEmailDTO;
//import com.ironhack.midterm_project.controller.dto.seller_dto.SellerNameDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.SellerSalesDTO;
import com.ironhack.midterm_project.model.Seller;

import java.util.List;

public interface ISellerController {
    List<Seller> getAllSellers();
    Seller getSellerById(Integer id);
//    void addNewSeller(Seller seller);
    void updateSellerName(EmployeeNameDTO sellerNameDTO, Integer id);
    void updateSellerEmail(SellerEmailDTO sellerEmailDTO, Integer id);
    void updateSellerSales(SellerSalesDTO sellerSalesDTO, Integer id);
    void updateSellerInformation(SellerDTO sellerDTO, Integer id);
    void deleteSeller(Integer id);
    void deleteAllSellers();
}
