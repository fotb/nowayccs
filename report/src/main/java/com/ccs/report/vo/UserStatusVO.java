package com.ccs.report.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_USERSTATUS")
public class UserStatusVO extends BaseEntity implements Serializable {

	public static final String STATUS_0 = "0"; // session expired

	public static final String STATUS_1 = "1"; // session online

	/**
	 * 
	 */
	private static final long serialVersionUID = 6681559797948212149L;

	@Column(name = "USERID")
	private String userId;

	@Column(name = "SESSIONID")
	private String sessionId;

	@Column(name = "LASTHEARTBEATDT")
	private Date lastHbDt;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "AGENTSTATUS")
	private String agentStatus;
	
	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getLastHbDt() {
		return lastHbDt;
	}

	public void setLastHbDt(Date lastHbDt) {
		this.lastHbDt = lastHbDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
