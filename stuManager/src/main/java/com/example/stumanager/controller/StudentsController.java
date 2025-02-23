package com.example.stumanager.controller;

import com.example.stumanager.model.Students;
import com.example.stumanager.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 请求路径前缀
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsService service;

    @RequestMapping("/queryById")
    public Object queryById(Integer id){
        return service.queryById(id);
    }


    @PostMapping("/login")
    public Object login(@RequestBody Students bean){
        return service.login(bean.getCode(), bean.getPassword());
    }
}
