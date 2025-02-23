package com.example.stumanager.service.impl;

import com.example.stumanager.mapper.DormAdminsMapper;
import com.example.stumanager.model.DormAdmins;
import com.example.stumanager.service.DormAdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//在controller中不会调用mapper，只会调用service
@Service
public class DormAdminsServiceImpl implements DormAdminsService {

    @Autowired
    private DormAdminsMapper mapper;
    @Override
    public DormAdmins queryById(Integer id) {
        DormAdmins bean = mapper.queryById(id);
        bean.setPassword("");
        return bean;
    }

    @Override
    public DormAdmins login(String account, String password) {
        DormAdmins bean = mapper.login(account, password);
        bean.setPassword("");
        return bean;
    }


}
