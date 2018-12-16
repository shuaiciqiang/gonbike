package com.gonbike.shop.controller;

import java.util.List;
import java.util.Map;

import com.gonbike.common.controller.BaseController;
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

import com.gonbike.shop.domain.AddressDO;
import com.gonbike.shop.service.AddressService;
import com.gonbike.common.utils.PageUtils;
import com.gonbike.common.utils.Query;
import com.gonbike.common.utils.R;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 10:51:02
 */
 
@Controller
@RequestMapping("/shop/address")
public class AddressController extends BaseController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping()
	@RequiresPermissions("shop:address:address")
	String Address(){
	    return "shop/address/address";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("shop:address:address")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<AddressDO> addressList = addressService.list(query);
		int total = addressService.count(query);
		PageUtils pageUtils = new PageUtils(addressList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("shop:address:add")
	String add(){
	    return "shop/address/add";
	}

	@GetMapping("/edit/{addressId}")
	@RequiresPermissions("shop:address:edit")
	String edit(@PathVariable("addressId") Long addressId,Model model){
		AddressDO address = addressService.get(addressId);
		model.addAttribute("address", address);
	    return "shop/address/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("shop:address:add")
	public R save( AddressDO address){
		if(addressService.save(address)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("shop:address:edit")
	public R update( AddressDO address){
		addressService.update(address);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("shop:address:remove")
	public R remove( Long addressId){
		if(addressService.remove(addressId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("shop:address:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		addressService.batchRemove(ids);
		return R.ok();
	}
	
}
