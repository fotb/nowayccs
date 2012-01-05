package com.ccs.icd.dao;

import java.util.List;

import com.ccs.icd.vo.RecordInfoVO;

public interface IRecordInfoDAO {
	RecordInfoVO findById(String callId, String tableName);
	
	/**
	 * @param callerNo
	 * @param agentId
	 * @param beginTime: yyyyMMddHHmm
	 * @return
	 */
	List<RecordInfoVO> findRecordInfo(String callerNo, String agentId,
			String beginTime, String tableName);
	
}