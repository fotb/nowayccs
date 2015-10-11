package com.ccs.web.domain;

import java.io.Serializable;

public class TbFooterBean implements Serializable {
	private static final long serialVersionUID = -1872724937289866705L;
	
	private String name;
	
	private String counts;
	
	private String iconCls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCounts() {
		return counts;
	}

	public void setCounts(String counts) {
		this.counts = counts;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
}
