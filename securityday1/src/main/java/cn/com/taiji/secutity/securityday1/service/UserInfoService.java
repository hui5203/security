package cn.com.taiji.secutity.securityday1.service;

import cn.com.taiji.secutity.securityday1.domain.UserInfo;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-02 16:56
 */
public interface UserInfoService {
    public UserInfo findByUsername(String username);
}