package cn.com.taiji.securityday3.config;

import cn.com.taiji.securityday3.extend.CustomUserDetailService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;


/**
 * @program: securityday1
 * @description: 配置类中的security配置类
 * @author: 范彬慧
 * @create: 2019-08-01 11:11
 */
@EnableWebSecurity(debug = true)  //组合注解，@EnableWebSecurity完成的工作便是加载了WebSecurityConfiguration，AuthenticationConfiguration这两个核心配置类...debug = true表示可以看日志
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String CAPTCHA_SESSION_KEY = "captcha";
    //日志
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CustomCaptchaFilter customFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.debug("hello world");
        //请求授权
        //permitAll()是允许所有的意思，若不写，则没有权限访问login.html页面
        http.authorizeRequests()
                .antMatchers("/error","/captcha.jpg").permitAll()
//                .antMatchers("/user").hasAnyRole("USER","ADMIN")
//                .antMatchers("/admin").hasRole("ADMIN" )
                .anyRequest()
                .access("@customAuthService.canAccess(request,authentication)")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
//                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
//                    httpServletResponse.setContentType("application/json;charset=UTF-8");
//                    httpServletResponse.getWriter().write("{'success':'0','message':'欢迎登录'}");
//                }).failureHandler((httpServletRequest, httpServletResponse, e) -> {
//                    httpServletResponse.setContentType("application/json;charset=UTF-8");
//                    httpServletResponse.getWriter().write("{'error_code':'401','message':'"+e.getMessage()+"'}");
//                });
        http.csrf().disable();
        http.sessionManagement().maximumSessions(1);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore();
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


    @Bean
    public DefaultKaptcha captcha() { // 配置图形验证码的基本参数
        Properties properties = new Properties();
        // 图片宽度
        properties.setProperty("kaptcha.image.width", "150");
        // 图片长度
        properties.setProperty("kaptcha.image.height", "50");
        // 字符集
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789");
        // 字符长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        Config config = new Config(properties);
        // 使用默认的图形验证码实现，当然也可以自定义实现
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}