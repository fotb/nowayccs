package com.ccs.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
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
		return getHibernateTemplate().find("from UserRoleVO vo where vo.id.userId = ?", userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRoleVO> findByRoleId(String roleId) {
		return getHibernateTemplate().find("from UserRoleVO vo where vo.id.roleId = ?", roleId);
	}

	@Override
	public void delete(final List<UserRoleVO> list) {
		getHibernateTemplate().deleteAll(list);
	}

	@Override
	public void saveOrUpdate(List<UserRoleVO> list) {
		getHibernateTemplate().saveOrUpdateAll(list);
	}

	@Override
	public void merge(UserRoleVO vo) {
		getHibernateTemplate().merge(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findUserIdsByRoleIds(List<String> roleIdList) {
		StringBuffer hqlBuffer = new StringBuffer(1000);
		hqlBuffer.append("from UserRoleVO t where t.id.roleId in (");
		
		for (int i = 0; i < roleIdList.size(); i++) {
			if(i == roleIdList.size() - 1) {
				hqlBuffer.append("? )");
			} else {
				hqlBuffer.append("?, ");
			}
		}
		List<UserRoleVO> list = getHibernateTemplate().find(hqlBuffer.toString(), roleIdList.toArray());
		List<String> userIdList = new ArrayList<String>();
		for (Iterator<UserRoleVO> iter = list.iterator(); iter.hasNext();) {
			UserRoleVO userRoleVO = iter.next();
			userIdList.add(userRoleVO.getId().getUserId());
		}
		return userIdList;
	}

}
