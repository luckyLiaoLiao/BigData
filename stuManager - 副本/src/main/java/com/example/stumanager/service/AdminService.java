package com.example.stumanager.service;

import com.example.stumanager.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Admin queryById(Integer id);
}
