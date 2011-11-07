package com.ccs.web.domain;

import java.io.Serializable;

public class ReceiverSearchDomain implements Serializable {

	private static final long serialVersionUID = 5435853177524512268L;

	private String receiverType;

	// volunteer
	private String areaId;

	private String areaSubId;

	private String volunteerNo;

	private String serviceName;

	// entprise
	private String entpriseNo;
	
	private String bigEntCategoryId;

	private String subEntCategoryId;

	private String entCategoryId;
	
	private String startDt;
	
	private String endDt;

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

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

	public String getEntpriseNo() {
		return entpriseNo;
	}

	public void setEntpriseNo(String entpriseNo) {
		this.entpriseNo = entpriseNo;
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
