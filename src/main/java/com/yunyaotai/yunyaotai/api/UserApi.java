package com.yunyaotai.yunyaotai.api;

import com.yunyaotai.yunyaotai.entity.Admin;
import com.yunyaotai.yunyaotai.entity.User;
import com.yunyaotai.yunyaotai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @Autowired
    UserService userService;


    @PostMapping //添加用户
    //输入地址  Post  http://localhost:8080/drug/api/user
    //输入数据  {
    //    "name":"user02",
    //    "password":"123456",
    //    "confirmPassword":"123456",
    //    "info":"今天很不开心",
    //    "type":"user"
    //    }
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "ok";
    }

    //根据id查找用户
    //输入地址 Get http://localhost:8080/drug/api/user/1
    //返回数据 {
    //    "id": 1,
    //    "name": "user01",
    //    "password": "123456",
    //    "confirmPassword": "123456",
    //    "info": "今天不该见到你",
    //    "type": "user"
    //}
    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable("id") Integer id){
        return userService.getUserById(id);
    }

    //根据username查找用户
    //输入地址 Get c?name=user02
    //返回数据 {
    //    "id": 2,
    //    "name": "user02",
    //    "password": "123456",
    //    "confirmPassword": "123456",
    //    "info": "今天不该见到你",
    //    "type": "user"
    //}
    @GetMapping("search")
    public Optional<User> getUserByUsername(String name) {
        return userService.getUserByUsername(name);
    }


    //获取所有用户信息
    //输入地址 Get http://localhost:8080/drug/api/user/0/10
    //返回数据"content": [
    //        {
    //            "id": 1,
    //            "name": "user01",
    //            "password": "123456",
    //            "confirmPassword": "123456",
    //            "info": "今天不该见到你",
    //            "type": "user"
    //        },
    //        {
    //            "id": 2,
    //            "name": "user02",
    //            "password": "123456",
    //            "confirmPassword": "123456",
    //            "info": "今天不该见到你",
    //            "type": "user"
    //        }
    //    ],
    @GetMapping("{page}/{size}")
    public Page<User> getAllUsers(@PathVariable("page") int page, @PathVariable("size") int size) {
        return userService.getAllUsers(PageRequest.of(page, size));
    }

    //修改用户信息
    //输入地址 Get http://localhost:8080/drug/api/user/update?id=1&name=user&password=500500&confirmPassword=500&info=helloworld
    @GetMapping("update")
    public String updateUser(Integer id, String name, String password, String confirmPassword,
                              String info) {
        userService.updateUserById(id, name, password, confirmPassword, info);
        return "ok";
    }

    //修改用户头像信息
    //输入地址  Get http://localhost:8080/drug/api/user/updatafaceimg?id=1&faceimg=......
    @GetMapping("updatafaceimg")
    public String updateUserFaceimg(Integer id,String faceimg){
        userService.updateUserFaceimg(id,faceimg);
        return "ok";
    }

    //删除用户
    //输入地址 Delete http://localhost:8080/drug/api/user/1
    @DeleteMapping("{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        return userService.deleteUserById(id) ? "ok" : "false";
    }

}
