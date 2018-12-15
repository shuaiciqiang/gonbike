package com.gonbike.system.shiro;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.gonbike.common.config.ApplicationContextRegister;
import com.gonbike.system.domain.UserToken;
import com.gonbike.system.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.gonbike.common.utils.ShiroUtils;
import com.gonbike.system.dao.UserDao;
import com.gonbike.system.domain.UserDO;
import com.gonbike.system.service.MenuService;
import org.springframework.stereotype.Component;


@Component
public class UserRealm extends AuthorizingRealm {
/*	@Autowired
	UserDao userMapper;
	@Autowired
	MenuService menuService;*/
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof OAuth2Token;
	}

	/**
	 * 授权(验证权限时调用)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserDO user = (UserDO)principals.getPrimaryPrincipal();
		Long userId =  user.getUserId();

		//用户权限列表
		MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String accessToken = (String) token.getPrincipal();

		//根据accessToken，查询用户信息
		UserService userService =ApplicationContextRegister.getBean(UserService.class);
		UserToken userToken = userService.getUserTokenByTokenId(accessToken);
		//token失效
		SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date expireTime=null;
		try {
			expireTime=sDateFormat.parse(userToken.getExpireTime());
		}catch(Exception e){
			throw new IncorrectCredentialsException("token有效期过期，请重新登录");
		}
		if(userToken == null || expireTime.getTime() < System.currentTimeMillis()){
			throw new IncorrectCredentialsException("token失效，请重新登录");
		}
		SimpleAuthenticationInfo info=null;

		UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
		// 查询用户信息
		UserDO user =userMapper.get(userToken.getUserId());

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}


		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		info = new SimpleAuthenticationInfo(user, accessToken, getName());
		return info;
	}

}
