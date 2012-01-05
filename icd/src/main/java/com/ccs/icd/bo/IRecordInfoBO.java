package com.ccs.icd.bo;

import java.util.List;

import com.ccs.icd.vo.RecordInfoVO;

public interface IRecordInfoBO {
	RecordInfoVO findById(String callId, int year);

	/**
	 * @param callerNo
	 * @param agentId
	 * @param beginTime: yyyyMMddHHmm
	 * @return
	 */
	List<RecordInfoVO> findRecordInfo(String callerNo, String agentId,
			String beginTime, int year);
}
