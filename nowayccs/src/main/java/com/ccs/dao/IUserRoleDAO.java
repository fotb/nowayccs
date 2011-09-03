package com.ccs.dao;

import java.util.List;

import com.ccs.vo.UserRoleIdVO;
import com.ccs.vo.UserRoleVO;

public interface IUserRoleDAO {
	UserRoleVO findById(final UserRoleIdVO idVO);
	
	void saveOrUpdate(final UserRoleVO vo);
	
	void saveOrUpdate(final List<UserRoleVO> list);
	
	void merge(final UserRoleVO vo);
	
	void delete(final UserRoleVO vo);
	
	List<UserRoleVO> findAll();
	
	List<UserRoleVO> findByUserId(final String userId);
	
	List<UserRoleVO> findByRoleId(final String roleId);
	
	void delete(final List<UserRoleVO> list);
}
