package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_DICT")
public class DictVO implements Serializable {

	private static final long serialVersionUID = -2938939457541288503L;

	@Id
	@GeneratedValue(generator = "hibernate-uuid")
	@org.hibernate.annotations.GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
	@Column(name = "DICTID")
	private String dictId;

	@Column(name = "DICTTYPE")
	private String dictType;

	@Column(name = "SORTINDEX")
	private String sortIndex;

	@Column(name = "VAL")
	private String value;

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	
	public String getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dictId == null) ? 0 : dictId.hashCode());
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
		DictVO other = (DictVO) obj;
		if (dictId == null) {
			if (other.dictId != null)
				return false;
		} else if (!dictId.equals(other.dictId))
			return false;
		return true;
	}

}
