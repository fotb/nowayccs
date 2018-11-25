package com.ccs.bean;

import java.io.Serializable;

public class UserTrafficBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5930923244448944360L;

	
	private String userId;
	
	private String loginName;
	
	private String userName;
	
	private int traffic;

	
	public UserTrafficBean(String userId, String loginName, String userName,
			long traffic) {
		super();
		this.userId = userId;
		this.loginName = loginName;
		this.userName = userName;
		this.traffic = (int) traffic;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTraffic() {
		return traffic;
	}

	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}
	
}
