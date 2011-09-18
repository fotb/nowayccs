package com.ccs.web.domain;

import java.io.Serializable;
import java.util.Date;

public class InfoBean implements Serializable {

	private static final long serialVersionUID = -1483581214079226413L;
	private String infoId;

	private String helpName;

	private String helpMode;

	private String helpTel;

	private String helpAddr;

	private String helpContent;

	private String helpType;

	private String helpArea;

	private String creator;

	private Date createTime;

	private String helpGroup;

	private Date finishTime;

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

	public String getHelpMode() {
		return helpMode;
	}

	public void setHelpMode(String helpMode) {
		this.helpMode = helpMode;
	}

	public String getHelpTel() {
		return helpTel;
	}

	public void setHelpTel(String helpTel) {
		this.helpTel = helpTel;
	}

	public String getHelpAddr() {
		return helpAddr;
	}

	public void setHelpAddr(String helpAddr) {
		this.helpAddr = helpAddr;
	}

	public String getHelpContent() {
		return helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}

	public String getHelpType() {
		return helpType;
	}

	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}

	public String getHelpArea() {
		return helpArea;
	}

	public void setHelpArea(String helpArea) {
		this.helpArea = helpArea;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHelpGroup() {
		return helpGroup;
	}

	public void setHelpGroup(String helpGroup) {
		this.helpGroup = helpGroup;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
