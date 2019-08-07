package cn.com.taiji.securityday4.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/login")
    public String myLogin() {
        return "Login";
    }

    @GetMapping("/")
    public String index() {
        logger.info("测试热部署");
        return "index";
    }

}
