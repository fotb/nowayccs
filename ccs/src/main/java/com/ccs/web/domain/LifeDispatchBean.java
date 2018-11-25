package com.ccs.web.domain;

import java.io.Serializable;

public class LifeDispatchBean implements Serializable {

	private static final long serialVersionUID = -556499363593249003L;
	
	private String infoId;
	
	private String receiverType;
	
	private String receiverId;

	private String linkName;

	private String linkTel;

	private String handMode;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getHandMode() {
		return handMode;
	}

	public void setHandMode(String handMode) {
		this.handMode = handMode;
	}

}
