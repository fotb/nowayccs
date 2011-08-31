package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleOperationIdVO implements Serializable {

	private static final long serialVersionUID = 9221921023328833374L;

	@Column(name = "ROLEID")
	private String roleId;

	@Column(name = "OPERATIONID")
	private String operationid;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOperationid() {
		return operationid;
	}

	public void setOperationid(String operationid) {
		this.operationid = operationid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((operationid == null) ? 0 : operationid.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		RoleOperationIdVO other = (RoleOperationIdVO) obj;
		if (operationid == null) {
			if (other.operationid != null)
				return false;
		} else if (!operationid.equals(other.operationid))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

}
