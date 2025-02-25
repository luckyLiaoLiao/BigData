package bootbatis.controller;

import bootbatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//controller是用来控制跳转的

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
//    这里的"/"就是templates的根目录
    public String index(){
//        这里的index就是src/main/resources/templates/index.html
//        这里可以按住ctrl点开index，是因为添加了thymeleaf依赖
        return "index";
    }

}
