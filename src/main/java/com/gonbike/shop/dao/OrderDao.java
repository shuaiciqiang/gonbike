package com.gonbike.shop.dao;

import com.gonbike.shop.domain.OrderDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 09:59:45
 */
@Mapper
public interface OrderDao {

	OrderDO get(Long orderId);
	
	List<OrderDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OrderDO order);
	
	int update(OrderDO order);
	
	int remove(Long order_id);
	
	int batchRemove(Long[] orderIds);
}
