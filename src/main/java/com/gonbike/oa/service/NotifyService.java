package com.gonbike.oa.service;

import com.gonbike.common.utils.PageUtils;
import com.gonbike.oa.domain.NotifyDO;

import java.util.List;
import java.util.Map;

/**
 * 通知通告
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-10-05 17:11:16
 */
public interface NotifyService {

	NotifyDO get(Long id);

	List<NotifyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(NotifyDO notify);

	int update(NotifyDO notify);

	int remove(Long id);

	int batchRemove(Long[] ids);

//	Map<String, Object> message(Long userId);

	PageUtils selfList(Map<String, Object> map);
}
