package com.ccs.bean;

import java.io.Serializable;
import java.util.Date;

import com.ccs.util.DateUtil;

public class InfoSearchBean implements Serializable {

	private static final long serialVersionUID = 2610925888450029041L;

	private String creator;

	private String helpType;

	private String helpArea;

	private String helpGroup;

	private String startDt;

	private String endDt;

	private String helpTel;

	private String address;
	
	private String helpContent;

	public String getHelpContent() {
		return helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getHelpGroup() {
		return helpGroup;
	}

	public void setHelpGroup(String helpGroup) {
		this.helpGroup = helpGroup;
	}

	public String getStartDt() {
		return null == startDt ? DateUtil.format(new Date(), "yyyy-MM-dd") : startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return null == endDt ? DateUtil.format(new Date(), "yyyy-MM-dd") : endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getHelpTel() {
		return helpTel;
	}

	public void setHelpTel(String helpTel) {
		this.helpTel = helpTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
