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

import com.gonbike.system.domain.ItemsDO;
import com.gonbike.system.service.ItemsService;
import com.gonbike.common.controller.BaseController;
import com.gonbike.common.utils.PageUtils;
import com.gonbike.common.utils.Query;
import com.gonbike.common.utils.R;

/**
 * 商品表
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 19:09:08
 */
 
@Controller
@RequestMapping("/system/items")
public class ItemsController extends BaseController {
	@Autowired
	private ItemsService itemsService;
	
	@GetMapping()
	@RequiresPermissions("system:items:items")
	String Items(){
	    return "system/items/items";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:items:items")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ItemsDO> itemsList = itemsService.list(query);
		int total = itemsService.count(query);
		PageUtils pageUtils = new PageUtils(itemsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:items:add")
	String add(){
	    return "system/items/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:items:edit")
	String edit(@PathVariable("id") Long id,Model model){
		ItemsDO items = itemsService.get(id);
		model.addAttribute("items", items);
	    return "system/items/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:items:add")
	public R save( ItemsDO items){
		if(itemsService.save(items)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:items:edit")
	public R update( ItemsDO items){
		itemsService.update(items);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:items:remove")
	public R remove( Long id){
		if(itemsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:items:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		itemsService.batchRemove(ids);
		return R.ok();
	}
	
}
