package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_APPINFO")
public class AppInfoVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4611239605947586400L;

	@Column(name = "INFORMATIONID")
	private String informationId;

	@Column(name = "ORDERSMONEY")
	private String ordersMoney;

	@Column(name = "ORDERSCOMMENTNUM")
	private String ordersCommentNum;

	@Column(name = "ORDERSCOMMENTCONTENT")
	private String ordersCommentContent;

	@Column(name = "ORDERSSERVERSNAME")
	private String ordersServiesName;

	@Column(name = "ORDERSERVERSPHONE")
	private String ordersServersPhone;

	@Column(name = "ORDERSERVERSSERVICETYPE")
	private String ordersServersServiceType;

	@Column(name = "ORDERSSERVERSCHECK")
	private String orderServersCheck;

	@Column(name = "ORDERSTYPE")
	private String ordersType;

	public String getInformationId() {
		return informationId;
	}

	public void setInformationId(String informationId) {
		this.informationId = informationId;
	}

	public String getOrdersMoney() {
		return ordersMoney;
	}

	public void setOrdersMoney(String ordersMoney) {
		this.ordersMoney = ordersMoney;
	}

	public String getOrdersCommentNum() {
		return ordersCommentNum;
	}

	public void setOrdersCommentNum(String ordersCommentNum) {
		this.ordersCommentNum = ordersCommentNum;
	}

	public String getOrdersCommentContent() {
		return ordersCommentContent;
	}

	public void setOrdersCommentContent(String ordersCommentContent) {
		this.ordersCommentContent = ordersCommentContent;
	}

	public String getOrdersServiesName() {
		return ordersServiesName;
	}

	public void setOrdersServiesName(String ordersServiesName) {
		this.ordersServiesName = ordersServiesName;
	}

	public String getOrdersServersPhone() {
		return ordersServersPhone;
	}

	public void setOrdersServersPhone(String ordersServersPhone) {
		this.ordersServersPhone = ordersServersPhone;
	}

	public String getOrdersServersServiceType() {
		return ordersServersServiceType;
	}

	public void setOrdersServersServiceType(String ordersServersServiceType) {
		this.ordersServersServiceType = ordersServersServiceType;
	}

	public String getOrderServersCheck() {
		return orderServersCheck;
	}

	public void setOrderServersCheck(String orderServersCheck) {
		this.orderServersCheck = orderServersCheck;
	}

	public String getOrdersType() {
		return ordersType;
	}

	public void setOrdersType(String ordersType) {
		this.ordersType = ordersType;
	}

}
