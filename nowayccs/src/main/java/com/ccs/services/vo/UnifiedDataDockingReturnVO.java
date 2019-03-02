package com.ccs.services.vo;

import java.io.Serializable;

public class UnifiedDataDockingReturnVO implements Serializable {
	
	public static final String RETURN_CODE_SUCCESS = "200";
	
	public static final String RETURN_CODE_ERROR = "300";

	/**
	 * 
	 */
	private static final long serialVersionUID = 6754494266085612796L;
	
	private String resultCode;
	
	private String resultMsg;
	
	private String issueNew;
	
	private String serialNumber;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(String issueNew) {
		this.issueNew = issueNew;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

}
