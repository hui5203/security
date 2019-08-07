package cn.com.taiji.secutity.securityday1.service;

import cn.com.taiji.secutity.securityday1.domain.Role;
import cn.com.taiji.secutity.securityday1.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-02 16:58
 */
@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailService.loadUserByUsername()");
        //通过username获取用户信息
        UserInfo userInfo = userInfoService.findByUsername(username);
        if(userInfo == null) {
            throw new UsernameNotFoundException("not found");
        }
//        //定义权限列表.
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        //用户可以访问的资源名称（或者说用户所拥有的权限)注意：必须"ROLE_"开头
//        authorities.add(new SimpleGrantedAuthority(userInfo.getRole().name()));

        //定义权限列表.
        List<GrantedAuthority> authorities = new ArrayList<>();
        //用户可以访问的资源名称（或者说用户所拥有的权限）注意：必须"ROLE_"开头
        for(Role role:userInfo.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }

        User userDetails = new User(userInfo.getUsername(),passwordEncoder.encode(userInfo.getPassword()),authorities);
        return userDetails;
    }

}