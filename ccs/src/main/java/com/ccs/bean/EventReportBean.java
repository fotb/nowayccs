package com.ccs.bean;

import java.io.Serializable;

public class EventReportBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4475824475535157105L;

	private EventBean event;

	public EventBean getEvent() {
		return event;
	}

	public void setEvent(EventBean event) {
		this.event = event;
	}
}
