package cn.com.taiji.secutity.securityday1.repository;

import cn.com.taiji.secutity.securityday1.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-02 16:19
 */
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    public UserInfo findByUsername(String username);
}