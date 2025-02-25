package com.example.stumanager.model;

public class Admin {
    private Integer id;
    private String account;
    private String password;
    private String createdate;
    private Integer status;

    public Admin() {
    }

    public Admin(Integer id, String account, String password, String createdate, Integer status) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.createdate = createdate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createdate='" + createdate + '\'' +
                ", status=" + status +
                '}';
    }
}
