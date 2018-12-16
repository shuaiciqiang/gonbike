package com.gonbike.shop.dao;

import com.gonbike.shop.domain.AddressDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 10:51:02
 */
@Mapper
public interface AddressDao {

	AddressDO get(Long addressid);
	
	List<AddressDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AddressDO address);
	
	int update(AddressDO address);
	
	int remove(Long addressId);
	
	int batchRemove(Long[] addressids);
}
