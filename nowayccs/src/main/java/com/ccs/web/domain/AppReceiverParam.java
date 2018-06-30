package com.ccs.web.domain;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class AppReceiverParam implements java.io.Serializable{
	
	private static final long serialVersionUID = 2912728370708818997L;

	@NotBlank(message = "求助者姓名不能为空")
	@Length(min = 1, max = 20, message="求助者姓名长度不能超过20")
	private String helpName;
	
	@NotBlank(message = "求助方式不能为空，默认为6")
	@Pattern(regexp = "6", message="求助方式默认值为6")
	private String helpMode;
	
	@NotBlank(message = "联系电话不能为空")
	@Length(min = 1, max = 1024, message="联系电话长度不能超过1024")
	private String helpTel;
	
	@NotBlank(message = "地址不能为空")
	@Length(min = 1, max = 50, message="地址长度不能超过50")
	private String helpAddr;
	
	@NotBlank(message = "求组内容不能为空")
	@Length(min = 1, max = 50, message="求助内容长度不能超过1024")
	private String helpContent;
	
	@NotBlank(message = "求助类型不能为空")
	@Pattern(regexp = "2", message="求助类型默认值为2")
	private String helpType;
	
	@NotBlank(message = "求助区域不能为空")
	@Length(min = 1, max = 10, message="求助区域长度不能超过10")
	private String helpArea;
	
	//@NotBlank(message = "受理人群不能为空")
	private String helpGroup;
	
	@NotBlank(message = "状态不能为空，默认为0")
	@Pattern(regexp = "0", message="状态默认值为0")
	private String status;

	
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
