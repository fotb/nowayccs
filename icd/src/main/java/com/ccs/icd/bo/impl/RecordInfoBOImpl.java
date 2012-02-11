package com.ccs.icd.bo.impl;

import java.util.ArrayList;
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
		RecordInfoVO infoVO = null;
		for (int i = 1; i <= 12; i++) {
			infoVO = recordInfoDAO.findById(callId,
					"RecordInfo" + String.valueOf(i) + "VO");
			if (null != infoVO) {
				break;
			}
		}
		return infoVO;
	}

	@Override
	public List<RecordInfoVO> findRecordInfo(String callerNo, String agentId,
			String beginTime, int year) {
		List<RecordInfoVO> list = new ArrayList<RecordInfoVO>();
		for (int i = 1; i <= 12; i++) {
			list = recordInfoDAO.findRecordInfo(callerNo, agentId, beginTime,
					"RecordInfo" + String.valueOf(i) + "VO");
			if (!list.isEmpty()) {
				break;
			}
		}
		return list;
	}

}
