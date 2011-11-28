package com.ccs.bean;

import java.io.Serializable;
import java.util.Date;

import com.ccs.util.DateUtil;

public class LifeInfoSearchBean implements Serializable {

	private static final long serialVersionUID = 2610925888450029041L;

	private String receiverType;

	private String keyWords;

	private String startDt;

	private String endDt;

	private String helpArea;
	
	private String helpApprove;
	
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getHelpApprove() {
		return helpApprove;
	}

	public void setHelpApprove(String helpApprove) {
		this.helpApprove = helpApprove;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getStartDt() {
//		return null == startDt ? DateUtil.format(DateUtil.addDate(new Date(), - 1), "yyyy-MM-dd") : startDt;
		return null == startDt ? DateUtil.format(new Date(), "yyyy-MM-dd") : startDt;
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
	public String getHelpArea() {
		return helpArea;
	}

	public void setHelpArea(String helpArea) {
		this.helpArea = helpArea;
	}

}
