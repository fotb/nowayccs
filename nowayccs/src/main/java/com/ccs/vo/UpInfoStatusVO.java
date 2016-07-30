package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UP_INFOSTATUS")
public class UpInfoStatusVO extends BaseEntity implements Serializable {
	
	public static final String UPLOAD_STATUS_YES = "1";
	public static final String UPLOAD_STATUS_NO = "0";

	/**
	 * 
	 */
	private static final long serialVersionUID = -3372123701243567800L;
	
	
	@Column(name = "INFORMATIONID")
	private String infomationId;
	
	
	@Column(name = "UPSTATUS")
	private String upStatus;
	
	
	@Column(name = "UPLOADDT")
	private Date upLoadDt;
	
	
	@Column(name = "RESULT")
	private String result;
	
	@Column(name = "REMARK")
	private String remark;

	public String getInfomationId() {
		return infomationId;
	}

	public void setInfomationId(String infomationId) {
		this.infomationId = infomationId;
	}

	public String getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(String upStatus) {
		this.upStatus = upStatus;
	}

	public Date getUpLoadDt() {
		return upLoadDt;
	}

	public void setUpLoadDt(Date upLoadDt) {
		this.upLoadDt = upLoadDt;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
