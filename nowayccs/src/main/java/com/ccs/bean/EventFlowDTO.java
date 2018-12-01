package com.ccs.bean;

import java.io.Serializable;
import java.util.List;

public class EventFlowDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8183158455257186658L;
	
	private List<Object> attachments;
	
	private String eventBaseState;
	
	private String organizationId;
	
	private String organizationCode;
	
	private String organizationName;
	
	private String processDate;
	
	private String serialNumber;
	
	private String stateCode;
	
	private String suggestion;
	
	private String userId;
	
	private String userName;

	public List<Object> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}

	public String getEventBaseState() {
		return eventBaseState;
	}

	public void setEventBaseState(String eventBaseState) {
		this.eventBaseState = eventBaseState;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getProcessDate() {
		return processDate;
	}

	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
}
