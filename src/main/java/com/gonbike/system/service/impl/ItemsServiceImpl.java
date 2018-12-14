package com.gonbike.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gonbike.system.dao.ItemsDao;
import com.gonbike.system.domain.ItemsDO;
import com.gonbike.system.service.ItemsService;



@Service
public class ItemsServiceImpl implements ItemsService {
	@Autowired
	private ItemsDao itemsDao;
	
	@Override
	public ItemsDO get(Long id){
		return itemsDao.get(id);
	}
	
	@Override
	public List<ItemsDO> list(Map<String, Object> map){
		return itemsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return itemsDao.count(map);
	}
	
	@Override
	public int save(ItemsDO items){
		return itemsDao.save(items);
	}
	
	@Override
	public int update(ItemsDO items){
		return itemsDao.update(items);
	}
	
	@Override
	public int remove(Long id){
		return itemsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return itemsDao.batchRemove(ids);
	}
	
}
