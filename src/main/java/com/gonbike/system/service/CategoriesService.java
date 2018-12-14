package com.gonbike.system.service;

import com.gonbike.system.domain.CategoriesDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 18:33:10
 */
public interface CategoriesService {
	
	CategoriesDO get(Long id);
	
	List<CategoriesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CategoriesDO categories);
	
	int update(CategoriesDO categories);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
