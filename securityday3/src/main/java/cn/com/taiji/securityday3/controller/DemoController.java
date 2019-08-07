package cn.com.taiji.securityday3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: securityday1
 * @description: controller
 * @author: 范彬慧
 * @create: 2019-08-01 10:27
 */
@RestController
public class DemoController {
//    @GetMapping("/")
//    public String demo01(){
//        return "hello world";
//    }

    @GetMapping("/admin")
    public String Admin(){
        return "admin";
    }

    @GetMapping("/user")
    public String User(){
        return "user";
    }

    @GetMapping("/a")
    public String A(){
        return "A";
    }

    @GetMapping("/b")
    public String B(){
        return "B";
    }

}
