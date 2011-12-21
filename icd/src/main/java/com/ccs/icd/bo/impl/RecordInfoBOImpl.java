package com.ccs.icd.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.icd.bo.IRecordInfoBO;
import com.ccs.icd.dao.IRecordInfoDAO;
import com.ccs.icd.vo.RecordInfoVO;

@Service("recordInfoBO")
public class RecordInfoBOImpl implements IRecordInfoBO {

	@Autowired
	private IRecordInfoDAO recordInfoDAO;
	
	@Override
	public RecordInfoVO findById(String callId) {
		return recordInfoDAO.findById(callId);
	}

	@Override
	public List<RecordInfoVO> findRecordInfo(String callerNo, String agentId,
			String beginTime) {
		return recordInfoDAO.findRecordInfo(callerNo, agentId, beginTime);
	}

}
