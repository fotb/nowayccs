package com.ccs.services.vo;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("issueNew") 
public class UnifiedDataDockingVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3130154428764134172L;

//	private String key;
	
	//事件名称
	private String subject;
	
	//所属区域
	private String orgName;
	
	//发生地点
	private String occurLocation;
	
	//发生时间
	private String occurDate;
	
	@XStreamAlias("issueRelatedPeoples") 
	private List<IssueRelatedPeople> issueRelatedPeoples;
	
	//涉及人数
	private String relatePeopleCount;
	
	//事件类型大类
	private String issueBigTypeName;
	
	//事件类型小类
	private String issueSmallTypeName;
	
	//事件简述
	private String issueContent;
	
	//数据来源
	private String dataOrigin;
	
	//包裹多个附件标签
	@XStreamAlias("issueAttachs") 
	private List<IssueAttach> issueAttachs;
	
	
//	public String getKey() {
//		return key;
//	}
//
//
//	public void setKey(String key) {
//		this.key = key;
//	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getOccurLocation() {
		return occurLocation;
	}


	public void setOccurLocation(String occurLocation) {
		this.occurLocation = occurLocation;
	}


	public String getOccurDate() {
		return occurDate;
	}


	public void setOccurDate(String occurDate) {
		this.occurDate = occurDate;
	}


	public List<IssueRelatedPeople> getIssueRelatedPeoples() {
		return issueRelatedPeoples;
	}


	public void setIssueRelatedPeoples(List<IssueRelatedPeople> issueRelatedPeoples) {
		this.issueRelatedPeoples = issueRelatedPeoples;
	}


	public String getRelatePeopleCount() {
		return relatePeopleCount;
	}


	public void setRelatePeopleCount(String relatePeopleCount) {
		this.relatePeopleCount = relatePeopleCount;
	}


	public String getIssueBigTypeName() {
		return issueBigTypeName;
	}


	public void setIssueBigTypeName(String issueBigTypeName) {
		this.issueBigTypeName = issueBigTypeName;
	}


	public String getIssueSmallTypeName() {
		return issueSmallTypeName;
	}


	public void setIssueSmallTypeName(String issueSmallTypeName) {
		this.issueSmallTypeName = issueSmallTypeName;
	}


	public String getIssueContent() {
		return issueContent;
	}


	public void setIssueContent(String issueContent) {
		this.issueContent = issueContent;
	}


	public String getDataOrigin() {
		return dataOrigin;
	}


	public void setDataOrigin(String dataOrigin) {
		this.dataOrigin = dataOrigin;
	}


	public List<IssueAttach> getIssueAttachs() {
		return issueAttachs;
	}


	public void setIssueAttachs(List<IssueAttach> issueAttachs) {
		this.issueAttachs = issueAttachs;
	}


	
	
	
	
	
}
