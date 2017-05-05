package com.ccs.web.domain;

import java.io.Serializable;

public class XzspListDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1624451380677185787L;

	private String pid;

	private String listCode;

	private String itemName;
	private String code;

	private String itemType;

	private String target;

	private String according;

	private String requirement;

	private String materials;

	private String proce;

	private String dealDept;

	private String legalTerm;

	private String promiseDate;

	private String chargeStand;

	private String chargeAccording;

	private String dealPhone;

	private String servicePhone;

	private String easyLevel;
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

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
