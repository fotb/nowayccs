package com.ccs.bean;

import java.io.Serializable;

public class PushEventBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3651820074132603527L;
	private EventBean event;

	public EventBean getEvent() {
		return event;
	}

	public void setEvent(EventBean event) {
		this.event = event;
	}

}
