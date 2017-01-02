package com.ccs.util;

import java.io.Serializable;

public class YearCountBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5603332701323245695L;

	private String date;

	private int count;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
