package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_LONELYMANINFO")
public class LonelyManInfoVO implements Serializable {

	private static final long serialVersionUID = 7313450337866378044L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "MANID")
	private String manId;

	@Column(name = "MANNAME")
	private String manName;

	@Column(name = "MANSEX")
	private String manSex;

	@Column(name = "BIRTHDAY")
	private String birthday;

	@Column(name = "IDCARDNO")
	private String idCardNo;

	@Column(name = "AREA")
	private String area;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "TELPHONE")
	private String telphone;

	@Column(name = "FAMILYTYPE")
	private String familyType;

	@Column(name = "FAMILYINFO")
	private String familyInfo;

	public String getManId() {
		return manId;
	}

	public void setManId(String manId) {
		this.manId = manId;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public String getManSex() {
		return manSex;
	}

	public void setManSex(String manSex) {
		this.manSex = manSex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getFamilyType() {
		return familyType;
	}

	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}

	public String getFamilyInfo() {
		return familyInfo;
	}

	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manId == null) ? 0 : manId.hashCode());
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
		LonelyManInfoVO other = (LonelyManInfoVO) obj;
		if (manId == null) {
			if (other.manId != null)
				return false;
		} else if (!manId.equals(other.manId))
			return false;
		return true;
	}
}
