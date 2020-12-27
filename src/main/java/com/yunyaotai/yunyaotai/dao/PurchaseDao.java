package com.yunyaotai.yunyaotai.dao;

import com.yunyaotai.yunyaotai.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDao extends JpaRepository<Purchase,Integer> {

}
