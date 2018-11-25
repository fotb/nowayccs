package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_POWERSTAFF_AREA")
public class PowerStaffAreaVO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -644582884873884398L;

	@Column(name = "STAFFID")
	private String staffId;
	
	@Column(name = "AREASUBID")
	private String areaSubId;


	public String getStaffId() {
		return staffId;
	}


	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}


	public String getAreaSubId() {
		return areaSubId;
	}


	public void setAreaSubId(String areaSubId) {
		this.areaSubId = areaSubId;
	}
	
}
