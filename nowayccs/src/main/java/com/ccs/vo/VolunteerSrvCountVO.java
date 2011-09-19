package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "VOLUNTEER_SRV_COUNT_VIEW")
public class VolunteerSrvCountVO implements Serializable {

	private static final long serialVersionUID = 656691360832279130L;

	@Id
	@Column(name = "VOLUNTEERID")
	private String volunteerId;
	
	@Column(name = "SRVCOUNT")
	private int count;

	public String getVolunteerId() {
		return volunteerId;
	}

	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
		VolunteerSrvCountVO other = (VolunteerSrvCountVO) obj;
		if (volunteerId == null) {
			if (other.volunteerId != null)
				return false;
		} else if (!volunteerId.equals(other.volunteerId))
			return false;
		return true;
	}
	
}
