package com.gonbike.common.controller;

import com.gonbike.system.domain.UserToken;
import org.springframework.stereotype.Controller;
import com.gonbike.common.utils.ShiroUtils;
import com.gonbike.system.domain.UserDO;

@Controller
public class BaseController {
	public UserDO getUser() {
		return ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUserName() {
		return getUser().getUserName();
	}
}