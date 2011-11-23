package com.ccs.sms.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.sms.bo.ISmsRecvBO;
import com.ccs.sms.dao.ISmsRecvDAO;
import com.ccs.sms.vo.SmsRecvVO;

@Service("smsRecvBO")
public class SmsRecvBOImpl implements ISmsRecvBO {

	@Autowired
	private ISmsRecvDAO smsRecvDAO;
	
	@Override
	public void saveOrUpdate(SmsRecvVO vo) {
		smsRecvDAO.saveOrUpdate(vo);
	}

	@Override
	public List<SmsRecvVO> findByStatus(String status) {
		return smsRecvDAO.findByStatus(status);
	}

	@Override
	public List<SmsRecvVO> findByAll() {
		return smsRecvDAO.findByAll();
	}
}
