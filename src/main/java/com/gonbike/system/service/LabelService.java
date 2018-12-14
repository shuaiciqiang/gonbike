package com.gonbike.system.service;

import com.gonbike.system.domain.LabelDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 17:02:21
 */
public interface LabelService {
	
	LabelDO get(Integer id);
	
	List<LabelDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LabelDO label);
	
	int update(LabelDO label);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
