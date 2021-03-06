package com.ccs.icd.vo;

import java.io.Serializable;
import java.util.Date;

public class RecordInfoVO implements Serializable {

	private static final long serialVersionUID = 1L;

	public RecordInfoVO(String callId, String callerNo, String calleeNo,
			String agentId, Date beginTime, Date endTime, String fileName) {
		super();
		this.callId = callId;
		this.callerNo = callerNo;
		this.calleeNo = calleeNo;
		this.agentId = agentId;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.fileName = fileName;
	}
	
	private String callId;
	
	private String callerNo;
	
	private String calleeNo;
	
	private String agentId;
	
	private Date beginTime;
	
	private Date endTime;
	
	private String fileName;

	public String getCallId() {
		return callId;
	}

	public void setCallId(String callId) {
		this.callId = callId;
	}

	public String getCallerNo() {
		return callerNo;
	}

	public void setCallerNo(String callerNo) {
		this.callerNo = callerNo;
	}

	public String getCalleeNo() {
		return calleeNo;
	}

	public void setCalleeNo(String calleeNo) {
		this.calleeNo = calleeNo;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
