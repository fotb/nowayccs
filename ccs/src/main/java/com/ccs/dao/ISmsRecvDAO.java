package com.ccs.dao;

import java.util.List;

import com.ccs.vo.SmsRecvVO;

public interface ISmsRecvDAO {

	void saveOrUpdate(SmsRecvVO vo);
	
	void delete(SmsRecvVO vo);
	
	SmsRecvVO findById(String smsId);
	
	List<SmsRecvVO> findByStatus(String status);
	
	void saveOrUpdateAll(List<SmsRecvVO> list);
	
}
