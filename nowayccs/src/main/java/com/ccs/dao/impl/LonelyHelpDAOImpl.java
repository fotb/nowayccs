package com.ccs.dao.impl;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.ILonelyHelpDAO;
import com.ccs.vo.LonelyHelpVO;

@Repository("lonelyHelpDAO")
public class LonelyHelpDAOImpl extends DefaultDAOSupport implements
		ILonelyHelpDAO {

	@Override
	public void saveOrUpdate(LonelyHelpVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(LonelyHelpVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public LonelyHelpVO findById(String helpId) {
		return getHibernateTemplate().get(LonelyHelpVO.class, helpId);
	}

}
