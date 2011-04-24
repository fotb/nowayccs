package com.ccs.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IAreaDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.AreaVO;

@Repository("areaDAO")
public class AreaDAOImpl extends DefaultDAOSupport implements IAreaDAO {

	@Override
	public void saveOrUpdate(AreaVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(AreaVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public AreaVO findById(String areaId) {
		return (AreaVO)getHibernateTemplate().get(AreaVO.class, areaId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaVO> findAll() {
		return getHibernateTemplate().find("from AreaVO vo");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaVO> findAll(final PageInfo pageInfo) {
		   return  getHibernateTemplate().executeFind( new  HibernateCallback<Object>() { 
			     public  Object doInHibernate(Session s)  throws  HibernateException, SQLException { 
			           Query query  =  s.createQuery("from AreaVO vo"); 
			           query.setFirstResult((pageInfo.getCurrentPage()-1)*pageInfo.getPAGE_COUNT()); 
			           query.setMaxResults(pageInfo.getPAGE_COUNT()); 
			           return query.list(); 
			           } 
			     });
	}

}
