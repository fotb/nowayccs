package com.ccs.bean;

import java.io.Serializable;
import java.util.Date;

import com.ccs.util.DateUtil;

public class AffairInfoSearchBean implements Serializable {

	private static final long serialVersionUID = 2610925888450029041L;

	private String startDt;

	private String endDt;
	
	private String helpApprove;
	
	private String status;

	public String getHelpApprove() {
		return helpApprove;
	}

	public void setHelpApprove(String helpApprove) {
		this.helpApprove = helpApprove;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDt() {
		return null == startDt ? DateUtil.format(DateUtil.getDayOfProvsMonth(new Date()), "yyyy-MM-dd") : startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return null == endDt ? DateUtil.format(new Date(), "yyyy-MM-dd") : endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

}
