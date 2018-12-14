package com.gonbike.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gonbike.system.domain.LabelDO;
import com.gonbike.system.service.LabelService;
import com.gonbike.common.controller.BaseController;
import com.gonbike.common.utils.DateUtils;
import com.gonbike.common.utils.PageUtils;
import com.gonbike.common.utils.Query;
import com.gonbike.common.utils.R;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 17:02:21
 */
 
@Controller
@RequestMapping("/system/label")
public class LabelController extends BaseController {
	@Autowired
	private LabelService labelService;
	
	@GetMapping()
	@RequiresPermissions("system:label:label")
	String Label(){
	    return "system/label/label";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:label:label")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LabelDO> labelList = labelService.list(query);
		int total = labelService.count(query);
		PageUtils pageUtils = new PageUtils(labelList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:label:add")
	String add(){
	    return "system/label/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:label:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LabelDO label = labelService.get(id);
		model.addAttribute("label", label);
	    return "system/label/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:label:add")
	public R save( LabelDO label){
		label.setCreateUserId(getUser().getUserId());
		label.setCreateUser(getUser().getUsername());
		label.setCreateDate(DateUtils.getDateTime());
		if(labelService.save(label)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:label:edit")
	public R update( LabelDO label){
		label.setModifyUserId(getUser().getUserId());
		label.setModifyUser(getUser().getUsername());
		label.setModifyDate(DateUtils.getDateTime());
		labelService.update(label);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:label:remove")
	public R remove( Integer id){
		if(labelService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:label:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		labelService.batchRemove(ids);
		return R.ok();
	}
	
}
