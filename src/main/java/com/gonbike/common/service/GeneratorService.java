/**
 * 
 */
package com.gonbike.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * @author 77509028@qq.com
 * @Time 2018年12月12日
 * @description
 * 
 */
@Service
public interface GeneratorService {
	List<Map<String, Object>> list();

	byte[] generatorCode(String[] tableNames);
}
