package com.ccs.web.domain;

import java.io.Serializable;

public class CountBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6662138745151445087L;
	
	private String id;
	
	private Long count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
