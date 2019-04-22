package com.ccs.services.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("data")
public class UnifiedDataDockingReturnVO implements Serializable {
	
	public static final String RETURN_CODE_SUCCESS = "200";
	
	public static final String RETURN_CODE_ERROR = "300";
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6754494266085612796L;
	
	private String resultCode;
	
	private String resultMsg;
	
	private IssueNew issueNew;
	

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

	public IssueNew getIssueNew() {
		return issueNew;
	}

	public void setIssueNew(IssueNew issueNew) {
		this.issueNew = issueNew;
	}

}
