package  com.gonbike.notice.dao;

import  com.gonbike.notice.domain.ContentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 文章内容
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-10-03 16:17:48
 */
@Mapper
public interface ContentDao {

	ContentDO get(Long cid);
	
	List<ContentDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ContentDO content);
	
	int update(ContentDO content);
	
	int remove(Long cid);
	
	int batchRemove(Long[] cids);
}
