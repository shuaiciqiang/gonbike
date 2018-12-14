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

import com.gonbike.system.domain.CategoriesDO;
import com.gonbike.system.service.CategoriesService;
import com.gonbike.common.controller.BaseController;
import com.gonbike.common.utils.PageUtils;
import com.gonbike.common.utils.Query;
import com.gonbike.common.utils.R;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 18:33:10
 */
 
@Controller
@RequestMapping("/system/categories")
public class CategoriesController extends BaseController {
	@Autowired
	private CategoriesService categoriesService;
	
	@GetMapping()
	@RequiresPermissions("system:categories:categories")
	String Categories(){
	    return "system/categories/categories";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:categories:categories")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CategoriesDO> categoriesList = categoriesService.list(query);
		int total = categoriesService.count(query);
		PageUtils pageUtils = new PageUtils(categoriesList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("system:categories:add")
	String add(){
	    return "system/categories/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("system:categories:edit")
	String edit(@PathVariable("id") Long id,Model model){
		CategoriesDO categories = categoriesService.get(id);
		model.addAttribute("categories", categories);
	    return "system/categories/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("system:categories:add")
	public R save( CategoriesDO categories){
		if(categoriesService.save(categories)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:categories:edit")
	public R update( CategoriesDO categories){
		categoriesService.update(categories);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("system:categories:remove")
	public R remove( Long id){
		if(categoriesService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:categories:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		categoriesService.batchRemove(ids);
		return R.ok();
	}
	
}
