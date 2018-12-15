package com.gonbike.system.dao;

import com.gonbike.system.domain.ItemLabelDO;
import com.gonbike.system.domain.ItemsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 商品表
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 19:09:08
 */
@Mapper
public interface ItemsDao {

	ItemsDO get(Long id);
	
	List<ItemsDO> list(Map<String, Object> map);
	void insertItemLabel(ItemLabelDO itemLabel);
	void deleteItemLabelByItemId(Long itemId);
	void updateItemLabelByItemId(Long itemId);
	int count(Map<String, Object> map);
	
	int save(ItemsDO items);
	
	int update(ItemsDO items);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
