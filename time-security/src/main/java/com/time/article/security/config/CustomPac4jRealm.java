package com.time.article.security.config;

import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.token.Pac4jToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.pac4j.core.profile.CommonProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author suiguozhen on 18/05/10
 */
public class CustomPac4jRealm extends Pac4jRealm {
    //假的权限
    private final static String SELFAUTHZ = "user:edit";

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomPac4jRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        Pac4jToken token = (Pac4jToken) authenticationToken;
        LinkedHashMap<String, CommonProfile> profiles = token.getProfiles();
        // 获取用户信息
        CommonProfile casProfile = profiles.get("rest");
        return super.doGetAuthenticationInfo(authenticationToken);
    }

    /**
     * 由于使用了SSO单点登录系统，此Ream只负责授权
     * 权限认证（为当前登录的Subject授予角色和权限）
     *
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		String userName = (String) super.getAvailablePrincipal(principals) ; //获取用户名
//		System.out.println("========================用户名字username->"+JSON.toJSONString(userName));
        SimpleAuthorizationInfo info=null ;
        try{

            // 权限信息对象info，用来存放查出的用户的所有的角色及权限
            info = new SimpleAuthorizationInfo();
            //假设hsjhsj没有权限=【"user:edit"】
            List<String> permissions = new ArrayList<String>();
            permissions.add(SELFAUTHZ);
            info.addStringPermissions(permissions);


        }catch(Exception e){
            LOGGER.info("=====================ShiroCasRealm异常---------------");
        }
        return info;
    }
}
