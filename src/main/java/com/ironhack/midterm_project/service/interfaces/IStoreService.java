package com.ironhack.midterm_project.service.interfaces;
import com.ironhack.midterm_project.DTO.store_dto.StoreDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreDepartmentsDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreLocationDTO;
import com.ironhack.midterm_project.DTO.store_dto.StoreNameDTO;
import com.ironhack.midterm_project.model.Store;
import java.util.List;

public interface IStoreService {
    List<Store> getAllStores();
    Store getStoreById(Integer id);
    void addNewStore(Store store);
    void updateStoreName(StoreNameDTO storeNameDTO, Integer id);
    void updateStoreLocation(StoreLocationDTO storeLocationDTO, Integer id);
    void updateStoreDepartments(StoreDepartmentsDTO storeDepartmentsDTO, Integer id);
    void updateStoreInformation(StoreDTO storeDTO, Integer id);
    void deleteStore(Integer id);
    void deleteAllStores();

}