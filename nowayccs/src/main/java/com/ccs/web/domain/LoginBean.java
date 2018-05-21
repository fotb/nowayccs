package com.ccs.web.domain;

import java.io.Serializable;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3300064745325217854L;

	private String loginName;
	
	private String loginPassword;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
}
