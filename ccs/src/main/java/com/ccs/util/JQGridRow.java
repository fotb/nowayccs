package com.ccs.util;

import java.io.Serializable;
import java.util.List;

public class JQGridRow implements Serializable {
	private static final long serialVersionUID = -835959212338129667L;
	private String id;
	private List<String> cell;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getCell() {
		return cell;
	}

	public void setCell(List<String> cell) {
		this.cell = cell;
	}

}
