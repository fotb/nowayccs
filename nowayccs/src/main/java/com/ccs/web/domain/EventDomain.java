package com.ccs.web.domain;

import java.io.Serializable;

public class EventDomain implements Serializable {

	private static final long serialVersionUID = 7018594047925221778L;
	private String eventSubject;
	private String eventDate;
	private String eventLocation;
	private String eventContent;
	private String eventLevel;
	private String isImpPlase;
	private String relatePeopleCount;
	private String firstCategoryId;
	private String mobile;
	private String objName;
	

	public EventDomain() {
		super();
	}

	public EventDomain(String eventSubject, String eventDate, String eventLocation, String eventContent,
			String eventLevel, String isImpPlase, String relatePeopleCount, String firstCategoryId, String mobile,
			String objName) {
		super();
		this.eventSubject = eventSubject;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.eventContent = eventContent;
		this.eventLevel = eventLevel;
		this.isImpPlase = isImpPlase;
		this.relatePeopleCount = relatePeopleCount;
		this.firstCategoryId = firstCategoryId;
		this.mobile = mobile;
		this.objName = objName;
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

	public String getFirstCategoryId() {
		return firstCategoryId;
	}

	public void setFirstCategoryId(String firstCategoryId) {
		this.firstCategoryId = firstCategoryId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

}
