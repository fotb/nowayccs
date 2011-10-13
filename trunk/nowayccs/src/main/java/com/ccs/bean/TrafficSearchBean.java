package com.ccs.bean;

import java.io.Serializable;
import java.util.Date;

import com.ccs.util.DateUtil;

public class TrafficSearchBean implements Serializable {

	private static final long serialVersionUID = -4499867880466323743L;

	private String loginName;

	private String startDt;

	private String endDt;

	private String helpType;

	public String getHelpType() {
		return helpType;
	}

	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
