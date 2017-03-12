package com.ccs.report.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_HIST_COUNT")
public class HistCountVO extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = -5645022856076628986L;
	@Column(name = "YEAR")
	private String year;

	@Column(name = "COUNT")
	private String count;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}