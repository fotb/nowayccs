package com.ccs.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_INFO_DATE_COUNT")
public class InfoDateCountVO extends BaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2030329890179648290L;

	@Column(name = "SDATE")
	private String date;

	@Column(name = "COUNT")
	private String count;


	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}