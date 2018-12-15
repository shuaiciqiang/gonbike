package com.gonbike.system.dao;

import com.gonbike.system.domain.LabelDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 17:02:21
 */
@Mapper
public interface LabelDao {

	LabelDO get(Integer id);
	List<LabelDO> getLabelListByItemId(String itemId);
	List<LabelDO> list(Map<String, Object> map);
	List<LabelDO> getLabelList(LabelDO label);
	int count(Map<String, Object> map);
	
	int save(LabelDO label);
	
	int update(LabelDO label);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
