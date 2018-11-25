package com.ccs.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IRoleOperationDAO;
import com.ccs.vo.RoleOperationIdVO;
import com.ccs.vo.RoleOperationVO;

@Repository("roleOperationDAO")
public class RoleOperationDAOImpl extends DefaultDAOSupport implements
		IRoleOperationDAO {

	@Override
	public RoleOperationVO findById(RoleOperationIdVO idVO) {
		return getHibernateTemplate().get(RoleOperationVO.class, idVO);
	}

	@Override
	public void saveOrUpdate(RoleOperationVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(RoleOperationVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleOperationVO> findAll() {
		return (List<RoleOperationVO>) getHibernateTemplate().find("from RoleOperationVO");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleOperationVO> findByRoleId(String roleId) {
		return (List<RoleOperationVO>) getHibernateTemplate().find(
				"from RoleOperationVO vo where vo.id.roleId = ?", roleId);
	}

	@Override
	public void delete(List<RoleOperationVO> list) {
		getHibernateTemplate().deleteAll(list);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void saveAll(List<RoleOperationVO> list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			RoleOperationVO roleOperationVO = (RoleOperationVO) iterator.next();
			getHibernateTemplate().save(roleOperationVO);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleOperationVO> findByOperationId(String operationId) {
		return (List<RoleOperationVO>) getHibernateTemplate().find("from RoleOperationVO t where t.id.operationId = ?", operationId);
	}

}
