package com.guangguanger.MyCRUD.web.security;

import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import com.guangguanger.MyCRUD.web.model.Permission;
import com.guangguanger.MyCRUD.web.model.Role;
import com.guangguanger.MyCRUD.web.model.User;
import com.guangguanger.MyCRUD.web.service.PermissionService;
import com.guangguanger.MyCRUD.web.service.RoleService;
import com.guangguanger.MyCRUD.web.service.UserService;

/**
 * 用户身份验证,授权 Realm 组件
 * 
 * @author StarZou
 * @since 2014年6月11日 上午11:35:28
 * （teng：shiro：4）：securityRealm
 **/
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    /**
     * 获取角色和权限: 自动调用，也可显式调用。
     * （1）PrincipalCollection是一个身份集合，现在就一个Realm，所以直接调用getPrimaryPrincipal
     * 得到之前传入的用户名，然后根据用户名调用userService获得角色和权限信息。
     * （2）授权回调函数，进行鉴权单缓存中午用户的授权信息时会调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String username = String.valueOf(principals.getPrimaryPrincipal());

        final User user = userService.selectByUsername(username); // 根据用户名获取用户信息，从而获得角色和权限
        final List<Role> roleInfos = roleService.selectRolesByUserId(user.getId());
        for (Role role : roleInfos) {
            // 添加角色
            System.err.println(role);
            authorizationInfo.addRole(role.getRoleSign());

            final List<Permission> permissions = permissionService.selectPermissionsByRoleId(role.getId());
            for (Permission permission : permissions) {
                // 添加权限
                System.err.println(permission);
                authorizationInfo.addStringPermission(permission.getPermissionSign());
            }
        }
        return authorizationInfo;
    }

    /**
     * 登录验证：SecurityUtils.getSubject().login登录时传入AuthenticationToken子类对象
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = String.valueOf(token.getPrincipal());
        String password = new String((char[]) token.getCredentials());
        // 通过数据库进行验证
        final User authentication = userService.authentication(new User(username, password));
        if (authentication == null) {
            throw new AuthenticationException("用户名或密码错误.");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, getName());
        return authenticationInfo;
    }

}
