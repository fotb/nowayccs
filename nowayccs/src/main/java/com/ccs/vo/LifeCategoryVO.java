package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="HJ_LIFECATEGORY")
public class LifeCategoryVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -933437116602065181L;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="parentCode")
	private String parentCode;
	
	@Column(name="Detal")
	private String Detail;

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

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDetail() {
		return Detail;
	}

	public void setDetail(String detail) {
		Detail = detail;
	}
	
	

}
