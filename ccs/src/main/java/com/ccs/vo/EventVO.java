package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="SGPT_EVENT")
public class EventVO extends BaseEntity implements Serializable {
	
	public final static String EVENT_SOURCE_HOTCALL = "6";
	
	public final static String EVENT_STATUS_10 = "10";
	
	public final static String EVENT_ORG_CODE = "330402ZF260000";
	
	public final static String EVENT_ORG_ID = "330402ZF260000";
	
	public final static String UP_STATUS_0 = "0"; //未上报
	
	public final static String UP_STATUS_1 = "1"; //已上报

	/**
	 * 
	 */
	private static final long serialVersionUID = 544998300844290004L;

	
	@Column(name = "EVENTSUBJECT")
	private String eventSubject;
	
	@Column(name = "SERIALNUMBER")
	private String serialNumber;
	
	@Column(name = "EVENTDATE")
	private String eventDate;
	
	@Column(name = "EVENTLOCATION")
	private String eventLocation;
	
	@Column(name = "EVENTCONTENT")
	private String eventContent;
	
	@Column(name = "EVENTLEVEL")
	private String eventLevel;
	
	@Column(name = "EVENTSOURCE")
	private String eventSource;
	
	@Column(name = "ISIMPPLACE")
	private String isImpPlase;
	
	@Column(name = "RELATEPEOPLECOUNT")
	private String relatePeopleCount;
	
	@Column(name = "LONGITUDE")
	private String longitude;
	
	@Column(name = "LATITUDE")
	private String latitude;
	
	@Column(name = "FIRSTCATEGORYID")
	private String firstCategoryId;
	
	@Column(name = "SECONDCATEGORYID")
	private String secondCategoryId;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "ORGANIZATIONID")
	private String organizationId;
	
	@Column(name = "ORGINTERNALCODE")
	private String orginternalCode;
	
	@Column(name = "CURRENTORGID")
	private String currentOrgId;
	
	@Column(name = "CURRENTORGCODE")
	private String currentOrgCode;
	
	@Column(name = "PLATFORMTYPE")
	private String platFromType;
	
	@Column(name = "SHUNTPLATFORMTYPE")
	private String shuntPlatFromType;
	
	@Column(name = "SHUNTDATE")
	private String shuntDate;
	
	@Column(name = "RCLASSIFICATION")
	private String rclassification;
	
	@Column(name = "RCLASSIFICATIONID")
	private String rclassificationId;
	
	@Column(name = "OBJNAME")
	private String objName;
	
	@Column(name = "MPHONE")
	private String mPhone;
	
	@Column(name="INFORMATIONID")
	private String informationId;
	
	@Column(name="UPSTATUS")
	private String upStatus;
	
	@Lob
	@Column(name="SUGGESTION", length=10000)
	private String suggestion;
	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getInformationId() {
		return informationId;
	}

	public void setInformationId(String informationId) {
		this.informationId = informationId;
	}

	public String getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(String upStatus) {
		this.upStatus = upStatus;
	}

	public String getEventSubject() {
		return eventSubject;
	}

	public void setEventSubject(String eventSubject) {
		this.eventSubject = eventSubject;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public String getIsImpPlase() {
		return isImpPlase;
	}

	public void setIsImpPlase(String isImpPlase) {
		this.isImpPlase = isImpPlase;
	}

	public String getRelatePeopleCount() {
		return relatePeopleCount;
	}

	public void setRelatePeopleCount(String relatePeopleCount) {
		this.relatePeopleCount = relatePeopleCount;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(String firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}

	public String getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(String secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrginternalCode() {
		return orginternalCode;
	}

	public void setOrginternalCode(String orginternalCode) {
		this.orginternalCode = orginternalCode;
	}

	public String getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(String currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public String getCurrentOrgCode() {
		return currentOrgCode;
	}

	public void setCurrentOrgCode(String currentOrgCode) {
		this.currentOrgCode = currentOrgCode;
	}

	public String getPlatFromType() {
		return platFromType;
	}

	public void setPlatFromType(String platFromType) {
		this.platFromType = platFromType;
	}

	public String getShuntPlatFromType() {
		return shuntPlatFromType;
	}

	public void setShuntPlatFromType(String shuntPlatFromType) {
		this.shuntPlatFromType = shuntPlatFromType;
	}

	public String getShuntDate() {
		return shuntDate;
	}

	public void setShuntDate(String shuntDate) {
		this.shuntDate = shuntDate;
	}

	public String getRclassification() {
		return rclassification;
	}

	public void setRclassification(String rclassification) {
		this.rclassification = rclassification;
	}

	public String getRclassificationId() {
		return rclassificationId;
	}

	public void setRclassificationId(String rclassificationId) {
		this.rclassificationId = rclassificationId;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
}
