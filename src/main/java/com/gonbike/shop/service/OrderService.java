package com.gonbike.shop.service;

import com.gonbike.shop.domain.OrderDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 09:59:45
 */
public interface OrderService {
	
	OrderDO get(Long orderId);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long orderId);
	
	int batchRemove(Long[] orderIds);
}
