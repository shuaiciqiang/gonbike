package com.gonbike.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.gonbike.system.dao.CategoriesDao;
import com.gonbike.system.domain.CategoriesDO;
import com.gonbike.system.service.CategoriesService;



@Service
public class CategoriesServiceImpl implements CategoriesService {
	@Autowired
	private CategoriesDao categoriesDao;
	
	@Override
	public CategoriesDO get(Long id){
		return categoriesDao.get(id);
	}
	
	@Override
	public List<CategoriesDO> list(Map<String, Object> map){
		return categoriesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return categoriesDao.count(map);
	}
	
	@Override
	public int save(CategoriesDO categories){
		return categoriesDao.save(categories);
	}
	
	@Override
	public int update(CategoriesDO categories){
		return categoriesDao.update(categories);
	}
	
	@Override
	public int remove(Long id){
		return categoriesDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return categoriesDao.batchRemove(ids);
	}
	
}
