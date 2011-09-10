package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class LifeInformationVO implements Serializable {
	private static final long serialVersionUID = -7636204697917297352L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "LIFEINFORMATIONID")
	private String lifeInfoId;

	@Column(name = "INFORMATIONID")
	private String infoId;
	@Column(name = "RECEIVERTYPE")
	private String receiverType;

	@Column(name = "RECEIVERID")
	private String receiverId;

	@Column(name = "HANDMODE")
	private String handMode;

	@Column(name = "HANDOR")
	private String handor;

	@Column(name = "HANDTIME")
	private String handTime;

	@Column(name = "CALLMODE")
	private String callMode;

	@Column(name = "CALLRESULT")
	private String callResult;

	@Column(name = "FINISHTIME")
	private String finishTime;

	@Column(name = "HELPAPPROVE")
	private String helpApprove;

	@Column(name = "UNAPPROVECAUSE")
	private String unApproveCase;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "DEALRESULT")
	private String dealResult;

	@Column(name = "DEALCONTENT")
	private String dealContent;

	@Column(name = "PRINCIPAL")
	private String principal;

	@Column(name = "CALLER")
	private String caller;
	@Column(name = "CALLTIME")
	private String callTime;

	@Column(name = "CANCELER")
	private String callceler;

	@Column(name = "CANCELTIME")
	private String callcelTime;

	public String getLifeInfoId() {
		return lifeInfoId;
	}

	public void setLifeInfoId(String lifeInfoId) {
		this.lifeInfoId = lifeInfoId;
	}

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

	public String getHandMode() {
		return handMode;
	}

	public void setHandMode(String handMode) {
		this.handMode = handMode;
	}

	public String getHandor() {
		return handor;
	}

	public void setHandor(String handor) {
		this.handor = handor;
	}

	public String getHandTime() {
		return handTime;
	}

	public void setHandTime(String handTime) {
		this.handTime = handTime;
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

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
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

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getDealContent() {
		return dealContent;
	}

	public void setDealContent(String dealContent) {
		this.dealContent = dealContent;
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

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getCallceler() {
		return callceler;
	}

	public void setCallceler(String callceler) {
		this.callceler = callceler;
	}

	public String getCallcelTime() {
		return callcelTime;
	}

	public void setCallcelTime(String callcelTime) {
		this.callcelTime = callcelTime;
	}

}
