package com.gonbike.base.dao;


import java.util.List;
import java.util.Map;

import com.gonbike.base.domain.RegionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 省市区表
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 13:28:24
 */
@Mapper
public interface RegionDao {

	RegionDO get(Integer id);
	
	List<RegionDO> list(Map<String, Object> map);
	List<RegionDO> getlistByParentId(String parentId);
	int count(Map<String, Object> map);
	
	int save(RegionDO region);
	
	int update(RegionDO region);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
