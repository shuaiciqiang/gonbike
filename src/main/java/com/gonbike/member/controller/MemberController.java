package com.gonbike.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gonbike.common.controller.BaseController;
import com.gonbike.common.utils.HelpUtil;
import com.gonbike.system.domain.UserToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.gonbike.system.domain.UserDO;
import com.gonbike.system.service.UserService;
import com.gonbike.common.utils.PageUtils;
import com.gonbike.common.utils.Query;
import com.gonbike.common.utils.R;

/**
 * 
 * 会员管理
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-15 00:17:14
 */
 
@Controller
@RequestMapping("/member/user")
@Api(value="会员管理",tags={"会员操作操作接口"})
public class MemberController extends BaseController {
	@Autowired
	private UserService userService;
	
	@GetMapping()
	@RequiresPermissions("member:user:user")
	String User(){
	    return "member/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("member:user:user")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		UserDO vUserDO=new UserDO();
		vUserDO.setFromType(0);
		vUserDO.setOffset(Integer.parseInt(params.get("offset").toString()));
		vUserDO.setLimit(Integer.parseInt(params.get("limit").toString()));
		List<UserDO> userList = userService.listByUser(vUserDO);
		int total = userService.countByUser(vUserDO);
		PageUtils pageUtils = new PageUtils(userList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("member:user:add")
	String add(){
	    return "member/user/add";
	}

	@GetMapping("/edit/{userId}")
	@RequiresPermissions("member:user:edit")
	String edit(@PathVariable("userId") Long userId,Model model){
		UserDO user = userService.get(userId);
		model.addAttribute("user", user);
	    return "member/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("member:user:add")
	public R save( UserDO user){
		user.setFromType(0);
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("member:user:edit")
	public R update( UserDO user){
		user.setFromType(0);
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("member:user:remove")
	public R remove( Long userId){
		if(userService.remove(userId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("member:user:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] userIds){
		userService.batchremove(userIds);//.batchRemove(userIds);
		return R.ok();
	}


	@ResponseBody
	@RequestMapping(value="/pub/login",method= {RequestMethod.GET,RequestMethod.POST})
	@ApiOperation(value="商城会员登录",httpMethod="POST",notes="username是登录用户名password是登录密码")
	public R login(String username,String password){
		Map<String,Object> map=new HashMap<String,Object>();
		UserDO user =new UserDO();
		user.setFromType(0);
		user.setUserId(124l);
		user.setUsername("吴亦凡");
		String token= HelpUtil.getGUID();

		UserToken userToken=new UserToken();
		userToken.setUserId(user.getUserId());
		userToken.setId(user.getUserId());
		userToken.setToken(token);
		userToken.setCreateTime(HelpUtil.getDateTime());

		userService.saveUserToken(userToken);

		map.put("token",token);
		map.put("user",user);
		return R.ok(map);
	}

	@ResponseBody
	@RequestMapping(value="/api/info/{userId}",method= RequestMethod.GET)
	@ApiOperation(value="商城会员个人信息",httpMethod="GET",notes ="userId是个人账号Id")
	public R getUserInfo(@PathVariable String userId){
		Map<String,Object> map=new HashMap<String,Object>();
		UserDO user =new UserDO();
		user.setFromType(0);
		user.setUserId(Long.valueOf(userId));
		user=userService.getUserByUserId(user);
		map.put("user",user);
		return R.ok(map);
	}

}
