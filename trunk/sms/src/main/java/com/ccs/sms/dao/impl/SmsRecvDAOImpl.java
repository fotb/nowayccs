package com.ccs.sms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.sms.dao.DefaultDAOSupport;
import com.ccs.sms.dao.ISmsRecvDAO;
import com.ccs.sms.vo.SmsRecvVO;

@Repository("smsRecvDAO")
public class SmsRecvDAOImpl extends DefaultDAOSupport implements ISmsRecvDAO {

	@Override
	public void saveOrUpdate(SmsRecvVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsRecvVO> findByStatus(String status) {
		return getHibernateTemplate().find("from SmsRecvVO t where t.status = ? order by t.inseDate desc", status);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsRecvVO> findByAll() {
		return getHibernateTemplate().find("from SmsRecvVO t order by t.inseDate desc");
	}
	
}
