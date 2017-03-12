package com.ccs.report.util;

import java.io.Serializable;
import java.util.Date;

public class InfoDateCountBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6539237686530225615L;
	
	private String sdate;
	
	private String count;

	public float getSdate() {
		Date date = DateUtil.parse(sdate, "yyyyMMdd");
		return date.getTime();
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public float getCount() {
		return Float.valueOf(count);
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	

}
