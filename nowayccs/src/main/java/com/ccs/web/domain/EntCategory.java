package com.ccs.web.domain;

import java.io.Serializable;

public class EntCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234583815197431777L;
	private String categoryId;

	private String parentId;

	private String subParentId;

	private String value;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSubParentId() {
		return subParentId;
	}

	public void setSubParentId(String subParentId) {
		this.subParentId = subParentId;
	}

	

}
