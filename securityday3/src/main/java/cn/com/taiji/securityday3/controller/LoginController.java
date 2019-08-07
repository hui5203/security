package cn.com.taiji.securityday3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @GetMapping("/login")
    public String Login(){
        return "login";
    }


    @PostMapping("/")
    public String Success(){
        logger.info("测试热部署");
        return "success";
    }
}
