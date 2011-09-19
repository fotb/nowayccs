package com.ccs.web.domain;

import java.io.Serializable;

public class ReceiverSearchDomain implements Serializable {

	private static final long serialVersionUID = 5435853177524512268L;

	private String receiverType;

	// volunteer
	private String areaId;

	private String subAreaId;

	private String volunteerNo;

	private String serviceName;

	// entprise
	private String bigEntCategoryId;

	private String subEntCategoryId;

	private String entCategoryId;

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getSubAreaId() {
		return subAreaId;
	}

	public void setSubAreaId(String subAreaId) {
		this.subAreaId = subAreaId;
	}

	public String getVolunteerNo() {
		return volunteerNo;
	}

	public void setVolunteerNo(String volunteerNo) {
		this.volunteerNo = volunteerNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getBigEntCategoryId() {
		return bigEntCategoryId;
	}

	public void setBigEntCategoryId(String bigEntCategoryId) {
		this.bigEntCategoryId = bigEntCategoryId;
	}

	public String getSubEntCategoryId() {
		return subEntCategoryId;
	}

	public void setSubEntCategoryId(String subEntCategoryId) {
		this.subEntCategoryId = subEntCategoryId;
	}

	public String getEntCategoryId() {
		return entCategoryId;
	}

	public void setEntCategoryId(String entCategoryId) {
		this.entCategoryId = entCategoryId;
	}

}
