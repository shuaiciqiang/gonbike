package com.gonbike.common.controller;

import com.gonbike.system.domain.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.gonbike.common.utils.ShiroUtils;
import com.gonbike.system.domain.UserDO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class BaseController {
	@Autowired
	protected HttpServletRequest request;

	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUserName() {
		return getUser().getUsername();
	}

}