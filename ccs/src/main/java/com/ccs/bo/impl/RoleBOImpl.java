package com.ccs.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.IRoleBO;
import com.ccs.dao.IOperationDAO;
import com.ccs.dao.IRoleDAO;
import com.ccs.dao.IRoleOperationDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.OperationVO;
import com.ccs.vo.RoleOperationIdVO;
import com.ccs.vo.RoleOperationVO;
import com.ccs.vo.RoleVO;

@Service("roleBO")
public class RoleBOImpl implements IRoleBO {
	
	@Autowired
	private IRoleDAO roleDAO;
	
	@Autowired
	private IOperationDAO operationDAO;
	
	@Autowired
	private IRoleOperationDAO roleOperationDAO;

	@Override
	public List<RoleVO> findAllRole(PageInfo pageInfo) {
		pageInfo.setTotalRecords(roleDAO.getTotalCount());
		return roleDAO.findAll(pageInfo);
	}

	@Override
	public RoleVO findRoleById(String roleId) {
		return roleDAO.findById(roleId);
	}

	@Override
	@Transactional
	public void addRole(RoleVO roleVO, String[] operationIdAry) {
		roleDAO.saveOrUpdate(roleVO);
		
		if(null != operationIdAry) {
			List<RoleOperationVO> oldList = roleOperationDAO.findByRoleId(roleVO.getRoleId());
			if(!oldList.isEmpty()) {
				roleOperationDAO.delete(oldList);
			}
			List<RoleOperationVO> list = new ArrayList<RoleOperationVO>();
			for (int i = 0; i < operationIdAry.length; i++) {
				RoleOperationVO vo = new RoleOperationVO();
				RoleOperationIdVO idVO = new RoleOperationIdVO();
				idVO.setRoleId(roleVO.getRoleId());
				idVO.setOperationId(operationIdAry[i]);
				vo.setId(idVO);
				list.add(vo);
			}
			roleOperationDAO.saveAll(list);
		}
	}

	@Override
	public List<OperationVO> findAllOperation() {
		return operationDAO.findAll();
	}

	@Override
	public List<RoleOperationVO> findRoleOperationByRoleId(String roleId) {
		return roleOperationDAO.findByRoleId(roleId);
	}

	@Override
	@Transactional
	public void deleteRole(RoleVO vo) {
		List<RoleOperationVO> list = roleOperationDAO.findByRoleId(vo.getRoleId());
		roleOperationDAO.delete(list);
		roleDAO.delete(vo);
		//TODO
		//if uses asign to this role, delete use's role.
	}

	@Override
	public List<RoleVO> findAllRole() {
		return roleDAO.findAll();
	}
	
}
