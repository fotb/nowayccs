package com.ccs.bean;

import java.io.Serializable;

public class UserTrafficDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5930923244448944360L;

	private String userId;

	private String loginName;

	private String userName;

	private int referTraffic;

	private int lifeTraffic;

	private int affairTraffic;

	private int sclTraffic;

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

	public int getReferTraffic() {
		return referTraffic;
	}

	public void setReferTraffic(int referTraffic) {
		this.referTraffic = referTraffic;
	}

	public int getLifeTraffic() {
		return lifeTraffic;
	}

	public void setLifeTraffic(int lifeTraffic) {
		this.lifeTraffic = lifeTraffic;
	}

	public int getAffairTraffic() {
		return affairTraffic;
	}

	public void setAffairTraffic(int affairTraffic) {
		this.affairTraffic = affairTraffic;
	}

	public int getSclTraffic() {
		return sclTraffic;
	}

	public void setSclTraffic(int sclTraffic) {
		this.sclTraffic = sclTraffic;
	}

}
