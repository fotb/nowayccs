package com.ccs.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "HJ_ELEVHELPINFO")
public class ElevHelpInfoVO extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8579238585835070122L;

	@Column(name = "REPORTINGTIME")
	private String reportintTime;

	@Column(name = "REPORTER")
	private String reporter;
	
	@Column(name = "REPORTPHONE")
	private String reportPhone;
	
	@Column(name = "HELPDEPT")
	private String helpDept;
	
	@Column(name = "DISPATCHTIME")
	private String dispatchTime;
	
	@Column(name = "STARTTIME")
	private String startTime;
	
	@Column(name = "ARRIVETIME")
	private String arriveTime;
	
	@Column(name = "FINISHTIME")
	private String finishTime;

	@Column(name = "TRAPPPEDPERSON")
	private String trapppedPersion;
	
	@Column(name = "CASUALTY")
	private String casualty;
	
	@Column(name = "INJURIES")
	private String injuries;
	
	@Column(name = "DEATHTOLL")
	private String deathToll;
	
	@Column(name = "REASON")
	private String reason;
	
	@Column(name = "DEALRESULT")
	private String dealResult;
	
	@Column(name = "DUTYRESULT")
	private String dutyResult;
	
	public String getReportintTime() {
		return reportintTime;
	}
	public void setReportintTime(String reportintTime) {
		this.reportintTime = reportintTime;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getReportPhone() {
		return reportPhone;
	}
	public void setReportPhone(String reportPhone) {
		this.reportPhone = reportPhone;
	}
	public String getHelpDept() {
		return helpDept;
	}
	public void setHelpDept(String helpDept) {
		this.helpDept = helpDept;
	}
	public String getDispatchTime() {
		return dispatchTime;
	}
	public void setDispatchTime(String dispatchTime) {
		this.dispatchTime = dispatchTime;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getTrapppedPersion() {
		return trapppedPersion;
	}
	public void setTrapppedPersion(String trapppedPersion) {
		this.trapppedPersion = trapppedPersion;
	}
	public String getCasualty() {
		return casualty;
	}
	public void setCasualty(String casualty) {
		this.casualty = casualty;
	}
	public String getInjuries() {
		return injuries;
	}
	public void setInjuries(String injuries) {
		this.injuries = injuries;
	}
	public String getDeathToll() {
		return deathToll;
	}
	public void setDeathToll(String deathToll) {
		this.deathToll = deathToll;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDealResult() {
		return dealResult;
	}
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	public String getDutyResult() {
		return dutyResult;
	}
	public void setDutyResult(String dutyResult) {
		this.dutyResult = dutyResult;
	}
}
