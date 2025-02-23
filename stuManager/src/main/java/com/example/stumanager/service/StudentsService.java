package com.example.stumanager.service;

import com.example.stumanager.model.Students;

/**
* @author meira
* @description 针对表【students】的数据库操作Service
* @createDate 2025-02-23 16:12:19
*/
public interface StudentsService {
    Students queryById(Integer id);
    Students login(String code,String password);
}
