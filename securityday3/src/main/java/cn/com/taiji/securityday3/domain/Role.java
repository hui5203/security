package cn.com.taiji.securityday3.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @program: security
 * @description:角色类
 * @author: 范彬慧
 * @create: 2019-08-05 10:27
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private long rid;//主键.
    private String name;//角色名称.
    private String description;//角色描述.

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
