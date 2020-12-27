package com.yunyaotai.yunyaotai.api;

import com.yunyaotai.yunyaotai.dto.PurchaseDto;
import com.yunyaotai.yunyaotai.entity.Purchase;
import com.yunyaotai.yunyaotai.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseApi {
    @Autowired
    PurchaseService purchaseService;

    //添加购买信息
    //接口地址 http://localhost:8080/drug/api/purchase
    //输入数据 "userid":3,
    //    "drugid":3
    @PostMapping
    public String addPurchase(@RequestBody PurchaseDto purchaseDto){
        purchaseService.addPurchase(purchaseDto);
        return "ok";
    }

    //根据userid来获取所有购买信息
    //接口地址 http://localhost:8080/drug/api/purchase/findpurchase?id=3&page=0&size=10 get
    @GetMapping("findpurchase")
    public Page<Purchase> getAllPurchaseByUserId(Integer id,int page,int size){
        return purchaseService.getAllPurchaseByUserId(id,PageRequest.of(page,size));
    }

    //根据type来获取购买信息
    //http://localhost:8080/drug/api/purchase/search?type=nomark&page=0&size=10
    //http://localhost:8080/drug/api/purchase/search?type=remark&page=0&size=10
    @GetMapping("search")
    public Page<Purchase> getPurchaseByType(String type,int page,int size){
        return purchaseService.getPurchaseByType(type,PageRequest.of(page,size));
    }

    //修改购买信息
    //接口地址 http://localhost:8080/drug/api/purchase/update?id=6
    @GetMapping("update")
    public String updatePurchase(Integer id){
        purchaseService.updatePurchase(id);
        return "ok";
    }

    //删除购买信息
    //接口地址 DELETE http://localhost:8080/drug/api/purchase/1
    @DeleteMapping("{id}")
    public String deletePurchaseById(@PathVariable("id") Integer id){
        return purchaseService.deletePurchaseById(id)?"ok":"false";

    }

}
