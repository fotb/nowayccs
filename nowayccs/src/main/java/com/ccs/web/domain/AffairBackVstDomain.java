package com.ccs.web.domain;

import java.io.Serializable;

public class AffairBackVstDomain implements Serializable {

	private static final long serialVersionUID = 5131468643229805576L;

	private String answerMode;

	private String answerTime;

	private String answerResult;

	private String callMode;

	private String callResult;

	private String helpApprove;

	private String unApproveCause;

	private String remark;

	private String principal;

	private String finishTime;
	
	private String callTime;
	
	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getAnswerMode() {
		return answerMode;
	}

	public void setAnswerMode(String answerMode) {
		this.answerMode = answerMode;
	}

	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswerResult() {
		return answerResult;
	}

	public void setAnswerResult(String answerResult) {
		this.answerResult = answerResult;
	}

	public String getCallMode() {
		return callMode;
	}

	public void setCallMode(String callMode) {
		this.callMode = callMode;
	}

	public String getCallResult() {
		return callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

	public String getHelpApprove() {
		return helpApprove;
	}

	public void setHelpApprove(String helpApprove) {
		this.helpApprove = helpApprove;
	}

	public String getUnApproveCause() {
		return unApproveCause;
	}

	public void setUnApproveCause(String unApproveCause) {
		this.unApproveCause = unApproveCause;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

}
