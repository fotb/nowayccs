package com.ccs.web.domain;

import java.io.Serializable;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3300064745325217854L;

	private String logName;
	
	private String password;

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
