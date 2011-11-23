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
@Table(name = "SMS_RECV")
public class SmsRecvVO implements Serializable {

	private static final long serialVersionUID = 2614198539225996800L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SMS_ID")
	private Long smsId;
	
	@Column(name="ORG_TELE")
	private String orgTele;
	
	@Column(name="INSE_DATE")
	private Date inseDate;
	
	@Column(name="SMS_CONTENT")
	private String smsContent;
	
	@Column(name="SMS_STATUS")
	private String smsStatus;
	
	@Column(name="RE_CONTENT")
	private String reContent;
	
	@Column(name="RE_OPER")
	private String reOper;
	
	@Column(name="RE_DATE")
	private Date reDate;
	
	@Column(name="DEST_TELE")
	private String destTele;

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public String getOrgTele() {
		return orgTele;
	}

	public void setOrgTele(String orgTele) {
		this.orgTele = orgTele;
	}

	public Date getInseDate() {
		return inseDate;
	}

	public void setInseDate(Date inseDate) {
		this.inseDate = inseDate;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public String getReOper() {
		return reOper;
	}

	public void setReOper(String reOper) {
		this.reOper = reOper;
	}

	public Date getReDate() {
		return reDate;
	}

	public void setReDate(Date reDate) {
		this.reDate = reDate;
	}

	public String getDestTele() {
		return destTele;
	}

	public void setDestTele(String destTele) {
		this.destTele = destTele;
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
		SmsRecvVO other = (SmsRecvVO) obj;
		if (smsId == null) {
			if (other.smsId != null)
				return false;
		} else if (!smsId.equals(other.smsId))
			return false;
		return true;
	}
}
