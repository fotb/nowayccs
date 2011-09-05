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
}
