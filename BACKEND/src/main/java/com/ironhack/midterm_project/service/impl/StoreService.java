package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.DTO.department_dto.DepartmentDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreDepartmentsDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreLocationDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreNameDTO;
import com.ironhack.midterm_project.model.*;
import com.ironhack.midterm_project.repository.*;
import com.ironhack.midterm_project.service.interfaces.IStoreService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StoreService implements IStoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    DepartmentService departmentService;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Integer id) {
        return exceptionMsgStore(id);

    }

    @Override
    public Store getStoreByName(String name) {
        return exceptionMsgStore(name);

    }

    @Override
    public void addNewStore(Store store) {
        // ensure the store doesn't already exist
        Optional<Store> storeOptional = storeRepository.findByName(store.getName());
        if (storeOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The store " + store.getName() + " already exists.");

        } else {
            for (Department department : store.getDepartments()) {
                departmentService.alreadyExists(department);
                departmentService.employeesAndInventoryAlreadyExist(department);
            }

            // set the store departments store's id
            store.storeSetter();
            // set the department employees department's id
            for (Department department: store.getDepartments()){
                department.departmentSetter();
            }
            storeRepository.save(store);
        }
    }

    @Override
    public void updateStoreName(StoreNameDTO storeNameDTO, Integer id) {
        Store store = exceptionMsgStore(id);
        store.setName(storeNameDTO.getName());
        storeRepository.save(store);
    }

    @Override
    public void updateStoreLocation(StoreLocationDTO storeLocationDTO, Integer id) {
        Store store = exceptionMsgStore(id);
        store.setLocation(storeLocationDTO.getLocation());
        storeRepository.save(store);
    }

    @Override
    public void updateStoreDepartments(StoreDepartmentsDTO storeDepartmentsDTO, Integer id) {
        Store store = exceptionMsgStore(id);
        updateStoreDepartmentsHelper(storeDepartmentsDTO.getDepartments());
        storeRepository.save(store);
    }

    @Override
    public void updateStoreInformation(StoreDTO storeDTO, Integer id) {
        Store store = exceptionMsgStore(id);

        //NAME
        store.setName(storeDTO.getName());
        //LOCATION
        store.setLocation(storeDTO.getLocation());
        //DEPARTMENTS
        updateStoreDepartmentsHelper(storeDTO.getDepartments());

        storeRepository.save(store);
    }

    @Override
    public void deleteStore(Integer id) {
        exceptionMsgStore(id);
        storeRepository.deleteById(id);

    }

    @Override
    public void deleteAllStores() {
        List<Store> stores = storeRepository.findAll();
        if (stores.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No stores found.");
        storeRepository.deleteAll();
    }

    private Store exceptionMsgStore(Integer id){
        List<Store> stores = storeRepository.findAll();
        if (stores.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No stores found.");

        Optional<Store> storeOptional = storeRepository.findById(id);
        if (storeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Store with id " + id + " not found.");

        return storeOptional.get();
    }

    private Store exceptionMsgStore(String name){
        List<Store> stores = storeRepository.findAll();
        if (stores.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No stores found.");

        Optional<Store> storeOptional = storeRepository.findByName(name);
        if (storeOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Store with name " + name + " not found.");

        return storeOptional.get();
    }

    private void updateStoreDepartmentsHelper(List<DepartmentDTO> storeDepartmentsDTO){
        for(DepartmentDTO departmentDTO : storeDepartmentsDTO) {
            departmentService.updateDepartmentInformation(departmentDTO, departmentDTO.getName());
        }
    }
}
