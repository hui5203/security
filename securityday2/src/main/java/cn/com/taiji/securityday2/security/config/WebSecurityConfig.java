package cn.com.taiji.securityday2.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
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
    private DataSource dataSource;

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
                    httpServletResponse.getWriter().write("{'success_code':'0','message':'欢迎登录'}");
                }).failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.getWriter().write("{'error_code':'401','message':'"+e.getMessage()+"'}");
                });
        http.csrf().disable();
//                .and()
//                .httpBasic();
    }



    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        //不要加super.configure(web);，因为有配置文件优先级问题
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .withUser("user").password(passwordEncoder().encode("123")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("456")).roles("ADMIN");
    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//        web.ignoring().antMatchers("/css/**","/images/**","/js/**","/webjars/**");
//    }


    //使用内存来设置用户名和密码
//    @Bean
//    public UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user").password(passwordEncoder()
//                .encode("1")).roles("USER").build());
//        manager.createUser(User.withUsername("admin").password(passwordEncoder()
//                .encode("2")).roles("ADMIN").build());
//
//        return manager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        JdbcUserDetailsManager manager=new JdbcUserDetailsManager();
//        manager.setDataSource(dataSource);
//        manager.createUser(User.withUsername("user").password(passwordEncoder()
//                .encode("1")).roles("USER").build());
//        manager.createUser(User.withUsername("admin").password(passwordEncoder()
//                .encode("2")).roles("ADMIN").build());
//        return manager;
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}