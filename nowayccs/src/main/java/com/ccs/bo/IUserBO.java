package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.UserRoleVO;
import com.ccs.vo.UserVO;

public interface IUserBO {
	void saveOrUpdate(UserVO vo);

	void delete(UserVO vo);

	UserVO findById(String userId);

	List<UserVO> findAll(PageInfo pageInfo);

	UserVO login(String loginName, String pwd);
	
	void addUser(UserVO vo, String[] roleIds);
	
	List<UserVO> findByLoginName(String loginName);
	
	List<UserVO> findAllOnJob();
	
	List<UserRoleVO> findUserRoleByUserId(String userId);
	
	void editUser(UserVO vo, String[] roleIds);
	
	List<UserVO> findUserByOpertaionId(String operationId);
	
}
