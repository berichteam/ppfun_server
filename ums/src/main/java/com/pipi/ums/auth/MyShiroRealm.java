package com.pipi.ums.auth;

import com.pipi.common.domain.Users;
import com.pipi.common.service.inter.UserService;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by lazyb on 2017/7/10.
 */
@CommonsLog
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        UsernamePasswordToken authToken = (UsernamePasswordToken) token;
        String phone = String.valueOf(authToken.getUsername());
        String pwd = String.valueOf(authToken.getPassword());
        Users user = userService.login(phone, pwd);
        if (user == null) {
            throw new AccountException("账号或密码不正确！");
        }
        return new SimpleAuthenticationInfo(phone, pwd, getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("权限认证方法：MyShiroRealm.doGetAuthenticationInfo()");
        String phone = super.getAvailablePrincipal(principals).toString();
        Users user = userService.findByPhone(phone);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> roleSet = new HashSet<>();
            roleSet.add("admin");
            info.setRoles(roleSet);
            return info;
        }
        return null;
    }
}
