package com.gonbike.shop.controller;

import java.util.List;
import java.util.Map;

import com.gonbike.common.controller.BaseController;
import com.gonbike.common.utils.IDCreater;
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

import com.gonbike.shop.domain.OrderDO;
import com.gonbike.shop.service.OrderService;
import com.gonbike.common.utils.PageUtils;
import com.gonbike.common.utils.Query;
import com.gonbike.common.utils.R;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 09:59:45
 */
 
@Controller
@RequestMapping("/shop/order")
public class OrderController extends BaseController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping()
	@RequiresPermissions("shop:order:order")
	String Order(){
	    return "shop/order/order";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("shop:order:order")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<OrderDO> orderList = orderService.list(query);
		int total = orderService.count(query);
		PageUtils pageUtils = new PageUtils(orderList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("shop:order:add")
	String add(){
	    return "shop/order/add";
	}

	@GetMapping("/edit/{orderId}")
	@RequiresPermissions("shop:order:edit")
	String edit(@PathVariable("orderId") String orderId,Model model){
		OrderDO order = orderService.get(Long.valueOf(orderId));
		model.addAttribute("order", order);
	    return "shop/order/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("shop:order:add")
	public R save( OrderDO order){
		order.setOrderId(IDCreater.getNextID());
		if(orderService.save(order)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("shop:order:edit")
	public R update( OrderDO order){
		orderService.update(order);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("shop:order:remove")
	public R remove( String orderId){
		if(orderService.remove(Long.valueOf(orderId))>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("shop:order:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] orderIds){
		orderService.batchRemove(orderIds);
		return R.ok();
	}
	
}
