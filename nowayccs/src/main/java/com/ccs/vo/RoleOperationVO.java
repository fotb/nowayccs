package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "HJ_ROLEOPERATION")
public class RoleOperationVO implements Serializable {

	private static final long serialVersionUID = -2659778948850520689L;

	@EmbeddedId
	private RoleOperationIdVO id;

	public RoleOperationIdVO getId() {
		return id;
	}

	public void setId(RoleOperationIdVO id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		RoleOperationVO other = (RoleOperationVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
