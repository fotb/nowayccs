package com.ccs.bo;

import java.util.List;

import com.ccs.vo.SmsRecvVO;

public interface ISmsRecvBO {
	void saveOrUpdate(SmsRecvVO vo);

	void delete(SmsRecvVO vo);

	SmsRecvVO findById(String smsId);

	List<SmsRecvVO> findByStatus(String status);
	
	String RecvSms();

}
