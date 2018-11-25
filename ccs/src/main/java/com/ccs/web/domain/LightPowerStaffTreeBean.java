package com.ccs.web.domain;


import java.io.Serializable;
import java.util.List;

public class LightPowerStaffTreeBean implements Serializable {

	private static final long serialVersionUID = 451226974376200141L;
	
	private String total;
	
	private List<LPSRowBean> rows;
	
	private List<TbFooterBean> footer;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public List<LPSRowBean> getRows() {
		return rows;
	}

	public void setRows(List<LPSRowBean> rows) {
		this.rows = rows;
	}

	public List<TbFooterBean> getFooter() {
		return footer;
	}

	public void setFooter(List<TbFooterBean> footer) {
		this.footer = footer;
	}
}
