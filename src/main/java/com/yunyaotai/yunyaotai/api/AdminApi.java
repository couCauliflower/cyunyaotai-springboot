package com.yunyaotai.yunyaotai.api;


import com.yunyaotai.yunyaotai.entity.Admin;
import com.yunyaotai.yunyaotai.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminApi {
    @Autowired
    AdminService adminService;


    @PostMapping //添加管理员
    //输入地址  Post  http://localhost:8080/drug/api/admin
    //输入数据  {
    //    "name":"admin02",
    //    "password":"123456",
    //    "confirmPassword":"123456",
    //    "info":"今天很不开心",
    //    "type":"admin",
    //    }
    public String addAdmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return "ok";
    }


    //根据id查找管理员
    //输入地址 Get http://localhost:8080/drug/api/admin/1
    @GetMapping("{id}")
    public Optional<Admin> getAdminById(@PathVariable("id") Integer id){
        return adminService.getAdminById(id);
    }

    //查找管理员2
    // 输入地址 Get http://localhost:8080/drug/api/admin/find?id=1  路径不一样
    @GetMapping("find")
    public Optional<Admin> findAdmin(Integer id) {
        return adminService.findAdminById(id);
    }


    //根据adminname查找管理员
    //输入地址 Get http://localhost:8080/drug/api/admin/search?name=admin02
    @GetMapping("search")
    public Optional<Admin> getAdminByAdminname(String name) {
        return adminService.getAdminByAdminname(name);
    }

    //获取所有管理员信息
    //输入地址 Get http://localhost:8080/drug/api/admin/0/10
    @GetMapping("{page}/{size}")
    public Page<Admin> getAllAdmins(@PathVariable("page") int page, @PathVariable("size") int size) {
        return adminService.getAllAdmins(PageRequest.of(page, size));
    }


    //修改管理员信息
    //输入地址 Get http://localhost:8080/drug/api/admin/update?id=1&name=admin&password=500500&confirmPassword=500&info=helloworld
    @GetMapping("update")//localhost:8080/drug/api/admin/update?id=2&name=admin03&password=500500&info=hello
    public String updateAdmin(Integer id, String name, String password, String confirmPassword,
                              String info) {
        adminService.updateAdminById(id, name, password, confirmPassword, info);
        return "ok";
    }

    //修改管理员头像
    @GetMapping("updatefaceimg")
    public String updateAdminFaceimg(Integer id,String faceimg){
        adminService.updateAdminFaceimg(id,faceimg);
        return "ok";
    }

    //删除管理员
    //输入地址 Delete http://localhost:8080/drug/api/admin/1
    @DeleteMapping("{id}")
    public String deleteAdminById(@PathVariable("id") Integer id) {
        return adminService.deleteAdminById(id) ? "ok" : "false";
    }

}
