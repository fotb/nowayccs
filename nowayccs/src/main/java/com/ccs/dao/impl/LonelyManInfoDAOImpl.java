package com.ccs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.ILonelyManInfoDAO;
import com.ccs.util.StringUtil;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<String> findLonelyManIds(String lonelyManName, String telphone) {
		List<String> list = new ArrayList<String>();
		list.add("%" + lonelyManName + "%");
		list.add(StringUtil.emptyToNull(lonelyManName));
		list.add("%" + telphone + "%");
		list.add(StringUtil.emptyToNull(telphone));
		
		return getHibernateTemplate().find("select manId from LonelyManInfoVO vo where (vo.manName like ? or ? is null) and (vo.telphone like ? or ? is null)", list.toArray());
	}

}
