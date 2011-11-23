package com.ccs.sms.util;

import java.util.List;

import com.ccs.sms.bo.ISmsRecvBO;
import com.ccs.sms.vo.SmsRecvVO;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ISmsRecvBO smsRecvBO = (ISmsRecvBO) SpringUtil.getBean("smsRecvBO");
		
		List<SmsRecvVO> list = smsRecvBO.findByAll();
		
		for (SmsRecvVO vo : list) {
			System.out.println(vo.getOrgTele());
			System.out.println(vo.getSmsContent());
		}
	}

}
