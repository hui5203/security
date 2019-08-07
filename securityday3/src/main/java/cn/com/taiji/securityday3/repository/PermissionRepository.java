package cn.com.taiji.securityday3.repository;

import cn.com.taiji.securityday3.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission , Long> {
    public Permission findByUrl(String url);
}
