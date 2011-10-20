package com.ccs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.IBlackListDAO;
import com.ccs.vo.BlackListVO;

@Repository("blackListDAO")
public class BlackListDAOImpl extends DefaultDAOSupport implements
		IBlackListDAO {

	@Override
	public void saveOrUpdate(BlackListVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(BlackListVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public BlackListVO findById(String phoneId) {
		return getHibernateTemplate().get(BlackListVO.class, phoneId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public BlackListVO findByPhoneNum(String phoneNum) {
		List<BlackListVO> list = getHibernateTemplate().find("from BlackListVO t where t.phoneNum = ?", phoneNum);
		if(!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlackListVO> findByParams(String phoneNum, String levels) {
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append("from BlackListVO t where ");
		buffer.append("(t.phoneNum like ? or ? is null) ");
		buffer.append("and (t.levels = ? or ? is null)");
		List<Object> values = new ArrayList<Object>();
		values.add("%" + phoneNum + "%");
		values.add(phoneNum);
		values.add(levels);
		values.add(levels);
		return getHibernateTemplate().find(buffer.toString(), values.toArray());
	}

}
