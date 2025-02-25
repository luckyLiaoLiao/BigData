package com.example.stumanager.service.impl;

import com.example.stumanager.model.Admin;
import com.example.stumanager.service.AdminService;

//在controller中不会调用mapper，只会调用service
public class AdminServiceImpl implements AdminService {
    @Override
    public Admin queryById(Integer id) {
        return null;
    }
}
