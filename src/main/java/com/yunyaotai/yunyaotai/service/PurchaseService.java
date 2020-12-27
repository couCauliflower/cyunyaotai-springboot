package com.yunyaotai.yunyaotai.service;

import com.yunyaotai.yunyaotai.dao.DrugDao;
import com.yunyaotai.yunyaotai.dao.PurchaseDao;
import com.yunyaotai.yunyaotai.dao.UserDao;
import com.yunyaotai.yunyaotai.dto.PurchaseDto;
import com.yunyaotai.yunyaotai.entity.Comment;
import com.yunyaotai.yunyaotai.entity.Drug;
import com.yunyaotai.yunyaotai.entity.Purchase;
import com.yunyaotai.yunyaotai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    PurchaseDao purchaseDao;
    @Autowired
    UserDao userDao;
    @Autowired
    DrugDao drugDao;
    public void addPurchase(PurchaseDto purchaseDto) {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Optional optionalUser=userDao.findById(purchaseDto.getUserid());
        Optional optionalDrug=drugDao.findById(purchaseDto.getDrugid());
        purchaseDao.save(new Purchase()
                .setTime(sdf.format(d))
                .setType("nomark")
                .setUser((User) optionalUser.get())
                .setDrug((Drug) optionalDrug.get())
        );

    }

    public Page<Purchase> getAllPurchaseByUserId(Integer id,Pageable pageable) {
        Optional<User> userOptional=userDao.findById(id);
        return purchaseDao.findAll(Example.of(new Purchase().setUser(userOptional.get())),pageable);
    }

    public boolean deletePurchaseById(Integer id) {
        Optional<Purchase> purchaseOptional=purchaseDao.findById(id);
        if(purchaseOptional.isEmpty())return false;
        purchaseDao.delete(purchaseOptional.get());
        return true;
    }

    public void updatePurchase(Integer id) {
        Optional<Purchase> optionalPurchase=purchaseDao.findById(id);
        optionalPurchase.get().setType("remark");
        purchaseDao.save(optionalPurchase.get());
    }

    public Page<Purchase> getPurchaseByType(String type, Pageable pageable) {
        Purchase purchase=new Purchase();
        purchase.setType(type);
        return purchaseDao.findAll(Example.of(purchase),pageable);
    }
}
