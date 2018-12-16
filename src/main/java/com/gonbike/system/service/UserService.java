package com.gonbike.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gonbike.system.domain.UserToken;
import com.gonbike.system.vo.UserVO;
import org.springframework.stereotype.Service;

import com.gonbike.common.domain.Tree;
import com.gonbike.system.domain.DeptDO;
import com.gonbike.system.domain.UserDO;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
	UserDO get(Long id);
	UserDO getUserForLogin(UserDO user);
	UserDO getUserByUserId(UserDO user);
	void saveUserToken(UserToken userToken);
	List<UserDO> list(Map<String, Object> map);
	List<UserDO> listByUser(UserDO user);
	int count(Map<String, Object> map);
	int countByUser(UserDO user);
	int save(UserDO user);

	int update(UserDO user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO, UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<DeptDO> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);
	UserToken getUserTokenByTokenId(String tokenId);
	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;
}
