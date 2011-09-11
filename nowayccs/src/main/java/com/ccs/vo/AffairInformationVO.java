package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_AFFAIRINFORMATION")
public class AffairInformationVO implements Serializable {

	private static final long serialVersionUID = -4258786584636252767L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "AFFAIRINFORMATIONID")
	private String affairInfoId;

	@Column(name = "INFORMATIONID")
	private String infoId;

	@Column(name = "HANDACCEPTOR")
	private String handAcceptor;

	@Column(name = "HANDOR")
	private String handor;

	@Column(name = "HANDTIME")
	private Date handTime;

	@Column(name = "MOVEWAY")
	private String moveWay;

	@Column(name = "MOVEMODE")
	private String moveMode;

	@Column(name = "MOVEACCEPTOR")
	private String moveAcceptor;

	@Column(name = "MOVEACCEPTTEL")
	private String moveAcceptTel;

	@Column(name = "MOVETIME")
	private String moveTime;

	@Column(name = "ANSWERMODE")
	private String answerMode;

	@Column(name = "ANSWERTIME")
	private Date answerTime;

	@Column(name = "ANSWERRESULT")
	private String answerResult;

	@Column(name = "CALLMODE")
	private String callMode;

	@Column(name = "CALLRESULT")
	private String callResult;

	@Column(name = "FINISHTIME")
	private Date finishTime;

	@Column(name = "HELPAPPROVE")
	private String helpApprove;

	@Column(name = "UNAPPROVECAUSE")
	private String unApproveCase;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "PRINCIPAL")
	private String principal;

	@Column(name = "CALLER")
	private String caller;

	@Column(name = "CALLTIME")
	private Date callTime;

	@Column(name = "CANCELER")
	private String canceler;

	@Column(name = "CANCELTIME")
	private Date cancelTime;

	public String getAffairInfoId() {
		return affairInfoId;
	}

	public void setAffairInfoId(String affairInfoId) {
		this.affairInfoId = affairInfoId;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getHandAcceptor() {
		return handAcceptor;
	}

	public void setHandAcceptor(String handAcceptor) {
		this.handAcceptor = handAcceptor;
	}

	public String getHandor() {
		return handor;
	}

	public void setHandor(String handor) {
		this.handor = handor;
	}

	public Date getHandTime() {
		return handTime;
	}

	public void setHandTime(Date handTime) {
		this.handTime = handTime;
	}

	public String getMoveWay() {
		return moveWay;
	}

	public void setMoveWay(String moveWay) {
		this.moveWay = moveWay;
	}

	public String getMoveMode() {
		return moveMode;
	}

	public void setMoveMode(String moveMode) {
		this.moveMode = moveMode;
	}

	public String getMoveAcceptor() {
		return moveAcceptor;
	}

	public void setMoveAcceptor(String moveAcceptor) {
		this.moveAcceptor = moveAcceptor;
	}

	public String getMoveAcceptTel() {
		return moveAcceptTel;
	}

	public void setMoveAcceptTel(String moveAcceptTel) {
		this.moveAcceptTel = moveAcceptTel;
	}

	public String getMoveTime() {
		return moveTime;
	}

	public void setMoveTime(String moveTime) {
		this.moveTime = moveTime;
	}

	public String getAnswerMode() {
		return answerMode;
	}

	public void setAnswerMode(String answerMode) {
		this.answerMode = answerMode;
	}

	public Date getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}

	public String getAnswerResult() {
		return answerResult;
	}

	public void setAnswerResult(String answerResult) {
		this.answerResult = answerResult;
	}

	public String getCallMode() {
		return callMode;
	}

	public void setCallMode(String callMode) {
		this.callMode = callMode;
	}

	public String getCallResult() {
		return callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getHelpApprove() {
		return helpApprove;
	}

	public void setHelpApprove(String helpApprove) {
		this.helpApprove = helpApprove;
	}

	public String getUnApproveCase() {
		return unApproveCase;
	}

	public void setUnApproveCase(String unApproveCase) {
		this.unApproveCase = unApproveCase;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public String getCanceler() {
		return canceler;
	}

	public void setCanceler(String canceler) {
		this.canceler = canceler;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((affairInfoId == null) ? 0 : affairInfoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AffairInformationVO other = (AffairInformationVO) obj;
		if (affairInfoId == null) {
			if (other.affairInfoId != null)
				return false;
		} else if (!affairInfoId.equals(other.affairInfoId))
			return false;
		return true;
	}

}
