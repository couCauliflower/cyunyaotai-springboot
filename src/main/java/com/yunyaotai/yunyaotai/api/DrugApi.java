package com.yunyaotai.yunyaotai.api;


import com.yunyaotai.yunyaotai.entity.Admin;
import com.yunyaotai.yunyaotai.entity.Drug;
import com.yunyaotai.yunyaotai.service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/api/drug")
public class DrugApi {
    @Autowired
    DrugService drugService;

    //添加药品
    //接口地址  http://localhost:8080/drug/api/drug
    //输入数据
//    "name":"口炎清颗粒",
//            "price":"29.50",
//            "storageNumber":"125",
//            "limitNumber":"3",
//            "info":"滋阴清热，解毒消肿。用于阴虚火旺引起的口腔炎症。",
//            "used":"口服，一次2袋（20克），一日2次。",
//            "storageMonth":"24",
//            "productTime":"2020-04-12",
//            "type":"c8",
//            "img1":"http://172.81.245.195:8888/group1/M00/00/00/rFH1w1_DQdqAVgArAAG4Eoi897c791.jpg",
//            "img2":"http://172.81.245.195:8888/group1/M00/00/00/rFH1w1_DQemAMhXgAACdYbFgDiQ785.jpg",
//            "img3":"http://172.81.245.195:8888/group1/M00/00/00/rFH1w1_DQfiAI5qRAAEzWryPfR4305.jpg",
//            "img4":"http://172.81.245.195:8888/group1/M00/00/00/rFH1w1_DQgqALBTmAAER_0GVzsg959.jpg",
//            "img5":"http://172.81.245.195:8888/group1/M00/00/00/rFH1w1_DQheAERCNAADwL1uGL3Y268.jpg"
    @PostMapping
    public String addDrug(@RequestBody Drug drug){
        drugService.addDrug(drug);
        return "ok";
    }

    //查找所有药品
    //接口地址 http://localhost:8080/drug/api/drug/find
    @GetMapping("find")
    public Page<Drug> getAllDrugs(int page,int size){
        return drugService.getAllDrugs(PageRequest.of(page,size));

    }

    //根据type查找药品
    //输入地址 Get http://localhost:8080/drug/api/drug/search?type=c6&page=0&size=5
    @GetMapping("search")
    public Page<Drug> getDrugByType(String type,int page,int size) {
        return drugService.getDrugByType(type,PageRequest.of(page,size));
    }

    //根据id查找药品
    //输入地址 Get http://localhost:8080/drug/api/drug?id=6
    @GetMapping
    public Optional<Drug> getDrugById(int id){
        return drugService.getDrugById(id);
    }

    //修改药品信息
    //输入地址
    @GetMapping("update")
    public String updateDrug(Integer id,String name,String price,String storageNumber,String info,String used,
                             String storageMonth,String productTime){
        drugService.updateDrugById(id,name,price,storageNumber,info,used,storageMonth,productTime);
        return "ok";
    }

    //修改药品img1
    //接口地址 http://localhost:8080/drug/api/drug/updatedrugimg?id=1&img1=...
    @GetMapping("updatedrugimg")
    public String updateDrugimg(Integer id,String img1){
        drugService.updateDrugimg(id,img1);
        return "ok";
    }

    //删除药品
    @DeleteMapping("{id}")
    public String deleteDrugById(@PathVariable("id") Integer id){
        return drugService.deleteDrugById(id)?"ok":"false";
    }





}
