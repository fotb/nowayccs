package com.ccs.sms.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.sms.bo.ISmsSendBO;
import com.ccs.sms.dao.ISmsSendDAO;
import com.ccs.sms.vo.SmsSendVO;

@Service("smsSendBO")
public class SmsSendBOImpl implements ISmsSendBO {

	@Autowired
	private ISmsSendDAO smsSendDAO;
	
	@Override
	public void saveOrUpdate(SmsSendVO vo) {
		smsSendDAO.saveOrUpdate(vo);
	}

}
