package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENT_SRV_COUNT_VIEW")
public class EntSrvCountVO implements Serializable {
	private static final long serialVersionUID = 944112604499451872L;
	
	@Id
	@Column(name = "ENTPRISEID")
	private String entpriseId;

	@Column(name = "ENTCOUNT")
	private String count;

	public String getEntpriseId() {
		return entpriseId;
	}

	public void setEntpriseId(String entpriseId) {
		this.entpriseId = entpriseId;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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
		EntSrvCountVO other = (EntSrvCountVO) obj;
		if (entpriseId == null) {
			if (other.entpriseId != null)
				return false;
		} else if (!entpriseId.equals(other.entpriseId))
			return false;
		return true;
	}

}
