package cn.com.taiji.securityday5.service;

import cn.com.taiji.securityday5.domain.UserInfo;

public interface UserDomainService {
    UserInfo findByUsername(String username);
}
