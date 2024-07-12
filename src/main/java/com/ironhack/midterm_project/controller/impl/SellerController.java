package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.controller.dto.employee_dto.EmployeeNameDTO;
import com.ironhack.midterm_project.controller.dto.seller_dto.*;
import com.ironhack.midterm_project.controller.interfaces.ISellerController;
import com.ironhack.midterm_project.model.Seller;
import com.ironhack.midterm_project.service.impl.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SellerController implements ISellerController {

    @Autowired
    SellerService sellerService;

    //  ****************************************************  GET  ****************************************************
    @GetMapping("/sellers")
    public List<Seller> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/sellers/{id}")
    public Seller getSellerById(@PathVariable Integer id) {
        return sellerService.getSellerById(id);
    }

    //  ***************************************************  POST  ****************************************************
//    @PostMapping("/sellers")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addNewSeller(@RequestBody @Valid Seller seller) {
//        sellerService.addNewSeller(seller);
//    }

    //  ***************************************************  PATCH  ****************************************************
    @PatchMapping("/sellers/{id}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSellerName(@RequestBody @Valid EmployeeNameDTO sellerNameDTO, @PathVariable Integer id) {
        sellerService.updateSellerName(sellerNameDTO, id);
    }

    @PatchMapping("/sellers/{id}/email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSellerEmail(@RequestBody @Valid SellerEmailDTO sellerEmailDTO, @PathVariable Integer id) {
        sellerService.updateSellerEmail(sellerEmailDTO, id);
    }

    @PatchMapping("/sellers/{id}/sales")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSellerSales(@RequestBody @Valid SellerSalesDTO sellerSalesDTO, @PathVariable Integer id) {
        sellerService.updateSellerSales(sellerSalesDTO, id);
    }

    //  ***************************************************  PUT  ****************************************************
    @PutMapping("/sellers/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSellerInformation(@RequestBody @Valid SellerDTO sellerDTO, @PathVariable Integer id) {
        sellerService.updateSellerInformation(sellerDTO, id);
    }

    //  ***************************************************  DELETE  ****************************************************
    @DeleteMapping("/sellers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeller(@PathVariable Integer id) {
        sellerService.deleteSeller(id);
    }

    @DeleteMapping("/sellers/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllSellers() {
        sellerService.deleteAllSellers();
    }
}
