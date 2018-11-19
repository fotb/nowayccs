package com.ccs.bean;

import java.io.Serializable;
import java.util.List;

public class EventBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3872460428097404357L;
	
	private String eventContent; //事件内容",
	private String eventDate ;// 1504176289293,
	private String eventLevel;//1,
	private String eventLocation; //事件地点",
	private String eventSource; //3",
	private String eventSubject; //事件标题",
	private String firstCategoryId; //01",
	private String isImpplace; //":1,
	private String latiTude; //39.915156",
	private String longiTude; //116.39737",
	private String mobile; //15385671230",
	private String relatePeopleCount; //涉及人数",
	private String secondCategoryId; //01011",
	private String status; //":1,
	private String userId; //8afac2ed47d91bb60147f15d01800b2a",
	private String createDate; //":1504176289293,
	private String whereTo; //赴县",
	private String serialNumber;
	
	private List<RelavancyBean> relavancyList;
	
	public List<RelavancyBean> getRelavancyList() {
		return relavancyList;
	}
	public void setRelavancyList(List<RelavancyBean> relavancyList) {
		this.relavancyList = relavancyList;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getWhereTo() {
		return whereTo;
	}
	public void setWhereTo(String whereTo) {
		this.whereTo = whereTo;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
}
