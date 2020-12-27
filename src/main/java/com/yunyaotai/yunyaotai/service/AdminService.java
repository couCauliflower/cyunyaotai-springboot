package com.yunyaotai.yunyaotai.service;

import com.yunyaotai.yunyaotai.dao.AdminDao;
import com.yunyaotai.yunyaotai.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminDao adminDao;
    public void addAdmin(Admin admin) {
        adminDao.save(admin);
    }

    public Optional<Admin> getAdminById(Integer id) {
        return adminDao.findById(id);
    }

    public Optional<Admin> getAdminByAdminname(String name) {
        Admin admin = new Admin();
        admin.setName(name);
        return adminDao.findOne(Example.of(admin));
    }

    public Optional<Admin> findAdminById(Integer id) {
        return adminDao.findById(id);
    }

    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminDao.findAll(pageable);
    }

    public void updateAdminById(Integer id, String name, String password, String confirmPassword, String info) {
        Optional<Admin> optionalAdmin = adminDao.findById(id);
        optionalAdmin.get().setName(name);
        optionalAdmin.get().setPassword(password);
        optionalAdmin.get().setConfirmPassword(confirmPassword);
        optionalAdmin.get().setInfo(info);
        optionalAdmin.get().setType("admin");
        adminDao.save(optionalAdmin.get());
    }

    public boolean deleteAdminById(Integer id) {
        Optional<Admin> opt = adminDao.findById(id);
        if (opt.isEmpty()) {
            return false;
        }
        adminDao.delete(opt.get());
        return true;
    }

    public void updateAdminFaceimg(Integer id, String faceimg) {
        Optional<Admin> optionalAdmin=adminDao.findById(id);
        optionalAdmin.get().setFaceimg(faceimg);
        adminDao.save(optionalAdmin.get());
    }
}
