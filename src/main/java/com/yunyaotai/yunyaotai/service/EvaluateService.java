package com.yunyaotai.yunyaotai.service;

import com.yunyaotai.yunyaotai.dao.DrugDao;
import com.yunyaotai.yunyaotai.dao.EvaluateDao;
import com.yunyaotai.yunyaotai.dao.UserDao;
import com.yunyaotai.yunyaotai.dto.EvaluateDto;
import com.yunyaotai.yunyaotai.entity.*;
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
public class EvaluateService {
    @Autowired
    EvaluateDao evaluateDao;
    @Autowired
    DrugDao drugDao;
    @Autowired
    UserDao userDao;
    public void addEvaluate(EvaluateDto evaluateDto) {
        Date d=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
        Optional<User> optionalUser=userDao.findById(evaluateDto.getUserid());
        Optional<Drug> optionalDrug=drugDao.findById(evaluateDto.getDrugid());
        evaluateDao.save(new Evaluate()
                .setMessage(evaluateDto.getMessage())
                .setTime(sdf.format(d))
                .setFrom(optionalUser.get())
                .setTo(optionalDrug.get())
        );
    }

    public Page<Evaluate> getAllEvaluateByUserId(Integer id, Pageable pageable) {
        Optional<User> optionalUser=userDao.findById(id);
        return evaluateDao.findAll(Example.of(new Evaluate().setFrom(optionalUser.get())),pageable);
    }

    public boolean deleteEvaluateById(Integer id) {
        Optional<Evaluate> evaluateOptional=evaluateDao.findById(id);
        if(evaluateOptional.isEmpty())return false;
        evaluateDao.delete(evaluateOptional.get());
        return true;
    }

    public Page<Evaluate> getAllEvaluateByDrugId(Integer id, Pageable pageable) {
        Optional<Drug> optionalDrug=drugDao.findById(id);
        return evaluateDao.findAll(Example.of(new Evaluate().setTo(optionalDrug.get())),pageable);
    }
}
