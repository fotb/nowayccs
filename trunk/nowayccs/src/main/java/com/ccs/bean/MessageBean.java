package com.ccs.bean;

import java.io.Serializable;
import java.util.Date;

import com.ccs.util.DateUtil;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = -1644446826763497151L;

	private String messageType;

	private String creator;

	private String title;

	private String startDt;

	private String endDt;

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDt() {
		return null == startDt ? DateUtil.format(
				DateUtil.getDayOfProvsMonth(new Date()), "yyyy-MM-dd")
				: startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return null == endDt ? DateUtil.format(new Date(), "yyyy-MM-dd")
				: endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

}
