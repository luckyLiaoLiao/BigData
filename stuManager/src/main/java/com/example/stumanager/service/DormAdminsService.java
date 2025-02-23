package com.example.stumanager.service;

import com.example.stumanager.model.DormAdmins;

public interface DormAdminsService {

    DormAdmins queryById(Integer id);
    DormAdmins login(String account,String password);
}
