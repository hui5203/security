package cn.com.taiji.secutity.securityday1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @program: securityday1
 * @description: loginController
 * @author: 范彬慧
 * @create: 2019-08-01 15:12
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String Login(){
        return "login";
    }


    @PostMapping("/success")
    public String Success(){
        return "success";
    }
}
