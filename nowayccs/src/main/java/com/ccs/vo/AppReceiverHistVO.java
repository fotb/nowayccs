package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "APP_RECEIVER_HIST")
public class AppReceiverHistVO extends BaseEntity implements Serializable {

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
	
	@Column(name = "IP")
	private String ip;
	
	@Column(name = "AGENTID")
	private String agentId;

	
	public AppReceiverHistVO(AppReceiverVO vo) {
		super();
		this.helpName = vo.getHelpName();
		this.helpMode = vo.getHelpMode();
		this.helpTel = vo.getHelpTel();
		this.helpAddr = vo.getHelpAddr();
		this.helpContent = vo.getHelpContent();
		this.helpType = vo.getHelpType();
		this.helpArea = vo.getHelpArea();
		this.helpGroup = vo.getHelpGroup();
		this.status = AppReceiverVO.STATUS_FINISH;
		this.ip = vo.getIp();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
}
