package com.ccs.web.domain;

import java.io.Serializable;

public class PowerStaffReportBean implements Serializable {

	private static final long serialVersionUID = 1810429246887324561L;
	
	private String staffId;
	
	private String name;
	
	private String phone;
	
	private String area;
	
	private String areaSub;
	
	private int count;
	
	private int todayCount;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaSub() {
		return areaSub;
	}

	public void setAreaSub(String areaSub) {
		this.areaSub = areaSub;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTodayCount() {
		return todayCount;
	}

	public void setTodayCount(int todayCount) {
		this.todayCount = todayCount;
	}

}
