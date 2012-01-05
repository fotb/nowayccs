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
	public RecordInfoVO findById(String callId, int year) {
		return recordInfoDAO.findById(callId, getTableName(year));
	}

	@Override
	public List<RecordInfoVO> findRecordInfo(String callerNo, String agentId,
			String beginTime, int year) {
		return recordInfoDAO.findRecordInfo(callerNo, agentId, beginTime, getTableName(year));
	}
	
	private String getTableName(int year) {
		String tableName = "";
		switch (year) {
		case 2011:
			tableName = "RecordInfo12VO";
			break;
		case 2012:
			tableName = "RecordInfo1VO";
			break;
		default:
			break;
		}
		return tableName;
	}

}
