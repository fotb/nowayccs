package com.ccs.web.domain;

import java.io.Serializable;

public class VolunteerSearch implements Serializable {

	private static final long serialVersionUID = -505989481061719162L;

	private String status;

	private String serviceType;

	private String areaId;

	private String areaSubId;

	private String volunteerNo;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaSubId() {
		return areaSubId;
	}

	public void setAreaSubId(String areaSubId) {
		this.areaSubId = areaSubId;
	}

	public String getVolunteerNo() {
		return volunteerNo;
	}

	public void setVolunteerNo(String volunteerNo) {
		this.volunteerNo = volunteerNo;
	}

}
