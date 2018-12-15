package com.gonbike.system.controller;

import com.gonbike.common.annotation.Log;
import com.gonbike.common.controller.BaseController;
import com.gonbike.common.domain.FileDO;
import com.gonbike.common.domain.Tree;
import com.gonbike.common.service.FileService;
import com.gonbike.common.utils.HelpUtil;
import com.gonbike.common.utils.MD5Utils;
import com.gonbike.common.utils.R;
import com.gonbike.common.utils.ShiroUtils;
import com.gonbike.system.domain.MenuDO;
import com.gonbike.system.domain.UserDO;
import com.gonbike.system.domain.UserToken;
import com.gonbike.system.service.MenuService;
import com.gonbike.system.service.UserService;
import io.swagger.models.auth.In;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@Autowired
	UserService userService;
	
	/**
	 * 项目启动，默认进入页面
	 * @param model
	 * @return
	 */
	@GetMapping({ "/", "" })
	String welcome(Model model) {

		return "redirect:/login";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/photo_s.jpg");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_s.jpg");
		}
		model.addAttribute("userName", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password) {
		String token=null;
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().toString().equals("JSESSIONID")){
				token=cookie.getValue().toUpperCase().replace("-","");
			}
		}
		Map<String,Object> map=new HashMap<>();
		password = MD5Utils.encrypt(username, password);
		//去数据库校验
		UserDO vUser=new UserDO();
		vUser.setUsername(username);
		vUser.setPassword(password);
		UserDO user=userService.getUserForLogin(vUser);
		if (user==null){
			return R.error("用户或密码错误");
		}else {
			//创建token

			UserToken userToken=new UserToken();
			userToken.setId(user.getUserId());
			userToken.setUserId(user.getUserId());
			if (token==null) {
				userToken.setToken(HelpUtil.getGUID());
			}else{
				userToken.setToken(token);
			}
			userToken.setCreateTime(HelpUtil.getDateTime());

			userService.saveUserToken(userToken);
			map.put("token",userToken.getToken());
			map.put("expireTime",userToken.getExpireTime());
/*
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();

			try {
				subject.login(token);
				return R.ok();
			} catch (AuthenticationException e) {
				e.printStackTrace();
				return R.error("用户或密码错误");
			}
			*/
		}
		return R.ok(map);
	}

	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
