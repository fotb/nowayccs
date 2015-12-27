package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_POWERINFORMATION")
public class PowerInformationVO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 8653399342294000947L;
	@Column(name = "INFORMATIONID")
	private String informationId;
	@Column(name = "POWERSTAFFID")
	private String powerStaffId;
	@Column(name = "REMARK")
	private String remark;

	public String getInformationId() {
		return informationId;
	}

	public void setInformationId(String informationId) {
		this.informationId = informationId;
	}

	public String getPowerStaffId() {
		return powerStaffId;
	}

	public void setPowerStaffId(String powerStaffId) {
		this.powerStaffId = powerStaffId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
