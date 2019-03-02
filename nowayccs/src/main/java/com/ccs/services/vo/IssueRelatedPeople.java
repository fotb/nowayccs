package com.ccs.services.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("issueRelatedPeople") 
public class IssueRelatedPeople implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4416577311280490749L;
	
	//主要当事人姓名
	private String issueRelatedPeopleName;
	
	//主要当事人电话
	private String issueRelatedPeopleTelephone;

	public String getIssueRelatedPeopleName() {
		return issueRelatedPeopleName;
	}

	public void setIssueRelatedPeopleName(String issueRelatedPeopleName) {
		this.issueRelatedPeopleName = issueRelatedPeopleName;
	}

	public String getIssueRelatedPeopleTelephone() {
		return issueRelatedPeopleTelephone;
	}

	public void setIssueRelatedPeopleTelephone(String issueRelatedPeopleTelephone) {
		this.issueRelatedPeopleTelephone = issueRelatedPeopleTelephone;
	}
}