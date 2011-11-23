package com.ccs.sms.dao;

import java.util.List;

import com.ccs.sms.vo.SmsRecvVO;

public interface ISmsRecvDAO {

	void saveOrUpdate(SmsRecvVO vo);
	
	List<SmsRecvVO> findByStatus(String status);
	
	List<SmsRecvVO> findByAll();
}
