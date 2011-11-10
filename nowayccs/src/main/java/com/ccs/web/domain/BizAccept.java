package com.ccs.web.domain;

import java.io.Serializable;

public class BizAccept implements Serializable {
	private static final long serialVersionUID = 5602050510024652988L;

	private String helpName; // 求助者姓名
	private String helpMode; // 求助方式
	private String helpTel; // 求助者电话
	private String helpAddr; // 求助者地址
	private String helpContent; // 求助内容
	
	private String helpContent2; //第二求助内容，针对生活类服务
	
	private String helpContent3; //第三求助内容，针对生活类服务
	
	private String helpType; // 求助类型
	private String helpArea; // 求助区域
	private String creator; // 创建者
	private String createTime; // 创建时间
	private String result; // 咨询类处理结果
	private String handAcceptor; // 事务类移交接受人

	private String helpGroup;// 受理人群
	
	private String popupFlag;
	
	public String getPopupFlag() {
		return popupFlag;
	}

	public void setPopupFlag(String popupFlag) {
		this.popupFlag = popupFlag;
	}

	public String getHelpContent3() {
		return helpContent3;
	}

	public void setHelpContent3(String helpContent3) {
		this.helpContent3 = helpContent3;
	}

	public String getHelpContent2() {
		return helpContent2;
	}

	public void setHelpContent2(String helpContent2) {
		this.helpContent2 = helpContent2;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getCreator() {
		return creator;
	}

	public String getHandAcceptor() {
		return handAcceptor;
	}

	public String getHelpAddr() {
		return helpAddr;
	}

	public String getHelpArea() {
		return helpArea;
	}

	public String getHelpContent() {
		return helpContent;
	}

	public String getHelpMode() {
		return helpMode;
	}

	public String getHelpName() {
		return helpName;
	}

	public String getHelpTel() {
		return helpTel;
	}

	public String getHelpType() {
		return helpType;
	}

	public String getResult() {
		return result;
	}

	public String getHelpGroup() {
		return helpGroup;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}

	public void setHelpTel(String helpTel) {
		this.helpTel = helpTel;
	}

	public void setHelpName(String helpName) {
		this.helpName = helpName;
	}

	public void setHelpMode(String helpMode) {
		this.helpMode = helpMode;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}

	public void setHelpArea(String helpArea) {
		this.helpArea = helpArea;
	}

	public void setHelpAddr(String helpAddr) {
		this.helpAddr = helpAddr;
	}

	public void setHandAcceptor(String handAcceptor) {
		this.handAcceptor = handAcceptor;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setHelpGroup(String helpGroup) {
		this.helpGroup = helpGroup;
	}
}
