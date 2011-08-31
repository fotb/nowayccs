package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IUserRoleDAO;
import com.ccs.vo.UserRoleIdVO;
import com.ccs.vo.UserRoleVO;

@Repository("userRoleDAO")
public class UserRoleDAOImpl extends DefaultDAOSupport implements IUserRoleDAO {

	@Override
	public UserRoleVO findById(UserRoleIdVO idVO) {
		return getHibernateTemplate().get(UserRoleVO.class, idVO);
	}

	@Override
	public void saveOrUpdate(UserRoleVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(UserRoleVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleVO> findAll() {
		return getHibernateTemplate().find("from UserRoleVO");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleVO> findByUserId(String userId) {
		return getHibernateTemplate().find("from UserRoleVO vo where vo.userId = ?", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleVO> findByRoleId(String roleId) {
		return getHibernateTemplate().find("from UserRoleVO vo where vo.roleId = ?", roleId);
	}

	@Override
	public void delete(final List<UserRoleVO> list) {
		getHibernateTemplate().deleteAll(list);
	}

}
