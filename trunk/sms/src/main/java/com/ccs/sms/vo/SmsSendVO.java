package com.ccs.sms.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_SEND")
public class SmsSendVO implements Serializable {

	private static final long serialVersionUID = -4723664958381721109L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SMS_ID")
	private Long smsId;
	
	@Column(name="TELE_NUM")
	private String teleNum;
	
	@Column(name="SMS_CONTENT")
	private String smsContent;
	
	@Column(name="SEND_DATE")
	private Date sendDate;
	
	@Column(name="OPER_NAME")
	private String operName;
	
	@Column(name="SMS_STATUS")
	private String smsStatus;
	
	@Column(name="ORG_TELE")
	private String orgTele;

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public String getTeleNum() {
		return teleNum;
	}

	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getOrgTele() {
		return orgTele;
	}

	public void setOrgTele(String orgTele) {
		this.orgTele = orgTele;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((smsId == null) ? 0 : smsId.hashCode());
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
		SmsSendVO other = (SmsSendVO) obj;
		if (smsId == null) {
			if (other.smsId != null)
				return false;
		} else if (!smsId.equals(other.smsId))
			return false;
		return true;
	}
}
