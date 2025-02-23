package com.example.stumanager.service;

import com.example.stumanager.model.Admin;


public interface AdminService {

    Admin queryById(Integer id);
    Admin login(String account,String password);
}
