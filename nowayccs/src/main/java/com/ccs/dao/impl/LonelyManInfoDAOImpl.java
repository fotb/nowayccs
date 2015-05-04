package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.ILonelyManInfoDAO;
import com.ccs.vo.LonelyManInfoVO;

@Repository("lonelyManInfoDAO")
public class LonelyManInfoDAOImpl extends DefaultDAOSupport implements
		ILonelyManInfoDAO {

	@Override
	public void saveOrUpdate(LonelyManInfoVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(LonelyManInfoVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public LonelyManInfoVO findById(String manId) {
		return getHibernateTemplate().get(LonelyManInfoVO.class, manId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LonelyManInfoVO> findByTelphone(String telphone) {
		return getHibernateTemplate().find("from LonelyManInfoVO vo where vo.telphone = ?", telphone);
	}

}
