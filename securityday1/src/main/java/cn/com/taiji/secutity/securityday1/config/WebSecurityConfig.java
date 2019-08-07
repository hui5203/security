package cn.com.taiji.secutity.securityday1.config;

import cn.com.taiji.secutity.securityday1.service.CustomUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @program: securityday1
 * @description: 配置类中的security配置类
 * @author: 范彬慧
 * @create: 2019-08-01 11:11
 */
@EnableWebSecurity(debug = true)  //组合注解，@EnableWebSecurity完成的工作便是加载了WebSecurityConfiguration，AuthenticationConfiguration这两个核心配置类...debug = true表示可以看日志
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("hello world");
        //请求授权
        //permitAll()是允许所有的意思，若不写，则没有权限访问login.html页面
        http.authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .successForwardUrl("/success").permitAll();
                .permitAll()
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.getWriter().write("{'error_code':'0','message':'欢迎登录'}");
                }).failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.getWriter().write("{'error_code':'401','message':'"+e.getMessage()+"'}");
                });
        http.csrf().disable();
//                .and()
//                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password(passwordEncoder()
//                .encode("123")).roles("USER").build());
//        manager.createUser(User.withUsername("admin").password(passwordEncoder()
//                .encode("123")).roles("ADMIN").build());
//
//        return manager;
//    }

    /**
     * 密码加密处理
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}