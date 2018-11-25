package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_PARTYMEMBERFORLONELY")
public class PartyMemberForLonelyVO implements Serializable {

	private static final long serialVersionUID = 5087028034393603979L;
	
	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "MEMBERID")
	private String memberId;
	
	@Column(name = "MEMBERNAME")
	private String memberName;
	
	@Column(name = "MEMBERSEX")
	private String memberSex;
	
	@Column(name = "BIRTHDAY")
	private String birthday;
	
	@Column(name = "WORKDEPT")
	private String workDept;
	
	@Column(name = "LINKPHONE")
	private String linkPhone;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "SPECIALTY")
	private String specialty;
	
	@Column(name  = "LONELYMANID")
	private String lonelyManId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWorkDept() {
		return workDept;
	}

	public void setWorkDept(String workDept) {
		this.workDept = workDept;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getLonelyManId() {
		return lonelyManId;
	}

	public void setLonelyManId(String lonelyManId) {
		this.lonelyManId = lonelyManId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((memberId == null) ? 0 : memberId.hashCode());
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
		PartyMemberForLonelyVO other = (PartyMemberForLonelyVO) obj;
		if (memberId == null) {
			if (other.memberId != null)
				return false;
		} else if (!memberId.equals(other.memberId))
			return false;
		return true;
	}
}
