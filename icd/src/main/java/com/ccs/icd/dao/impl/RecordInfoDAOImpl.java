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

	private static final String DEFAULT_SELECT = "select new com.ccs.icd.vo.RecordInfoVO(t.callId, t.callerNo, t.calleeNo, t.agentId, t.beginTime, t.endTime, t.fileName) from ";
	
	@SuppressWarnings("unchecked")
	@Override
	public RecordInfoVO findById(String callId, String tableName) {
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append(DEFAULT_SELECT);
		buffer.append(tableName + " t ");
		buffer.append("where t.callId = ?");
		List<RecordInfoVO> list = getHibernateTemplate().find(buffer.toString(), callId);
		return list.isEmpty() ? null : list.get(0);
	}
	
	/* (non-Javadoc)
	 * @see com.ccs.icd.dao.IRecordInfoDAO#findRecordInfo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RecordInfoVO> findRecordInfo(String callerNo, String agentId,
			String beginTime, String tableName) {
		StringBuffer buffer = new StringBuffer(1000);
		buffer.append(DEFAULT_SELECT);
		buffer.append(tableName + " t ");
		buffer.append("where ");
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
