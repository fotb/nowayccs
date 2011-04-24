package com.ccs.web.domain;

import java.io.Serializable;

public class EntCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234583815197431777L;

	private String categoryId;

	private String subCategoryId;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

}
