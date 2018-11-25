package com.ccs.bo.impl;

import java.rmi.RemoteException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.ISmsSendBO;
import com.ccs.dao.ISmsSendDAO;
import com.ccs.services.client.MessageManagerProxy;
import com.ccs.vo.SmsSendVO;
@Service("smsSendBO")
public class SmsSendBOImpl implements ISmsSendBO {

	@Autowired
	private ISmsSendDAO smsSendDAO;
	
	@Override
	@Transactional
	public String send(SmsSendVO vo) {
		String errorMsg = "";
		MessageManagerProxy proxy = new MessageManagerProxy();
		try {
			String smsStr = proxy.smsSend(vo.getTelNum(), vo.getContent());
			System.out.println(smsStr);
		} catch (RemoteException e) {
			e.printStackTrace();
			errorMsg = "Y";
		}
		smsSendDAO.save(vo);
		
		return errorMsg;
	}

}
