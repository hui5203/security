package cn.com.taiji.securityclassoauth2client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-07 16:50
 */
@RestController
public class SimpleController {
    @GetMapping("/")
    public String aaa(Principal principal) {
        return "hello，" + principal.getName();
    }

    //参数中的Principal对象由Spring框架自动注入，表示当前登录的用户。
    @GetMapping
    public String hello(Principal principal){
        return "hello,"+principal.getName();
    }
}
