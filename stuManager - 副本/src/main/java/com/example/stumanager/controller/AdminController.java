package com.example.stumanager.controller;

import com.example.stumanager.mapper.AdminMapper;
import com.example.stumanager.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 请求路径前缀
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminMapper;

    @RequestMapping("/queryById")
    public Admin queryById(Integer id){
        return adminMapper.queryById(id);
    }
}
