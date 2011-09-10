package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @hibernate.class table="HJ_Volunteer"
 * 
 */
@Entity
@Table(name = "HJ_VOLUNTEER")
public class VolunteerVO implements Serializable {

	private static final long serialVersionUID = -33814107700105659L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "VOLUNTEERID")
	private String volunteerId;

	@Column(name = "VOLUNTEERNO")
	private String volunteerNo;

	@Column(name = "VOLUNTEERNAME")
	private String volunteerName;

	@Column(name = "SEX")
	private String sex;

	@Column(name = "SERVICENAME")
	private String serviceName;

	@Column(name = "SERVICETIME")
	private String serviceTime;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "LINKTEL")
	private String linkTel;

	@Column(name = "LINKMOBILE")
	private String linkMobile;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "AREAID")
	private String areaId;

	@Column(name = "AREASUBID")
	private String areaSubId;

	@Column(name = "SERVICETYPE")
	private String serviceType;

	@Column(name = "STATUS")
	private String status;

	public String getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}

	public String getVolunteerNo() {
		return volunteerNo;
	}

	public void setVolunteerNo(String volunteerNo) {
		this.volunteerNo = volunteerNo;
	}

	public String getVolunteerName() {
		return volunteerName;
	}

	public void setVolunteerName(String volunteerName) {
		this.volunteerName = volunteerName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLinkTel() {
		return linkTel;
	}

	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaSubId() {
		return areaSubId;
	}

	public void setAreaSubId(String areaSubId) {
		this.areaSubId = areaSubId;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((volunteerId == null) ? 0 : volunteerId.hashCode());
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
		VolunteerVO other = (VolunteerVO) obj;
		if (volunteerId == null) {
			if (other.volunteerId != null)
				return false;
		} else if (!volunteerId.equals(other.volunteerId))
			return false;
		return true;
	}

}
