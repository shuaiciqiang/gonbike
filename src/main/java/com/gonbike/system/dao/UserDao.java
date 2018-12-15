package com.gonbike.system.dao;

import com.gonbike.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import com.gonbike.system.domain.UserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author Shuaige
 * @email 77509028@qq.com
 * @date 2018-10-03 09:45:11
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);
	UserDO getUserForLogin(UserDO user);
	void insertUserToken(UserToken userToken);
	void updateUserToken(UserToken userToken);
	int countUserTokenByUserId(String userId);
	List<UserDO> list(Map<String, Object> map);
	List<UserDO> listByUser(UserDO user);
	int count(Map<String, Object> map);
	int countByUser(UserDO user);
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);
	
	Long[] listAllDept();
	UserToken getUserTokenByTokenId(String tokenId);
}
