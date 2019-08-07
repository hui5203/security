package cn.com.taiji.securityday3.repository;

import cn.com.taiji.securityday3.domain.UserInfo;
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