package com.ccs.bean;

import java.io.Serializable;

public class ReceiverEntDTO implements Serializable {
	private static final long serialVersionUID = 2838722902083942537L;

	private String entpriseId;

	private String entpriseNo;

	private String entpriseName;

	private String serviceTel;

	private String totalDispatch;

	private String todayDispatch;

	public String getEntpriseId() {
		return entpriseId;
	}

	public void setEntpriseId(String entpriseId) {
		this.entpriseId = entpriseId;
	}

	public String getEntpriseNo() {
		return entpriseNo;
	}

	public void setEntpriseNo(String entpriseNo) {
		this.entpriseNo = entpriseNo;
	}

	public String getEntpriseName() {
		return entpriseName;
	}

	public void setEntpriseName(String entpriseName) {
		this.entpriseName = entpriseName;
	}

	public String getServiceTel() {
		return serviceTel;
	}

	public void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public String getTotalDispatch() {
		return totalDispatch;
	}

	public void setTotalDispatch(String totalDispatch) {
		this.totalDispatch = totalDispatch;
	}

	public String getTodayDispatch() {
		return todayDispatch;
	}

	public void setTodayDispatch(String todayDispatch) {
		this.todayDispatch = todayDispatch;
	}

}
