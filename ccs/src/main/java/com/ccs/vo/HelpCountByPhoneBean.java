package com.ccs.vo;

import java.io.Serializable;

public class HelpCountByPhoneBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3783913357611638615L;

	private String helpTel;

	private String telCount;

	public String getHelpTel() {
		return helpTel;
	}

	public void setHelpTel(String helpTel) {
		this.helpTel = helpTel;
	}

	public String getTelCount() {
		return telCount;
	}

	public void setTelCount(String telCount) {
		this.telCount = telCount;
	}

	public HelpCountByPhoneBean(String helpTel, String telCount) {
		super();
		this.helpTel = helpTel;
		this.telCount = telCount;
	}
}
