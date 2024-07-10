package com.ironhack.midterm_project.service.interfaces;

//import com.ironhack.midterm_project.model.Employee;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Seller;

import java.util.List;

public interface ISellerService {
    List<Seller> getAllSellers();
    Seller getSellerById(Integer id);
    void addNewSeller(Seller seller);
    void updateSellerName(String name, Integer id);
    void updateSellerEmail(String email, Integer id);
    void updateSellerSales(Integer sales, Integer id);
    void updateSellerInformation(Seller seller, Integer id);
    void deleteSeller(Integer id);
}
