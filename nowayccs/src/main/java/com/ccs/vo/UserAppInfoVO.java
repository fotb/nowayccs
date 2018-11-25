package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_USERAPPINFO")
public class UserAppInfoVO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 3254471128752853007L;
	
	@Column(name = "USERID")
	private String userId;
	@Column(name = "APPINFOID")
	private String appInfoId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAppInfoId() {
		return appInfoId;
	}
	public void setAppInfoId(String appInfoId) {
		this.appInfoId = appInfoId;
	}

}
