package com.ironhack.midterm_project.service.interfaces;
import com.ironhack.midterm_project.model.Department;
import com.ironhack.midterm_project.model.Store;
import java.util.List;

public interface IStoreService {
    List<Store> getAllStores();
    Store getStoreById(Integer id);
    void addNewStore(Store store);
    void updateStoreName(String name, Integer id);
    void updateStoreLocation(String location, Integer id);
    void updateStoreDepartments(List<Department> departments, Integer id);
    void updateStoreInformation(Store store, Integer id);
    void deleteStore(Integer id);
    void deleteAllStores();

}