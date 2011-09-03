package com.ccs.dao;

import java.util.List;

import com.ccs.vo.RoleOperationIdVO;
import com.ccs.vo.RoleOperationVO;

public interface IRoleOperationDAO {
	RoleOperationVO findById(final RoleOperationIdVO idVO);

	void saveOrUpdate(final RoleOperationVO vo);

	void delete(final RoleOperationVO vo);

	List<RoleOperationVO> findAll();

	List<RoleOperationVO> findByRoleId(final String roleId);

	void delete(final List<RoleOperationVO> list);
	
	void saveAll(List<RoleOperationVO> list);
}
