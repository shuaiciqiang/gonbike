package com.gonbike.system.dao;

import com.gonbike.system.domain.CategoriesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-14 18:33:10
 */
@Mapper
public interface CategoriesDao {

	CategoriesDO get(Long id);
	
	List<CategoriesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(CategoriesDO categories);
	
	int update(CategoriesDO categories);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
