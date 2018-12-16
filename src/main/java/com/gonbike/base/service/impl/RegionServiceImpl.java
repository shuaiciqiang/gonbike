package com.gonbike.base.service.impl;

import com.gonbike.base.dao.RegionDao;
import com.gonbike.base.domain.RegionDO;
import com.gonbike.base.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class RegionServiceImpl implements RegionService {
	@Autowired
	private RegionDao regionDao;
	
	@Override
	public RegionDO get(Integer id){
		return regionDao.get(id);
	}
	
	@Override
	public List<RegionDO> list(Map<String, Object> map){
		return regionDao.list(map);
	}


	@Override
	public List<RegionDO> getlistByParentId(String parentId){
		return regionDao.getlistByParentId(parentId);
	}
	@Override
	public int count(Map<String, Object> map){
		return regionDao.count(map);
	}
	
	@Override
	public int save(RegionDO region){
		return regionDao.save(region);
	}
	
	@Override
	public int update(RegionDO region){
		return regionDao.update(region);
	}
	
	@Override
	public int remove(Integer id){
		return regionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return regionDao.batchRemove(ids);
	}
	
}
