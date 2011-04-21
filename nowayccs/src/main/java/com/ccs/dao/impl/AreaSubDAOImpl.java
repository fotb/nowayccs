package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IAreaSubDAO;
import com.ccs.vo.AreaSubVO;

@Repository("areaDAO")
public class AreaSubDAOImpl extends DefaultDAOSupport implements IAreaSubDAO {

	@Override
	public void saveOrUpdate(AreaSubVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(AreaSubVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public AreaSubVO findById(String areaSubId) {
		return (AreaSubVO)getHibernateTemplate().get(AreaSubVO.class, areaSubId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaSubVO> findAll() {
		return getHibernateTemplate().find("from AreaSubVO vo");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AreaSubVO> findByAreaId(String areaId) {
		return getHibernateTemplate().find("from AreaSubVO vo where vo.areaId = ?", areaId);
	}

	
}
