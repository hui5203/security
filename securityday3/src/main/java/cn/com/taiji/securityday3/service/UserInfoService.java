package cn.com.taiji.securityday3.service;

import cn.com.taiji.securityday3.domain.UserInfo;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-02 16:56
 */
public interface UserInfoService {
    public UserInfo findByUsername(String username);
}