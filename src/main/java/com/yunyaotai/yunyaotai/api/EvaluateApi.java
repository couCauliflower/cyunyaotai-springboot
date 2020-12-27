package com.yunyaotai.yunyaotai.api;

import com.yunyaotai.yunyaotai.dto.EvaluateDto;
import com.yunyaotai.yunyaotai.entity.Evaluate;
import com.yunyaotai.yunyaotai.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/evaluate")
public class EvaluateApi {
    @Autowired
    EvaluateService evaluateService;

    //添加评价信息
    //接口地址 http://localhost:8080/drug/api/evaluate
    //输入数据 "userid":3,
    //    "drugid":6,
    //    "message":"今天本该过的不一样。"
    @PostMapping
    public String addEvaluate(@RequestBody EvaluateDto evaluateDto){
        evaluateService.addEvaluate(evaluateDto);
        return "ok";
    }

    //根据userid来获取评价信息
    //接口地址 http://localhost:8080/drug/api/evaluate/findevaluate?id=3&page=0&size=10
    @GetMapping("findevaluate")
    public Page<Evaluate> getAllEvaluateByUserId(Integer id,int page,int size){
        return evaluateService.getAllEvaluateByUserId(id,PageRequest.of(page,size));
    }

    //根据drugid来获取评价信息
    //接口地址 http://localhost:8080/drug/api/evaluate/findevaluatebydrugid?id=1&page=0&size=10
    @GetMapping("findevaluatebydrugid")
    public Page<Evaluate> getAllEvaluateByDrugId(Integer id,int page,int size){
        return evaluateService.getAllEvaluateByDrugId(id,PageRequest.of(page,size));
    }

    //删除评价信息
    @DeleteMapping("{id}")
    public String deleteEvaluateById(@PathVariable("id") Integer id){
        return evaluateService.deleteEvaluateById(id)?"ok":"false";
    }
}
