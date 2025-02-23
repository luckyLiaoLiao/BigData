package com.example.stumanager.controller;

import com.example.stumanager.model.Admin;
import com.example.stumanager.model.DormAdmins;
import com.example.stumanager.service.AdminService;
import com.example.stumanager.service.DormAdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 请求路径前缀
@RequestMapping("/dorm_admins")
public class DormAdminsController {

    @Autowired
    private DormAdminsService dormAdminsService;

    @RequestMapping("/queryById")
    public Object queryById(Integer id){
        return dormAdminsService.queryById(id);
    }


    @PostMapping("/login")
    public Object login(@RequestBody DormAdmins bean){
        return dormAdminsService.login(bean.getAccount(), bean.getPassword());
    }
}
