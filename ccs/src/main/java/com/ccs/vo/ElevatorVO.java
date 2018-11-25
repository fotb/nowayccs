package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="HJ_ELEVATOR")
public class ElevatorVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -280155599709845444L;

	@Column(name = "USEDEPT")
	private String useDept;

	@Column(name = "POSITION")
	private String position;

	@Column(name = "SERVICENAME")
	private String serviceName;

	@Column(name = "MANUFACTURER")
	private String manufacturer;

	@Column(name = "REFERNO")
	private String referNo;

	@Column(name = "DEVEICEID")
	private String deviceId;

	@Column(name = "SERIALNUMBER")
	private String serialNumber;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "SURVEYDATE")
	private String surveyDate;
	
	@Column(name = "NEXTSURVEYDATE")
	private String nextSurveyDate;

	public String getUseDept() {
		return useDept;
	}

	public void setUseDept(String useDept) {
		this.useDept = useDept;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getReferNo() {
		return referNo;
	}

	public void setReferNo(String referNo) {
		this.referNo = referNo;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(String surveyDate) {
		this.surveyDate = surveyDate;
	}

	public String getNextSurveyDate() {
		return nextSurveyDate;
	}

	public void setNextSurveyDate(String nextSurveyDate) {
		this.nextSurveyDate = nextSurveyDate;
	}
}
