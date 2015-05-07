package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_LONELYHELP")
public class LonelyHelpVO implements Serializable {

	private static final long serialVersionUID = -7531784278874380666L;
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "HELPID")
	private String helpId;
	
	@Column(name = "LONELYMANID")
	private String lonelyManId;
	
	@Column(name = "HELPCONTENT")
	private String helpContent;
	
	@Column(name = "CREATOR")
	private String creator;
	
	@Column(name = "CREATETIME")
	private Date createTime;
	
	@Column(name = "FINISHTIME")
	private Date finishTime;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "DELIVERER")
	private String deliverer;

	public String getHelpId() {
		return helpId;
	}

	public void setHelpId(String helpId) {
		this.helpId = helpId;
	}

	public String getLonelyManId() {
		return lonelyManId;
	}

	public void setLonelyManId(String lonelyManId) {
		this.lonelyManId = lonelyManId;
	}

	public String getHelpContent() {
		return helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
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

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((helpId == null) ? 0 : helpId.hashCode());
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
		LonelyHelpVO other = (LonelyHelpVO) obj;
		if (helpId == null) {
			if (other.helpId != null)
				return false;
		} else if (!helpId.equals(other.helpId))
			return false;
		return true;
	}

}
