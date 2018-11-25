package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_SMSRECV")
public class SmsRecvVO implements Serializable {

	private static final long serialVersionUID = -8350358318284328978L;
	
	public static final String STATUS_NEW = "N";
	
	public static final String STATUS_COMPLETE = "C";
	
	public static final String STATUS_DELETE = "D";

	@Id
	@Column(name = "SMSID")
	private String smsId;

	@Column(name = "TELNUM")
	private String telNum;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "RECVDT")
	private Date recvDt;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "UPDATEDT")
	private Date updateDt;

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

	public Date getRecvDt() {
		return recvDt;
	}

	public void setRecvDt(Date recvDt) {
		this.recvDt = recvDt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
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
