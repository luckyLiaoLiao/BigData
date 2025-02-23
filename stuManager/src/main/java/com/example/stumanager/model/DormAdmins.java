package com.example.stumanager.model;

import lombok.Data;

/**
 *
 * @TableName dorm_admins
 */
@Data
public class DormAdmins {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private Object gender;

    /**
     *
     */
    private String phone;

    /**
     *
     */
    private String account;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private Integer dormId;

}