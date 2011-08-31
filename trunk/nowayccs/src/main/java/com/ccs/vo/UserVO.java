package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_USER")
public class UserVO implements Serializable {

	private static final long serialVersionUID = -5007845231641201412L;

	public static final String ONJOB_YES = "1";
	
	public static final String ONJOB_NO = "0";
	
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "USERID")
	private String userId;

	@Column(name = "LOGINNAME")
	private String loginName;

	@Column(name = "LOGINPASSWORD")
	private String loginPassword;

	@Column(name = "USERNAME")
	private String userName;

	@Column(name = "HOMETEL")
	private String homeTel;

	@Column(name = "HOMEADDR")
	private String homeAddr;

	@Column(name = "HOMEPOSTCODE")
	private String homePostCode;

	@Column(name = "OFFICETEL")
	private String officeTel;

	@Column(name = "OFFICEADDR")
	private String officeAddr;

	@Column(name = "OFFICEPOSTCODE")
	private String officePostCode;

	@Column(name = "LINKMOBILE")
	private String linkMobile;

	@Column(name = "EMAILADDR")
	private String emailAddr;

	@Column(name = "ONJOB")
	private String onJob;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserVO other = (UserVO) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
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

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getHomePostCode() {
		return homePostCode;
	}

	public void setHomePostCode(String homePostCode) {
		this.homePostCode = homePostCode;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeAddr() {
		return officeAddr;
	}

	public void setOfficeAddr(String officeAddr) {
		this.officeAddr = officeAddr;
	}

	public String getOfficePostCode() {
		return officePostCode;
	}

	public void setOfficePostCode(String officePostCode) {
		this.officePostCode = officePostCode;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getOnJob() {
		return onJob;
	}

	public void setOnJob(String onJob) {
		this.onJob = onJob;
	}

}
