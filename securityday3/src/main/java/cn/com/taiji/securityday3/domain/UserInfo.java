package cn.com.taiji.securityday3.domain;

import javax.persistence.*;
import java.util.List;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-02 16:16
 */
@Entity
public class UserInfo {
//    public enum Role{    //这里是写死的，直接用的admin和user
//        ROLE_ADMIN,ROLE_USER
//    }
    @Id
    @GeneratedValue
    private long uid;//主键.
    private String username;//用户名.
    private String password;//密码.
//    @Enumerated(EnumType.STRING)
//    private Role role;

    //用户－－角色：多对多的关系．
    @ManyToMany(fetch= FetchType.EAGER)//立即从数据库中进行加载数据;
    @JoinTable(name = "UserRole", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns ={@JoinColumn(name = "rid") })
    private List<Role> roles;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }


    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}