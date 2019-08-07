package cn.com.taiji.securityday3.config;

import cn.com.taiji.securityday3.domain.Permission;
import cn.com.taiji.securityday3.domain.Role;
import cn.com.taiji.securityday3.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: security
 * @description:
 * @author: 范彬慧
 * @create: 2019-08-05 14:19
 */
@Component  //必须有
public class CustomAuthService {
    @Autowired
    private PermissionRepository permissionReporitory;

    public Boolean canAccess(HttpServletRequest request, Authentication authentication){
        //写一些动态鉴权逻辑
        //1.先判断当前的用户有没有认证过
        Object principal=authentication.getPrincipal();
        if(principal==null||"anonymousUser".equals(principal)){
            return false;
        }
        //2.如果是匿名的角色，ROLE_ANONYMOUS

        //3.动态鉴权逻辑
        //User
        //Role
        //Permission  存的是用户ID（uid）和资源（即所有url接口）的对应关系
        String url = request.getRequestURI();

        Permission permission = permissionReporitory.findByUrl(url);
        if(permission == null || CollectionUtils.isEmpty(permission.getRoles())){
            return false;
        }

        for (Role role : permission.getRoles()) {
            // 如果authentication当前用户拥有这个role角色，则返回true
            if(CollectionUtils.isEmpty(authentication.getAuthorities())){
                return false ;
            }

            for (GrantedAuthority authority : authentication.getAuthorities()) {
                //如果用户拥有访问某个url的权限的角色，就允许
                if(role.getName().equals(authority.getAuthority())){
                    return true ;
                }
            }
        }

        return false; //如果返回fales则不能进入任何一个页面，如果是true则可以进入
    }
}
