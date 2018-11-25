package com.ccs.vo;

import java.io.Serializable;

public class EntSrvCountVO implements Serializable {
	private static final long serialVersionUID = 944112604499451872L;
	
	private String entpriseId;

	private long count;

	public EntSrvCountVO(String entpriseId, long count) {
		super();
		this.entpriseId = entpriseId;
		this.count = count;
	}

	public String getEntpriseId() {
		return entpriseId;
	}

	public void setEntpriseId(String entpriseId) {
		this.entpriseId = entpriseId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
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
