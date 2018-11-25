package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_TRANSTYPE")
public class TransTypeVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2681253296743793004L;

	@Column(name = "PARENTID")
	private String parentId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	@Column(name = "REMARK")
	private String remark;


	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


}
