package com.ccs.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IRoleDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.RoleVO;

@Repository("roleDAO")
public class RoleDAOImpl extends DefaultDAOSupport implements IRoleDAO {

	@Override
	public RoleVO findById(String roleId) {
		return getHibernateTemplate().get(RoleVO.class, roleId);
	}

	@Override
	public void saveOrUpdate(RoleVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(RoleVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleVO> findAll() {
		return (List<RoleVO>) getHibernateTemplate().find("from RoleVO");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleVO> findAll(final PageInfo pageInfo) {
		return (List<RoleVO>) getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					public Object doInHibernate(Session s)
							throws HibernateException {
						Query query = s.createQuery("from RoleVO");
						query.setFirstResult((pageInfo.getCurrentPage() - 1) * pageInfo.getPAGE_COUNT());
						query.setMaxResults(pageInfo.getPAGE_COUNT());
						return query.list();
					}
				});
	}

	@Override
	public int getTotalCount() {
		  String hql = "select count(vo.roleId) from RoleVO vo ";
		  Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
		  return count.intValue();
	}

}
