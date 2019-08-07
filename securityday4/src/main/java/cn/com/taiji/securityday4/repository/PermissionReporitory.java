package cn.com.taiji.securityday4.repository;

import cn.com.taiji.securityday4.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionReporitory extends JpaRepository<Permission, Long> {
    public Permission findByUrl(String url);
}