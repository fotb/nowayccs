package com.ccs.web.domain;

import java.io.Serializable;
import java.util.Date;

public class ShsForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2731560430487261409L;
	
	private String specialName;
	
	private String telphone;
	
	private String memberName;
	
	private String startDt;
	
	private String endDt;

	public String getSpecialName() {
		return specialName;
	}

	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	
}
