package com.ccs.web.domain;

import java.io.Serializable;

public class SmsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8805059987825965528L;

	
	private String phoneNum;
	
	private String smsContent;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}
	
	
}
