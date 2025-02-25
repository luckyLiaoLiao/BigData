package com.example.stumanager.controller;

import com.example.stumanager.model.User;
import jakarta.websocket.OnClose;
import org.springframework.web.bind.annotation.*;

//@Controller 这个控制器需要页面模版
//这是一个只返回数据的控制器，与页面模版无关
@RestController
public class HelloController {

    //    控制器中的方法在浏览器中的访问地址http://localhost:8080/hello
    @RequestMapping("/hello")
    public String hello_get() {
        return "Hello Springboot";
    }

    //    http://localhost:8080/hello_post
    @RequestMapping(value = "/hello_post", method = RequestMethod.POST)
    public String hello_post() {
        return "Hello Springboot";
    }

    //    http://localhost:8080/hello_get_params?name=廖廖&age=18
    @RequestMapping("/hello_get_params")
    public String hello_get_params(String name, Integer age) {
        return "Hello," + age + "岁的" + name;
    }


    @RequestMapping(value = "/hello_post_params", method = RequestMethod.POST)
    public String hello_post_params(String username, String passwd) {
        return "username：" + username + "，passwd：" + passwd;
    }

    @RequestMapping(value = "/RequestParam_required", method = RequestMethod.POST)
    public String RequestParam_required(@RequestParam String username, String passwd) {
        return "username：" + username + "，passwd：" + passwd;
    }

    @RequestMapping(value = "/RequestParam_required_defaultValue", method = RequestMethod.POST)
    public String RequestParam_required_defaultValue(@RequestParam String username, String passwd, @RequestParam(defaultValue = "nick") String nickname) {
        return "username：" + username + "，passwd：" + passwd + "，nickname：" + nickname;
    }

    @RequestMapping(value = "/RequestParam_default", method = RequestMethod.POST)
    public String RequestParam_default(@RequestParam String username, @RequestParam(value = "密码", required = false) String passwd) {
        return "username：" + username + "，passwd：" + passwd;
    }

    @RequestMapping(value = "/RequestParam_alias", method = RequestMethod.POST)
    public String hello_post_params_RequestParam(@RequestParam("yourParaName") String username, String passwd) {
        return "username：" + username + "，passwd：" + passwd;
    }

    @RequestMapping("test_pathVariable/{id}")
    public String test_pathVariable(@PathVariable Integer id){
        return "id: " + id;
    }

    @RequestMapping("test_RequestBody")
    public Object test_RequestBody(@RequestBody User user){
        user.setUsername(user.getUsername()+"，还可以对传入的参数做一些处理哦");
        return user;
    }

    @RequestMapping("test_RequestHeader")
    public Object test_RequestHeader(@RequestHeader String a,@RequestHeader String auth){
        return "a: " + a + "，auth: " + auth;
    }
}

