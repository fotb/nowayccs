package com.ccs.web.domain;

import java.io.Serializable;

public class EntSearch implements Serializable {

	private static final long serialVersionUID = -9075569914198622802L;
	
	public static final String DEFAULT_VALUE_SELECT = "All";

	private String entpriseNo = "";

	private String entpriseName = "";

	private String parentCategoryId;

	private String subParentCategoryId;

	private String categoryId;

	public final String getEntpriseNo() {
		return entpriseNo;
	}

	public final void setEntpriseNo(String entpriseNo) {
		this.entpriseNo = entpriseNo;
	}

	public final String getEntpriseName() {
		return entpriseName;
	}

	public final void setEntpriseName(String entpriseName) {
		this.entpriseName = entpriseName;
	}

	public final String getParentCategoryId() {
		return parentCategoryId;
	}

	public final void setParentCategoryId(String parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public final String getSubParentCategoryId() {
		return subParentCategoryId;
	}

	public final void setSubParentCategoryId(String subParentCategoryId) {
		this.subParentCategoryId = subParentCategoryId;
	}

	public final String getCategoryId() {
		return categoryId;
	}

	public final void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
