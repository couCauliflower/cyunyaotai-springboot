package com.yunyaotai.yunyaotai.service;

import com.yunyaotai.yunyaotai.dao.DrugDao;
import com.yunyaotai.yunyaotai.entity.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DrugService {
    @Autowired
    DrugDao drugDao;
    public void addDrug(Drug drug) {
       drugDao.save(drug);

    }

    public Page<Drug> getAllDrugs(Pageable pageable) {
        return drugDao.findAll(pageable);
    }

    public Page<Drug> getDrugByType(String type, Pageable pageable) {
        Drug drug=new Drug();
        drug.setType(type);
        return drugDao.findAll(Example.of(drug),pageable);
    }

    public Optional<Drug> getDrugById(int id) {
        return drugDao.findById(id);
    }

    public void updateDrugById(Integer id, String name, String price, String storageNumber,
                               String info, String used, String storageMonth, String productTime) {
        Optional<Drug> drug =drugDao.findById(id);
        drug.get().setName(name);
        drug.get().setPrice(price);
        drug.get().setStorageNumber(storageNumber);
        drug.get().setInfo(info);
        drug.get().setUsed(used);
        drug.get().setStorageMonth(storageMonth);
        drug.get().setProductTime(productTime);
        drugDao.save(drug.get());
    }

    public boolean deleteDrugById(Integer id) {
        Optional<Drug> drugOptional=drugDao.findById(id);
        if(drugOptional.isEmpty())return false;
        drugDao.delete(drugOptional.get());
        return true;
    }

    public void updateDrugimg(Integer id, String img1) {
        Optional<Drug> drugOptional=drugDao.findById(id);
        drugOptional.get().setImg1(img1);
        drugDao.save(drugOptional.get());
    }
}
