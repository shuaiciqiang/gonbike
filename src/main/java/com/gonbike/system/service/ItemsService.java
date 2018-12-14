package com.gonbike.system.service;

import com.gonbike.system.domain.ItemsDO;

import java.util.List;
import java.util.Map;

/**
 * 商品表
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 19:09:08
 */
public interface ItemsService {
	
	ItemsDO get(Long id);
	
	List<ItemsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ItemsDO items);
	
	int update(ItemsDO items);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
