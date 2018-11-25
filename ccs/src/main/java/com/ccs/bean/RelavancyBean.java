package com.ccs.bean;

import java.io.Serializable;

public class RelavancyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2354694872191217055L;

	private String rclassIfIcation;
	private String rclassIfIcationId;
	private String objName; // ":"人员名称",
	private String mPhone; // ":"15217868765",
	private String description; // ":"关联人员描述"

	public String getRclassIfIcation() {
		return rclassIfIcation;
	}

	public void setRclassIfIcation(String rclassIfIcation) {
		this.rclassIfIcation = rclassIfIcation;
	}

	public String getRclassIfIcationId() {
		return rclassIfIcationId;
	}

	public void setRclassIfIcationId(String rclassIfIcationId) {
		this.rclassIfIcationId = rclassIfIcationId;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
