package com.ccs.web.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Dict implements Serializable {

	private static final long serialVersionUID = -2876275446627780479L;

	private String dictId;

	@NotNull
	private String dictType;

	@NotNull
	@Size(min = 1, max = 25)
	private String sortIndex;

	@NotNull
	private String value;

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

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

}
