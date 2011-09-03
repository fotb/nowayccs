package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.UserVO;

public interface IUserDAO {
	void saveOrUpdate(UserVO vo);
	
	void update(UserVO vo);
	
	void delete(UserVO vo);
	
	UserVO findById(String userId);
	
	List<UserVO> findAll(PageInfo pageInfo);
	
	UserVO findByLoginNameAndPwd(String loginName, String pwd);
	
	int getTotalCount();
	
	List<UserVO> findByLoginName(String loginName);
	
	List<UserVO> findAllOnJob();
	
}
