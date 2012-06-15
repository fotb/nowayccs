package com.ccs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.dao.DefaultDAOSupport;
import com.ccs.dao.ISmsSendDAO;
import com.ccs.vo.SmsRecvVO;
import com.ccs.vo.SmsSendVO;
@Repository("smsSendDAO")
public class SmsSendDAOImpl extends DefaultDAOSupport implements ISmsSendDAO {

	@Override
	public void saveOrUpdate(SmsSendVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@Override
	public void delete(SmsSendVO vo) {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public SmsSendVO findById(String smsId) {
		return getHibernateTemplate().get(SmsSendVO.class, smsId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsRecvVO> findByStatus(String status) {
		return getHibernateTemplate().find("from SmsSendVO t where t.status = ?", status);
	}

	@Override
	public void save(SmsSendVO vo) {
		getHibernateTemplate().save(vo);
	}

}
