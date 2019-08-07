package cn.com.taiji.securityday4.service;

import cn.com.taiji.securityday4.domain.UserInfo;

public interface UserDomainService {
    UserInfo findByUsername(String username);
}
