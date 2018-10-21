package com.ccs.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IDictDAO;
import com.ccs.util.PageInfo;
import com.ccs.vo.DictVO;

@Repository("dictDAO")
public class DictDAOImpl extends DefaultDAOSupport implements IDictDAO {

	@Override
	public DictVO findById(String dictId) {
		return (DictVO) getHibernateTemplate().get(DictVO.class, dictId);
	}

	@Override
	public void saveOrUpdate(DictVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(DictVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DictVO> findByType(String dictType) {
		return (List<DictVO>) getHibernateTemplate().find(
				"from DictVO vo where vo.dictType = ? order by vo.sortIndex",
				dictType);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DictVO> findByType(final String dictType, final PageInfo pageInfo) {
		   return  (List<DictVO>) getHibernateTemplate().execute( new  HibernateCallback<Object>() { 
			     public  Object doInHibernate(Session s)  throws  HibernateException { 
			           Query query  =  s.createQuery("from DictVO vo where vo.dictType =? order by vo.sortIndex"); 
			           query.setString(0, dictType);
			           query.setFirstResult((pageInfo.getCurrentPage()-1)*pageInfo.getPAGE_COUNT()); 
			           query.setMaxResults(pageInfo.getPAGE_COUNT()); 
			           return query.list(); 
			           } 
			     });
	}

	@Override
	public int getTotalCountByDictType(String dictType) {
		  String hql = "select count(*) from DictVO vo where vo.dictType = ?";
		  Long count = (Long)getHibernateTemplate().find(hql, dictType).listIterator().next();
		  return count.intValue();
	}

}
