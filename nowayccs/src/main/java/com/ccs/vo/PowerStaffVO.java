package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_POWERSTAFF")
public class PowerStaffVO extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6439274737938698609L;

	@Column(name = "NAME")
	private String name;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "REMARK")
	private String remark;
	@Column(name = "CATEGORY")
	private String category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
