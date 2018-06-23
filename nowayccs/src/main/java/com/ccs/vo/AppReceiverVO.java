package com.ccs.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APP_RECEIVER")
public class AppReceiverVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -287207187566833221L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "RECEIVERID")
	private String receiverId;
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
	@Column(name = "CREATETIME")
	private Date createTime;
	@Column(name = "HELPGROUP")
	private String helpGroup;
	@Column(name = "HELPCATEGORY")
	private String helpCategory;
	@Column(name = "STATUS")
	private String status;

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHelpGroup() {
		return helpGroup;
	}

	public void setHelpGroup(String helpGroup) {
		this.helpGroup = helpGroup;
	}

	public String getHelpCategory() {
		return helpCategory;
	}

	public void setHelpCategory(String helpCategory) {
		this.helpCategory = helpCategory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
