package com.ironhack.midterm_project.controller.impl;

import com.ironhack.midterm_project.DTO.store_dto.StoreDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreDepartmentsDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreLocationDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreNameDTO;

import com.ironhack.midterm_project.controller.interfaces.IStoreController;

import com.ironhack.midterm_project.model.Store;

import com.ironhack.midterm_project.service.impl.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreController implements IStoreController {

    @Autowired
    StoreService storeService;

    //  ****************************************************  GET  ****************************************************
    @GetMapping("/stores")
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/stores/{id}")
    public Store getStoreById(@PathVariable Integer id) {
        return storeService.getStoreById(id);
    }

    @GetMapping("/stores/name/{name}")
    public Store getStoreByName(@PathVariable String name) {
        return storeService.getStoreByName(name);
    }

    //  ***************************************************  POST  ****************************************************
    @PostMapping("/stores")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStore(@RequestBody @Valid Store store) {
        storeService.addNewStore(store);
    }

    //  ***************************************************  PATCH  ****************************************************
    @PatchMapping("/stores/{id}/name")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStoreName(@RequestBody @Valid StoreNameDTO storeNameDTO, @PathVariable Integer id) {
        storeService.updateStoreName(storeNameDTO, id);
    }

    @PatchMapping("/stores/{id}/location")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStoreLocation(@RequestBody @Valid StoreLocationDTO storeLocationDTO, @PathVariable Integer id) {
        storeService.updateStoreLocation(storeLocationDTO, id);
    }

    @PatchMapping("/stores/{id}/departments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStoreDepartments(@RequestBody @Valid StoreDepartmentsDTO storeDepartmentsDTO, @PathVariable Integer id) {
        storeService.updateStoreDepartments(storeDepartmentsDTO, id);
    }

    //  ***************************************************  PUT  ****************************************************
    @PutMapping("/stores/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateStoreInformation(@RequestBody @Valid StoreDTO storeDTO, @PathVariable Integer id) {
        storeService.updateStoreInformation(storeDTO, id);
    }

    //  ***************************************************  DELETE  ****************************************************
    @DeleteMapping("/stores/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
    }

    @DeleteMapping("/stores/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllStores() {
        storeService.deleteAllStores();

    }
}
