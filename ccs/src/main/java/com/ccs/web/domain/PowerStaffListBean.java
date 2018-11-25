package com.ccs.web.domain;

import java.io.Serializable;

import com.ccs.vo.PowerStaffVO;

public class PowerStaffListBean extends PowerStaffVO implements Serializable {

	private static final long serialVersionUID = -3980190875253957681L;
	
	private String areaSubName;

	public String getAreaSubName() {
		return areaSubName;
	}

	public void setAreaSubName(String areaSubName) {
		this.areaSubName = areaSubName;
	}
	
}
