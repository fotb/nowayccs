package com.ccs.web.domain;

import java.io.Serializable;

public class LifeBackVstDomain implements Serializable {

	private static final long serialVersionUID = 5131468643229805576L;

	private String callMode;

	private String callResult;

	private String callTime;

	private String finishTime;

	private String helpApprove;

	private String unApproveCause;

	private String dealResult;

	private String dealContent;

	private String remark;

	private String principal;

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

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
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

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
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

}
