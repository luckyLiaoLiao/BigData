package com.example.stumanager.service.impl;

import com.example.stumanager.model.Students;
import com.example.stumanager.service.StudentsService;
import com.example.stumanager.mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author meira
* @description 针对表【students】的数据库操作Service实现
* @createDate 2025-02-23 16:12:19
*/
@Service
public class StudentsServiceImpl implements StudentsService{

    @Autowired
    private StudentsMapper studentsMapper;

    @Override
    public Students queryById(Integer id) {
        Students bean = studentsMapper.queryById(id);
        bean.setPassword("");
        return bean;
    }

    @Override
    public Students login(String code, String password) {
        Students bean = studentsMapper.login(code, password);
        bean.setPassword("");
        return bean;
    }
}




