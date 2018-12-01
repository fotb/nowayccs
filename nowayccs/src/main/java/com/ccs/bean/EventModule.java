package com.ccs.bean;

import java.io.Serializable;
import java.util.List;

public class EventModule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3546000705294749618L;
	
	private List<Object> attachments;
	
	private String eventContent;
	
	private String eventDate;
	
	private List<EventFlowDTO> eventFlowDTOList;
	
	private List<RelavancyBean> relavancyList;
	
	private String eventLevel;
	
	private String eventLocation;
	
	private String eventSource;
	
	private String eventSubject;
	
	private String firstCategoryId;
	
	private String isImpplace;
	
	private String latiTude;
	
	private String longiTude;
	
	private String mobile;
	
	private String organizationId;
	
	private String orgInternalCode;
	
	private String relatePeopleCount;
	
	private String secondCategoryId;
	
	private String serialNumber;
	
	private String status;
	
	private String limitDate;
	
	private String whereTo;
	
	private String sourcePlatformName;
	
	private String shuntPlatformName;

	public List<Object> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}

	public String getEventContent() {
		return eventContent;
	}

	public void setEventContent(String eventContent) {
		this.eventContent = eventContent;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public List<EventFlowDTO> getEventFlowDTOList() {
		return eventFlowDTOList;
	}

	public void setEventFlowDTOList(List<EventFlowDTO> eventFlowDTOList) {
		this.eventFlowDTOList = eventFlowDTOList;
	}

	public List<RelavancyBean> getRelavancyList() {
		return relavancyList;
	}

	public void setRelavancyList(List<RelavancyBean> relavancyList) {
		this.relavancyList = relavancyList;
	}

	public String getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public String getEventSubject() {
		return eventSubject;
	}

	public void setEventSubject(String eventSubject) {
		this.eventSubject = eventSubject;
	}

	public String getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(String firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}

	public String getIsImpplace() {
		return isImpplace;
	}

	public void setIsImpplace(String isImpplace) {
		this.isImpplace = isImpplace;
	}

	public String getLatiTude() {
		return latiTude;
	}

	public void setLatiTude(String latiTude) {
		this.latiTude = latiTude;
	}

	public String getLongiTude() {
		return longiTude;
	}

	public void setLongiTude(String longiTude) {
		this.longiTude = longiTude;
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

	public String getOrgInternalCode() {
		return orgInternalCode;
	}

	public void setOrgInternalCode(String orgInternalCode) {
		this.orgInternalCode = orgInternalCode;
	}

	public String getRelatePeopleCount() {
		return relatePeopleCount;
	}

	public void setRelatePeopleCount(String relatePeopleCount) {
		this.relatePeopleCount = relatePeopleCount;
	}

	public String getSecondCategoryId() {
		return secondCategoryId;
	}

	public void setSecondCategoryId(String secondCategoryId) {
		this.secondCategoryId = secondCategoryId;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}

	public String getWhereTo() {
		return whereTo;
	}

	public void setWhereTo(String whereTo) {
		this.whereTo = whereTo;
	}

	public String getSourcePlatformName() {
		return sourcePlatformName;
	}

	public void setSourcePlatformName(String sourcePlatformName) {
		this.sourcePlatformName = sourcePlatformName;
	}

	public String getShuntPlatformName() {
		return shuntPlatformName;
	}

	public void setShuntPlatformName(String shuntPlatformName) {
		this.shuntPlatformName = shuntPlatformName;
	}
}
