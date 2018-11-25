package com.ccs.web.domain;

import java.io.Serializable;

public class AffairDispatchBean implements Serializable {

	private static final long serialVersionUID = -556499363593249003L;

	private String infoId;

	private String moveWay;

	private String moveAcceptor;

	private String acceptorTel;

	private String moveMode;

	private String moveTime;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getMoveWay() {
		return moveWay;
	}

	public void setMoveWay(String moveWay) {
		this.moveWay = moveWay;
	}

	public String getMoveAcceptor() {
		return moveAcceptor;
	}

	public void setMoveAcceptor(String moveAcceptor) {
		this.moveAcceptor = moveAcceptor;
	}

	public String getAcceptorTel() {
		return acceptorTel;
	}

	public void setAcceptorTel(String acceptorTel) {
		this.acceptorTel = acceptorTel;
	}

	public String getMoveMode() {
		return moveMode;
	}

	public void setMoveMode(String moveMode) {
		this.moveMode = moveMode;
	}

	public String getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(String moveTime) {
		this.moveTime = moveTime;
	}

}
