package com.ironhack.midterm_project.service.impl;

import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Store;
import com.ironhack.midterm_project.repository.StoreRepository;
import com.ironhack.midterm_project.service.interfaces.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements IStoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store getStoreById(Integer id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public void addNewStore(Store store) {
        storeRepository.save(store);
    }

    @Override
    public void updateStoreName(String name, Integer id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) return;
        store.setName(name);
        storeRepository.save(store);
    }

    @Override
    public void updateStoreLocation(String location, Integer id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) return;
        store.setLocation(location);
        storeRepository.save(store);
    }

    @Override
    public void updateStoreDepartments(List<Department> departments, Integer id) {
        Store store = storeRepository.findById(id).orElse(null);
        if (store == null) return;
        store.setDepartments(departments);
        storeRepository.save(store);
    }

    @Override
    public void updateStoreInformation(Store store, Integer id) {
        Store prevStore = storeRepository.findById(id).orElse(null);
        if (prevStore == null) return;
        prevStore.setName(store.getName());
        prevStore.setLocation(store.getLocation());
        prevStore.setDepartments(store.getDepartments());
        storeRepository.save(prevStore);
    }

    @Override
    public void deleteStore(Integer id) {
        storeRepository.deleteById(id);
    }

    @Override
    public void deleteAllStores() {
        storeRepository.deleteAll();
    }
}
