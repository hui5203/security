package cn.com.taiji.securityday4.repository;

import cn.com.taiji.securityday4.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDomainRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
