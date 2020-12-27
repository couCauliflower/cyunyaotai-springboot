package com.yunyaotai.yunyaotai.service;

import com.yunyaotai.yunyaotai.dao.UserDao;
import com.yunyaotai.yunyaotai.entity.Admin;
import com.yunyaotai.yunyaotai.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public void addUser(User user) {
        userDao.save(user);
    }

    public Optional<User> getUserById(Integer id) {
        return userDao.findById(id);
    }

    public Optional<User> getUserByUsername(String name) {
        User user=new User();
        user.setName(name);
        return userDao.findOne(Example.of(user));
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    public void updateUserById(Integer id, String name, String password, String confirmPassword, String info) {
        Optional<User> optionalUser = userDao.findById(id);
        optionalUser.get().setName(name);
        optionalUser.get().setPassword(password);
        optionalUser.get().setConfirmPassword(confirmPassword);
        optionalUser.get().setInfo(info);
        optionalUser.get().setType("user");
        userDao.save(optionalUser.get());
    }

    public boolean deleteUserById(Integer id) {
        Optional<User> opt = userDao.findById(id);
        if (opt.isEmpty()) {
            return false;
        }
        userDao.delete(opt.get());
        return true;
    }

    public void updateUserFaceimg(Integer id, String faceimg) {
        Optional<User> optionalUser=userDao.findById(id);
        optionalUser.get().setFaceimg(faceimg);
        userDao.save(optionalUser.get());
    }
}
