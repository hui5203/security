package cn.com.taiji.secutity.securityday1.repository;

import cn.com.taiji.secutity.securityday1.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

}