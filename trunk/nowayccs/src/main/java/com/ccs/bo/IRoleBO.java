package com.ccs.bo;

import java.util.List;

import com.ccs.util.PageInfo;
import com.ccs.vo.OperationVO;
import com.ccs.vo.RoleOperationVO;
import com.ccs.vo.RoleVO;

public interface IRoleBO {

	List<RoleVO> findAllRole();

	List<RoleVO> findAllRole(PageInfo pageInfo);

	RoleVO findRoleById(String roleId);

	void addRole(RoleVO vo, String[] operationIdAry);

	List<OperationVO> findAllOperation();

	List<RoleOperationVO> findRoleOperationByRoleId(String roleId);

	void deleteRole(RoleVO vo);

}
