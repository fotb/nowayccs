package com.ccs.bean;

import java.io.Serializable;
import java.util.Date;

public class AffairInfoReportDTO implements Serializable {
	private static final long serialVersionUID = -296601043356293836L;

	private String infoId;

	private String helpName;

	private Date createTime;

	private String helpTel;

	private String helpContent;

	private String moveWay;

	private String moveAcceptor;

	private String creator;

	private String callResult;

	private String status;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getHelpName() {
		return helpName;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHelpTel() {
		return helpTel;
	}

	public void setHelpTel(String helpTel) {
		this.helpTel = helpTel;
	}

	public String getHelpContent() {
		return helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}

	public String getMoveWay() {
		return moveWay;
	}

	public void setMoveWay(String moveWay) {
		this.moveWay = moveWay;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCallResult() {
		return callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMoveAcceptor() {
		return moveAcceptor;
	}

	public void setMoveAcceptor(String moveAcceptor) {
		this.moveAcceptor = moveAcceptor;
	}
}
