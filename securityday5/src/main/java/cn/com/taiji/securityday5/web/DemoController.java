package cn.com.taiji.securityday5.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class DemoController {
    private Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/admin")
    public String admin() {
        return "return admin";
    }

    @GetMapping("/user")
    public String user() {
        return "return user";
    }

    @DeleteMapping("/user")
    public String d() {
        return "删除用户成功";
    }

    /**
     * controller方法的CORS配置，您可以向@RequestMapping注解处理程序方法添加一个@CrossOrigin注解，
     * 以便启用CORS（默认情况下，@CrossOrigin允许在@RequestMapping注解中指定的所有源和HTTP方法）：
     *      其中@CrossOrigin中的2个参数：
            origins  ： 允许可访问的域列表
     *      maxAge：准备响应前的缓存持续的最大时间（以秒为单位）。
     */
    //    @CrossOrigin
    @PutMapping("/user")
    public String update(Map<String,String> user) {
        logger.info("User={}",user);
        return "更新用户成功";
    }

    @PostMapping("/user")
    public String add(@RequestParam Map<String,String> user) {
        logger.info("User={}",user);
        return "增加用户成功";
    }

    @GetMapping("/a")
    public String a() {
        return "return a";
    }

    @GetMapping("/b")
    public Object b() {
        Object result=restTemplate.getForObject("https://www.baidu.com/",String.class);
        return result;
    }
}
