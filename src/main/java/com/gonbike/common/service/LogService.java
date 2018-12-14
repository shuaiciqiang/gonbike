package com.gonbike.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gonbike.common.domain.LogDO;
import com.gonbike.common.domain.PageDO;
import com.gonbike.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
