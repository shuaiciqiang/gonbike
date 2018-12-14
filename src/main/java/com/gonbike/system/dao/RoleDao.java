package com.gonbike.system.dao;

import com.gonbike.system.domain.RoleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-10-02 20:24:47
 */
@Mapper
public interface RoleDao {

	RoleDO get(Long roleId);
	
	List<RoleDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(RoleDO role);
	
	int update(RoleDO role);
	
	int remove(Long roleId);
	
	int batchRemove(Long[] roleIds);
}