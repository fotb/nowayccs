package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_LIFEINFORMATION")
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

	@Column(name = "CALLMODE")
	private String callMode;

	@Column(name = "CALLRESULT")
	private String callResult;

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
	private Date callTime;

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

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lifeInfoId == null) ? 0 : lifeInfoId.hashCode());
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
		LifeInformationVO other = (LifeInformationVO) obj;
		if (lifeInfoId == null) {
			if (other.lifeInfoId != null)
				return false;
		} else if (!lifeInfoId.equals(other.lifeInfoId))
			return false;
		return true;
	}

}
