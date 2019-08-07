//package cn.com.taiji.secutity.securityday1.service;
//
//import cn.com.taiji.secutity.securityday1.domain.UserInfo;
//import cn.com.taiji.secutity.securityday1.repository.UserInfoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
///**
// * @program: security
// * @description:
// * @author: 范彬慧
// * @create: 2019-08-02 17:01
// */
//@Service
//public class DataInit {
//    @Autowired
//    private UserInfoRepository userInfoRepository;
//    @PostConstruct
//    public void dataInit() {
//        UserInfo admin = new UserInfo();
//        admin.setUsername("admin");
//        admin.setPassword("123456");
//        admin.setRole(UserInfo.Role.ROLE_ADMIN);
//        userInfoRepository.save(admin);
//        UserInfo user = new UserInfo();
//        user.setUsername("user");
//        user.setPassword("123456");
//        user.setRole(UserInfo.Role.ROLE_USER);
//        userInfoRepository.save(user);
//    }
//}