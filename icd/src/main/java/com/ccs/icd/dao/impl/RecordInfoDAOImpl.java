package com.ccs.icd.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ccs.icd.dao.DefaultDAOSupport;
import com.ccs.icd.dao.IRecordInfoDAO;
import com.ccs.icd.vo.RecordInfoVO;

@Repository("recordInfoDAO")
public class RecordInfoDAOImpl extends DefaultDAOSupport implements
		IRecordInfoDAO {

	@Override
	public RecordInfoVO findById(String callId) {
		return getHibernateTemplate().get(RecordInfoVO.class, callId);
	}

	/* (non-Javadoc)
	 * @see com.ccs.icd.dao.IRecordInfoDAO#findRecordInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RecordInfoVO> findRecordInfo(String callerNo, String agentId,
			String beginTime) {
		StringBuffer buffer = new StringBuffer(300);
		buffer.append("from recordInfoVO t where ");
		buffer.append("t.callerNo = ? ");
		buffer.append("and t.agentId = ? ");
		buffer.append("and to_char(t.beginTime, 'yyyymmddhh24mi') = ? ");
		List<String> objs = new ArrayList<String>();
		objs.add(callerNo);
		objs.add(agentId);
		objs.add(beginTime);
		return getHibernateTemplate().find(buffer.toString(), objs.toArray());
	}

}
