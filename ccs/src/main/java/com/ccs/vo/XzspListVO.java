package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_XZSP_LIST")
public class XzspListVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7940703423673686232L;

	@Column(name = "LISTCODE")
	private String listCode;

	@Column(name = "ITEMNAME")
	private String itemName;
	@Column(name = "CODE")
	private String code;

	@Column(name = "ITEMTYPE")
	private String itemType;

	@Column(name = "TARGET")
	private String target;

	@Column(name = "ACCORDING")
	private String according;

	@Column(name = "REQUIREMENT")
	private String requirement;

	@Column(name = "MATERIALS")
	private String materials;

	@Column(name = "PROCE")
	private String proce;

	@Column(name = "DEALDEPT")
	private String dealDept;

	@Column(name = "LEGALTERM")
	private String legalTerm;

	@Column(name = "PROMISEDATE")
	private String promiseDate;

	@Column(name = "CHARGESTAND")
	private String chargeStand;

	@Column(name = "CHARGEACCORDING")
	private String chargeAccording;

	@Column(name = "DEALPHONE")
	private String dealPhone;

	@Column(name = "SERVICEPHONE")
	private String servicePhone;

	@Column(name = "EASYLEVEL")
	private String easyLevel;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getAccording() {
		return according;
	}

	public void setAccording(String according) {
		this.according = according;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public String getProce() {
		return proce;
	}

	public void setProce(String proce) {
		this.proce = proce;
	}

	public String getDealDept() {
		return dealDept;
	}

	public void setDealDept(String dealDept) {
		this.dealDept = dealDept;
	}

	public String getLegalTerm() {
		return legalTerm;
	}

	public void setLegalTerm(String legalTerm) {
		this.legalTerm = legalTerm;
	}

	public String getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(String promiseDate) {
		this.promiseDate = promiseDate;
	}

	public String getChargeStand() {
		return chargeStand;
	}

	public void setChargeStand(String chargeStand) {
		this.chargeStand = chargeStand;
	}

	public String getChargeAccording() {
		return chargeAccording;
	}

	public void setChargeAccording(String chargeAccording) {
		this.chargeAccording = chargeAccording;
	}

	public String getDealPhone() {
		return dealPhone;
	}

	public void setDealPhone(String dealPhone) {
		this.dealPhone = dealPhone;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getEasyLevel() {
		return easyLevel;
	}

	public void setEasyLevel(String easyLevel) {
		this.easyLevel = easyLevel;
	}

	public String getListCode() {
		return listCode;
	}

	public void setListCode(String listCode) {
		this.listCode = listCode;
	}

}
