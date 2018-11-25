package com.ccs.bean;

import java.io.Serializable;

public class ReceiverVolunteerDTO implements Serializable {

	private static final long serialVersionUID = 4391564060388960310L;

	private String volunteerId;

	private String volunteerNo;
	
	private String volunteerName;

	private String serviceName;

	private String phone;

	private String totalDispatch;

	private String todayDispatch;

	public String getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}

	public String getVolunteerName() {
		return volunteerName;
	}

	public void setVolunteerName(String volunteerName) {
		this.volunteerName = volunteerName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTotalDispatch() {
		return totalDispatch;
	}

	public void setTotalDispatch(String totalDispatch) {
		this.totalDispatch = totalDispatch;
	}

	public String getTodayDispatch() {
		return todayDispatch;
	}

	public void setTodayDispatch(String todayDispatch) {
		this.todayDispatch = todayDispatch;
	}

	public String getVolunteerNo() {
		return volunteerNo;
	}

	public void setVolunteerNo(String volunteerNo) {
		this.volunteerNo = volunteerNo;
	}

}
