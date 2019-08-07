package cn.com.taiji.securityday3.controller;

import cn.com.taiji.securityday3.config.WebSecurityConfig;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @program: security
 * @description: CaptchaController
 * @author: 范彬慧
 * @create: 2019-08-06 08:56
 */
@Controller
public class CaptchaController {
    @Autowired
    private Producer captchaProducer;

    @GetMapping("/captcha.jpg")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse
            response) throws IOException { // 设置内容类型
        response.setContentType("image/jpeg");
        // 创建验证码文本
        String capText = captchaProducer.createText();

        // 将验证码文本设置到 session
//        request.getSession().setAttribute("captcha", capText);
        request.getSession().setAttribute(WebSecurityConfig.CAPTCHA_SESSION_KEY, capText);

        // 创建验证码图片
        BufferedImage bi = captchaProducer.createImage(capText);

        // 获取响应输出流
        ServletOutputStream out = response.getOutputStream();
        // 将图片验证码数据写到响应输出流
        ImageIO.write(bi, "jpg", out);

        // 推送并关闭响应输出流
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}