package cn.com.taiji.securityday3.repository;

import cn.com.taiji.securityday3.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

}