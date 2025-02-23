package com.example.stumanager.service.impl;

import com.example.stumanager.mapper.AdminMapper;
import com.example.stumanager.mapper.DormAdminsMapper;
import com.example.stumanager.model.Admin;
import com.example.stumanager.model.DormAdmins;
import com.example.stumanager.service.AdminService;
import com.example.stumanager.service.DormAdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//在controller中不会调用mapper，只会调用service
@Service
public class DormAdminsServiceImpl implements DormAdminsService {

    @Autowired
    private DormAdminsMapper dormAdminsMapper;
    @Override
    public DormAdmins queryById(Integer id) {
        DormAdmins bean = dormAdminsMapper.queryById(id);
        bean.setPassword("");
        return bean;
    }

    @Override
    public DormAdmins login(String account, String password) {
        DormAdmins bean = dormAdminsMapper.login(account, password);
        bean.setPassword("");
        return bean;
    }


}
