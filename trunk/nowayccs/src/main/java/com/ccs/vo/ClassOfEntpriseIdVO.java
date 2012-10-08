package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ClassOfEntpriseIdVO implements Serializable {

	private static final long serialVersionUID = 6543414276908503490L;

	@Column(name = "ENTPRISEID")
	private String entpriseId;
	
	@Column(name = "ENTCLASSID")
	private String category;

	public final String getEntpriseId() {
		return entpriseId;
	}

	public final void setEntpriseId(String entpriseId) {
		this.entpriseId = entpriseId;
	}

	public final String getCategory() {
		return category;
	}

	public final void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
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
		ClassOfEntpriseIdVO other = (ClassOfEntpriseIdVO) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (entpriseId == null) {
			if (other.entpriseId != null)
				return false;
		} else if (!entpriseId.equals(other.entpriseId))
			return false;
		return true;
	}
}
