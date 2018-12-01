package com.ccs.bean;

import java.io.Serializable;

public class EventRequestBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285653334905878789L;

	private String requestId;
	
	private boolean success;
	
	private EventResponseBean response;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public EventResponseBean getResponse() {
		return response;
	}

	public void setResponse(EventResponseBean response) {
		this.response = response;
	}
	
	
}
