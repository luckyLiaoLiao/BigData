package com.example.stumanager.domain;

import lombok.Data;

/**
 * 
 * @TableName students
 */
@Data
public class Students {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String name;

    /**
     * 0：女，1：男
     */
    private Object gender;

    /**
     * dorms表的外键
     */
    private Integer dormId;

    /**
     * 寝室编号
     */
    private String roomId;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String phone;
}