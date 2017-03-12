package com.ccs.report.util;

import java.io.Serializable;

public class InfoAreaCountBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3465066645806906650L;
	
	private String areaId;
	
	private String areaName;
	
	private String count;

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
}
