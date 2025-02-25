package com.example.stumanager.controller;

import com.example.stumanager.config.JwtConfig;
import com.example.stumanager.model.Admin;
import com.example.stumanager.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 请求路径前缀
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService service;

    @Resource
    private JwtConfig jwtConfig;

    @RequestMapping("/queryById")
    public Object queryById(Integer id) {
        return service.queryById(id);
    }

    @PostMapping("/login_form")
    public Object loginWithForm(Admin admin) {
        return service.login(admin.getAccount(), admin.getPassword());
    }
//    注意：参数前不加@RequestBody，就只能通过form形式传递参数，加上则只能用json传递


//    @Transactional
    @PostMapping("/login")
    public Object login(@RequestBody Admin bean) {

        Admin loginUser = service.login(bean.getAccount(), bean.getPassword());
        if (loginUser != null) {
//            登录成功
//            产生token
            return jwtConfig.createToken(loginUser.getAccount());
        }
        return "登录失败";
    }
}
