package com.ccs.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_REFERINFORMATION")
public class ReferInformationVO implements Serializable {

	private static final long serialVersionUID = 8479733713451338309L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "REFERINFORMATIONID")
	private String referInfoId;

	@Column(name = "INFORMATIONID")
	private String infoId;

	@Column(name = "RESULT")
	private String result;

	@Column(name = "DEALER")
	private String dealer;

	@Column(name = "DEALTIME")
	private Date dealTime;

	public String getReferInfoId() {
		return referInfoId;
	}

	public void setReferInfoId(String referInfoId) {
		this.referInfoId = referInfoId;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((referInfoId == null) ? 0 : referInfoId.hashCode());
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
		ReferInformationVO other = (ReferInformationVO) obj;
		if (referInfoId == null) {
			if (other.referInfoId != null)
				return false;
		} else if (!referInfoId.equals(other.referInfoId))
			return false;
		return true;
	}

}
