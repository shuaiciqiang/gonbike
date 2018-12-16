package com.gonbike.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gonbike.shop.dao.AddressDao;
import com.gonbike.shop.domain.AddressDO;
import com.gonbike.shop.service.AddressService;



@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressDao addressDao;
	
	@Override
	public AddressDO get(Long addressid){
		return addressDao.get(addressid);
	}
	
	@Override
	public List<AddressDO> list(Map<String, Object> map){
		return addressDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return addressDao.count(map);
	}
	
	@Override
	public int save(AddressDO address){
		return addressDao.save(address);
	}
	
	@Override
	public int update(AddressDO address){
		return addressDao.update(address);
	}
	
	@Override
	public int remove(Long addressid){
		return addressDao.remove(addressid);
	}
	
	@Override
	public int batchRemove(Long[] addressids){
		return addressDao.batchRemove(addressids);
	}
	
}
