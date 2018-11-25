package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_HELPTYPE")
public class HelpTypeVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2018656311337849399L;
	
	@Column(name = "PARENTID")
	private String parentId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "remark")
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
