package com.ccs.web.domain;

import java.io.Serializable;

public class HelpCountByPhoneSearchBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5793183085224349118L;

	private String startDt;
	
	private String endDt;

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	
}
