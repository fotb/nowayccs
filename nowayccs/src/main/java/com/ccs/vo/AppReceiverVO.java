package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "APP_RECEIVER")
public class AppReceiverVO extends BaseEntity implements Serializable {
	
	public static final String STATUS_PROCESSING = "P";
	public static final String STATUS_FINISH = "F";

	/**
	 * 
	 */
	private static final long serialVersionUID = -287207187566833221L;

	@Column(name = "HELPNAME")
	private String helpName;
	
	@Column(name = "HELPMODE")
	private String helpMode;
	
	@Column(name = "HELPTEL")
	private String helpTel;
	
	@Column(name = "HELPADDR")
	private String helpAddr;
	
	@Column(name = "HELPCONTENT")
	private String helpContent;
	
	@Column(name = "HELPTYPE")
	private String helpType;
	
	@Column(name = "HELPAREA")
	private String helpArea;
	
	@Column(name = "HELPGROUP")
	private String helpGroup;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ORDERNUMBER")
	private String orderNumber;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getHelpName() {
		return helpName;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}

	public String getHelpMode() {
		return helpMode;
	}

	public void setHelpMode(String helpMode) {
		this.helpMode = helpMode;
	}

	public String getHelpTel() {
		return helpTel;
	}

	public void setHelpTel(String helpTel) {
		this.helpTel = helpTel;
	}

	public String getHelpAddr() {
		return helpAddr;
	}

	public void setHelpAddr(String helpAddr) {
		this.helpAddr = helpAddr;
	}

	public String getHelpContent() {
		return helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}

	public String getHelpType() {
		return helpType;
	}

	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}

	public String getHelpArea() {
		return helpArea;
	}

	public void setHelpArea(String helpArea) {
		this.helpArea = helpArea;
	}

	public String getHelpGroup() {
		return helpGroup;
	}

	public void setHelpGroup(String helpGroup) {
		this.helpGroup = helpGroup;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
