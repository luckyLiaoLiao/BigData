package com.example.stumanager.model;

import lombok.Data;

/**
 * @TableName students
 */
@Data
public class Students {
    private Integer id;

    private String code;

    private String name;

    private Object gender;

    private Integer dormId;

    private String roomId;

    private String password;

    private String phone;
}