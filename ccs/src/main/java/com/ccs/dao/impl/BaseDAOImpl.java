package com.ccs.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.type.Type;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.dao.IBaseDAO;
import com.ccs.util.GenericUtil;
import com.ccs.vo.BaseEntity;

@Transactional
public class BaseDAOImpl<E extends BaseEntity> extends HibernateDaoSupport implements IBaseDAO<E> {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	protected Class<E> clazz;

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(final SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
	
	
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		try {
			clazz = GenericUtil.getActualClass(this.getClass(), 0);
		} catch (Exception e) {
			log.error("base dao can not get  clazz!", e);
		}
	}

	@Override
	public void saveOrUpdate(E entity) throws Exception {
		this.getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAll(String orderBy) {
		if (orderBy == null) {
			orderBy = " createTime ";
		}
		String hql = "from " + this.clazz.getName() + "  where deleteFlag=0  order by " + orderBy;
		return this.getSessionFactory().getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAll() {
		String hql = "from " + this.clazz.getName() + "  where deleteFlag=0  order by createTime" ;
		return this.getSessionFactory().getCurrentSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> queryForObject(String hql, Object[] parameters) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		if (parameters != null) {
			for (int i = 0, len = parameters.length; i < len; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		return (List<E>) query.list();
	}

	@Override
	public E get(String pid) {
		return (E) this.getSessionFactory().getCurrentSession().get(this.clazz, pid);
	}

	@Override
	public int getRowCountByDetachedCriteria(DetachedCriteria condition) {
		Criteria criteria = condition.getExecutableCriteria(this.getSessionFactory().getCurrentSession());
		Long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return totalCount == null ? 0 : totalCount.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findByDetachedCriteria(DetachedCriteria condition, int page, int rows) {
		Criteria criteria = condition.getExecutableCriteria(this.getSessionFactory().getCurrentSession());
		criteria.setFirstResult((page - 1) * rows).setMaxResults(rows);
		criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public void save(E entity) throws Exception {
		this.getSessionFactory().getCurrentSession().save(entity);
	}

	@Override
	public void update(E entity) throws Exception {
		this.getSessionFactory().getCurrentSession().update(entity);
	}

	@Override
	public void deleteLogicByIds(String[] pidArray) throws Exception {
		String hql ="update  "+this.clazz.getName()+" t set   t.deleteFlag = ?  where t.pid in ( :pids ) " ;
		
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		
		query.setInteger(0, BaseEntity.DELETE_FLAG_YES);
		
		List<String> _tempList = new ArrayList<String>();
		for(int i = 0; i < pidArray.length ; i++){
			_tempList.add(pidArray[i]);
		}
		
		query.setParameterList("pids", _tempList);
		
		query.executeUpdate();
	}

	@Override
	public void evict(Object entity) {
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryFromObject(String hql, Object[] parameters) {
		Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
		if (parameters != null) {
			for (int i = 0, len = parameters.length; i < len; i++) {
				query.setParameter(i, parameters[i]);
			}
		}
		return (List<Object[]>) query.list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<E> getAllWithDeleted() {
		String hql = "from " + this.clazz.getName() + "  order by createTime" ;
		return this.getSessionFactory().getCurrentSession().createQuery(hql).list();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> createSQLQuery(String sql, Object[] args, Type[] types, int page, int rows, boolean isPagenation) throws Exception {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		
		query.setParameters(args, types);
		if(isPagenation) {
			query.setFirstResult((page - 1) * rows).setMaxResults(rows);
		}
		
		return query.list();
	}
	
	@Override
	public List<?> createSQLQuery(String sql, Object[] args, Type[] types) throws Exception {
		Query query = this.getSessionFactory().getCurrentSession().createSQLQuery(sql);
		
		query.setParameters(args, types);
		
		return query.list();
	}


	@Override
	public void delete(E entity) throws Exception {
			this.getSessionFactory().getCurrentSession().delete(entity);
	}

}

