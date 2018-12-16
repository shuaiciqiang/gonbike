package com.gonbike.shop.service;

import com.gonbike.shop.domain.AddressDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-12-16 10:51:02
 */
public interface AddressService {
	
	AddressDO get(Long addressid);
	
	List<AddressDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AddressDO address);
	
	int update(AddressDO address);
	
	int remove(Long addressid);
	
	int batchRemove(Long[] addressids);
}
