package com.ccs.bo.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ccs.bo.ISmsRecvBO;
import com.ccs.dao.ISmsRecvDAO;
import com.ccs.icd.util.DateUtil;
import com.ccs.services.client.MessageManagerProxy;
import com.ccs.util.StringUtil;
import com.ccs.vo.SmsRecvVO;
@Service("smsRecvBO")
public class SmsRecvBOImpl implements ISmsRecvBO {
	@Autowired
	private ISmsRecvDAO smsRecvDAO;
	
	@Override
	public void saveOrUpdate(SmsRecvVO vo) {
		smsRecvDAO.saveOrUpdate(vo);
	}

	@Override
	public void delete(SmsRecvVO vo) {
		smsRecvDAO.delete(vo);
	}

	@Override
	public SmsRecvVO findById(String smsId) {
		return smsRecvDAO.findById(smsId);
	}

	@Override
	public List<SmsRecvVO> findByStatus(String status) {
		return smsRecvDAO.findByStatus(status);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String RecvSms() {
		String errorMsg = "";
		try {
			MessageManagerProxy proxy = new MessageManagerProxy();
			String smsStr = proxy.selectAllRecv();
			System.out.println(smsStr);
			if(!StringUtil.isNull(smsStr) && smsStr.contains("|;|")) {
					List<SmsRecvVO> list = new ArrayList<SmsRecvVO>();
					String ids = "";
					String[] smsAry = smsStr.split("\\|;\\|");
					for (int i = 0; i < smsAry.length; i++) {
						String sms = smsAry[i];
						String[] ary = sms.split("\\|\\|");
						SmsRecvVO vo = new SmsRecvVO();
						vo.setSmsId(ary[0]);
						ids = ids + ary[0] + ",";
						vo.setTelNum(ary[1]);
						vo.setContent(ary[2]);
						String recvDtStr = ary[3];
						Date recvDt = DateUtil.parse(recvDtStr, "yyyy-MM-dd HH:mm:ss");
						vo.setRecvDt(recvDt);
						vo.setStatus(SmsRecvVO.STATUS_NEW);
						list.add(vo);
					}
					
					smsRecvDAO.saveOrUpdateAll(list);
					
					proxy.updateReadedRecv(ids);
			}
		}catch(RemoteException re) {
			re.printStackTrace();
			errorMsg = re.getMessage();
		}
		
		return errorMsg;
		
	}

}
