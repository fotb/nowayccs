package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_XZSP_INDEX")
public class XzspIndexVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7940703423673686232L;
	
	public static final String ROOT_NODE_FLAG = "-1";

	@Column(name = "LISTCODE")
	private String listCode;

	@Column(name = "PARENTCODE")
	private String parentCode;
	@Column(name = "NAME")
	private String name;
	public String getListCode() {
		return listCode;
	}
	public void setListCode(String listCode) {
		this.listCode = listCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}



}
