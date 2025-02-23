package com.example.stumanager.model;

import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String account;
    private String password;
    private String createdate;
    private Integer status;


}
