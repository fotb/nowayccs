package com.ccs.icd.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRECORDINFO9")
public class RecordInfo9VO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	/*
	CALLID 	VARCHAR2(25)	Y			
	CALLERNO	VARCHAR2(25)	Y			
	CALLEENO	VARCHAR2(25)	Y			
	AGENTID	NUMBER(5)	Y			
	CALLCENTERID	NUMBER(5)	Y			
	VIRTUALCALLCENTERID	NUMBER(5)	Y			
	BEGINTIME	DATE	Y			
	ENDTIME	DATE	Y			
	FILENAME	VARCHAR2(129)	Y			
	CALLTYPE	NUMBER(3)	Y			
	SERVICENO	NUMBER(5)	Y			
	VISITTIME	DATE	Y			
	VISITFLAG	NUMBER(10)	Y			
	MEDIATYPE	NUMBER(5)	Y			
	MODNO	NUMBER(3)	Y			
	TRKNO	NUMBER(5)	Y			
	SERVICEID	NUMBER(10)	Y			
	SERVICEINFO	VARCHAR2(80)	Y			
	CALLINFO	VARCHAR2(80)	Y			
	STOPREASON	NUMBER(5)	Y			
	LOCATIONID	NUMBER(8)	Y
	*/
	public RecordInfo9VO(String callId, String callerNo, String calleeNo,
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
	
	@Id
	@Column(name = "CALLID")
	private String callId;
	
	@Column(name = "CALLERNO")
	private String callerNo;
	
	@Column(name = "CALLEENO")
	private String calleeNo;
	
	@Column(name = "AGENTID")
	private String agentId;
	
	@Column(name = "BEGINTIME")
	private Date beginTime;
	
	@Column(name = "ENDTIME")
	private Date endTime;
	
	@Column(name = "FILENAME")
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
