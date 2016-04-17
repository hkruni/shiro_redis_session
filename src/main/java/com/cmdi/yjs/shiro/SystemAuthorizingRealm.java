package com.cmdi.yjs.shiro;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmdi.yjs.model.User;
import com.cmdi.yjs.service.UserService;

@Service  
public class SystemAuthorizingRealm extends AuthorizingRealm {  
  
	@Autowired
    private UserService userService;  
  
    // 获取授权信息  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
    // 获取认证信息 ,执行subject.login(token)时会调用该方法进行登录验证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
    	UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        User user = userService.getUser(token.getUsername());
        if(user == null) {
        	throw new UnknownAccountException ();
        } else if("locked".equals(user.getLocked())) {
        	throw new LockedAccountException ();
        }
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());  
    }  
}  
