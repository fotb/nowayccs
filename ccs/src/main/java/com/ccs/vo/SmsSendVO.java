package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_SMSSEND")
public class SmsSendVO implements Serializable {

	private static final long serialVersionUID = -8350358318284328978L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "SMSID")
	private String smsId;

	@Column(name = "TELNUM")
	private String telNum;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "SENDDT")
	private Date sendDt;

	@Column(name = "STATUS")
	private String status;

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSendDt() {
		return sendDt;
	}

	public void setSendDt(Date sendDt) {
		this.sendDt = sendDt;
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
