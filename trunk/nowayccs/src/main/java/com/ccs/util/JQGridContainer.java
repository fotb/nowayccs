package com.ccs.util;

import java.io.Serializable;
import java.util.List;

public class JQGridContainer implements Serializable {

	private static final long serialVersionUID = -8725121536298459258L;

	private Integer page;
	private Integer total;
	private Integer records;
	private List<JQGridRow> rows;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public List<JQGridRow> getRows() {
		return rows;
	}

	public void setRows(List<JQGridRow> rows) {
		this.rows = rows;
	}

}
