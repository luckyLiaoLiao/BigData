package com.example.stumanager.service.impl;

import com.example.stumanager.mapper.AdminMapper;
import com.example.stumanager.model.Admin;
import com.example.stumanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//在controller中不会调用mapper，只会调用service
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;
    @Override
    public Admin queryById(Integer id) {
        Admin bean = mapper.queryById(id);
        bean.setPassword("");
        return bean;
    }

    @Override
    public Admin login(String account, String password) {
        Admin bean = mapper.login(account, password);
        bean.setPassword("");
        return bean;
    }


}
