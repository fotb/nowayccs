package com.ccs.sms.bo;

import java.util.List;

import com.ccs.sms.vo.SmsRecvVO;

public interface ISmsRecvBO {

	void saveOrUpdate(SmsRecvVO vo);

	List<SmsRecvVO> findByStatus(String status);

	List<SmsRecvVO> findByAll();

}
