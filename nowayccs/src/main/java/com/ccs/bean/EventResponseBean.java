package com.ccs.bean;

import java.io.Serializable;

public class EventResponseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4475824475535157105L;

	private EventModule module;
	
	private boolean success;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public EventModule getModule() {
		return module;
	}

	public void setModule(EventModule module) {
		this.module = module;
	}

}
