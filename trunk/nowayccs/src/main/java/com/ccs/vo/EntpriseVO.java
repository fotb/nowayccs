package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_ENTPRISE")
public class EntpriseVO implements Serializable {

	private static final long serialVersionUID = 129020284010932524L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "ENTPRISEID")
	private String entpriseId;
	@Column(name = "ENTPRISENAME")
	private String entpriseName;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "LAWDEPUTY")
	private String lawDeputy;

	@Column(name = "REGMONEY")
	private String regMoney;

	@Column(name = "SERVICETIME")
	private String serviceTime;

	@Column(name = "LINKTEL")
	private String linkTel;

	@Column(name = "SERVICETEL")
	private String serviceTel;

	@Column(name = "MEMBERSIGN")
	private String memberSign;

	@Column(name = "REGISTERCODE")
	private String registerCode;

	@Column(name = "TAXCODE")
	private String taxCode;

	@Column(name = "DEALRANGE")
	private String dealRange;

	@Column(name = "LINKNAME")
	private String linkName;

	@Column(name = "ENTPRISENO")
	private String entpriseNo;

	@Column(name = "SERVICESTYPE")
	private String servicesType;

	@Column(name = "STATUS")
	private String status;

	public final String getEntpriseId() {
		return entpriseId;
	}

	public final void setEntpriseId(String entpriseId) {
		this.entpriseId = entpriseId;
	}

	public final String getEntpriseName() {
		return entpriseName;
	}

	public final void setEntpriseName(String entpriseName) {
		this.entpriseName = entpriseName;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final String getLawDeputy() {
		return lawDeputy;
	}

	public final void setLawDeputy(String lawDeputy) {
		this.lawDeputy = lawDeputy;
	}

	public final String getRegMoney() {
		return regMoney;
	}

	public final void setRegMoney(String regMoney) {
		this.regMoney = regMoney;
	}

	public final String getServiceTime() {
		return serviceTime;
	}

	public final void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public final String getLinkTel() {
		return linkTel;
	}

	public final void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public final String getServiceTel() {
		return serviceTel;
	}

	public final void setServiceTel(String serviceTel) {
		this.serviceTel = serviceTel;
	}

	public final String getMemberSign() {
		return memberSign;
	}

	public final void setMemberSign(String memberSign) {
		this.memberSign = memberSign;
	}

	public final String getRegisterCode() {
		return registerCode;
	}

	public final void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public final String getTaxCode() {
		return taxCode;
	}

	public final void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public final String getDealRange() {
		return dealRange;
	}

	public final void setDealRange(String dealRange) {
		this.dealRange = dealRange;
	}

	public final String getLinkName() {
		return linkName;
	}

	public final void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public final String getEntpriseNo() {
		return entpriseNo;
	}

	public final void setEntpriseNo(String entpriseNo) {
		this.entpriseNo = entpriseNo;
	}

	public final String getServicesType() {
		return servicesType;
	}

	public final void setServicesType(String servicesType) {
		this.servicesType = servicesType;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((entpriseId == null) ? 0 : entpriseId.hashCode());
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
		EntpriseVO other = (EntpriseVO) obj;
		if (entpriseId == null) {
			if (other.entpriseId != null)
				return false;
		} else if (!entpriseId.equals(other.entpriseId))
			return false;
		return true;
	}

}
