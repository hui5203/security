package cn.com.taiji.securityday3.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-05 14:33
 */
@Entity
public class Permission {
    @Id
    private long pid;//主键

    private String url;//授权链接

    @ManyToMany(fetch= FetchType.EAGER)
    @JoinTable(name="RolePermission",joinColumns= {@JoinColumn(name="pid")} , inverseJoinColumns= {@JoinColumn(name="rid")})
    private List<Role> roles;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
