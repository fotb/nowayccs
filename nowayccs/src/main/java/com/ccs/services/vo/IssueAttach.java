package com.ccs.services.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@XStreamAlias("issueAttach")
public class IssueAttach implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7834749709499834921L;
	
	private String fileName;
	
	private String fileUrl;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
}