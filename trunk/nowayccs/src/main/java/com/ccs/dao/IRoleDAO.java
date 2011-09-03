package com.ccs.dao;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.RoleVO;

public interface IRoleDAO {

	RoleVO findById(final String roleId);
	
	void saveOrUpdate(final RoleVO vo);
	
	void delete(final RoleVO	vo);
	
	List<RoleVO> findAll();
	
	List<RoleVO> findAll(final PageInfo pageInfo);
	
	int getTotalCount();
}
