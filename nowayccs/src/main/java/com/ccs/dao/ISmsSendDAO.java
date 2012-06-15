package com.ccs.dao;

import java.util.List;

import com.ccs.vo.SmsRecvVO;
import com.ccs.vo.SmsSendVO;

public interface ISmsSendDAO {

	void saveOrUpdate(SmsSendVO vo);
	
	void save(SmsSendVO vo);
	
	void delete(SmsSendVO vo);
	
	SmsSendVO findById(String smsId);
	
	List<SmsRecvVO> findByStatus(String status);
	
}
