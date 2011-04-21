package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IAreaDAO;
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

}
