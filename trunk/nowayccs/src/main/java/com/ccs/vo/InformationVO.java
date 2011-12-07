package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_INFORMATION")
public class InformationVO implements Serializable {
	private static final long serialVersionUID = 8151777801642274209L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "INFORMATIONID")
	private String infoId;

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

	@Column(name = "CREATOR")
	private String creator;

	@Column(name = "CREATETIME")
	private Date createTime;

	@Column(name = "HELPGROUP")
	private String helpGroup;

	@Column(name = "FINISHTIME")
	private Date finishTime;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "CANCELER")
	private String canceler;

	@Column(name = "CANCELTIME")
	private Date cancelTime;

	@Column(name = "DELIVERER")
	private String deliverer;

	@Column(name = "DELIVERMODE")
	private String deliverMode;

	@Column(name = "DELIVERTIME")
	private Date deliverTime;

	@Column(name = "AFFAIEACCEPTOR")
	private String affairAcceptor;

	@Column(name = "RECORDFILENAME")
	private String recordFileName;

	@Column(name = "ISRECORD")
	private String recordFlag;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getCanceler() {
		return canceler;
	}

	public void setCanceler(String canceler) {
		this.canceler = canceler;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	public String getDeliverMode() {
		return deliverMode;
	}

	public void setDeliverMode(String deliverMode) {
		this.deliverMode = deliverMode;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public String getAffairAcceptor() {
		return affairAcceptor;
	}

	public void setAffairAcceptor(String affairAcceptor) {
		this.affairAcceptor = affairAcceptor;
	}

	public String getRecordFileName() {
		return recordFileName;
	}

	public void setRecordFileName(String recordFileName) {
		this.recordFileName = recordFileName;
	}

	public String getRecordFlag() {
		return recordFlag;
	}

	public void setRecordFlag(String recordFlag) {
		this.recordFlag = recordFlag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((infoId == null) ? 0 : infoId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformationVO other = (InformationVO) obj;
		if (infoId == null) {
			if (other.infoId != null)
				return false;
		} else if (!infoId.equals(other.infoId))
			return false;
		return true;
	}

}
