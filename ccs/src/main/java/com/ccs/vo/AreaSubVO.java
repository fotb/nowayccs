package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_AREA_SUB")
public class AreaSubVO implements Serializable {

	private static final long serialVersionUID = -8238513040674772311L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "AREASUBID")
	private String areaSubId;

	@Column(name = "AREAID")
	private String areaId;

	@Column(name = "NAME")
	private String name;

	public String getAreaSubId() {
		return areaSubId;
	}

	public void setAreaSubId(String areaSubId) {
		this.areaSubId = areaSubId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((areaId == null) ? 0 : areaId.hashCode());
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
		AreaSubVO other = (AreaSubVO) obj;
		if (areaId == null) {
			if (other.areaId != null)
				return false;
		} else if (!areaId.equals(other.areaId))
			return false;
		return true;
	}

}
