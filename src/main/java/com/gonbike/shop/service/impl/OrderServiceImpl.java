package com.gonbike.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gonbike.shop.dao.OrderDao;
import com.gonbike.shop.domain.OrderDO;
import com.gonbike.shop.service.OrderService;



@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public OrderDO get(Long orderId){
		return orderDao.get(orderId);
	}
	
	@Override
	public List<OrderDO> list(Map<String, Object> map){
		return orderDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return orderDao.count(map);
	}
	
	@Override
	public int save(OrderDO order){
		return orderDao.save(order);
	}
	
	@Override
	public int update(OrderDO order){
		return orderDao.update(order);
	}
	
	@Override
	public int remove(Long orderId){
		return orderDao.remove(orderId);
	}
	
	@Override
	public int batchRemove(Long[] orderIds){
		return orderDao.batchRemove(orderIds);
	}
	
}
