package com.ccs.sms.dao.impl;

import org.springframework.stereotype.Repository;

import com.ccs.sms.dao.DefaultDAOSupport;
import com.ccs.sms.dao.ISmsSendDAO;
import com.ccs.sms.vo.SmsSendVO;

@Repository("smsSendDAO")
public class SmsSendDAOImpl extends DefaultDAOSupport implements ISmsSendDAO {

	@Override
	public void saveOrUpdate(SmsSendVO vo) {
		getHibernateTemplate().saveOrUpdate(vo);
	}

}
