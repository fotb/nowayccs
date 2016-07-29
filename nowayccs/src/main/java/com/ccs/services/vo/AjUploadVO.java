package com.ccs.services.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ThInfo") 
public class AjUploadVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5278330621762600232L;

	private String originalid;
	private String querytype;
	private String calltel;
	private String name;
	private String sex;
	private String otherlink;
	private String transactiontype;
	private String transactionlclass;
	private String transactionscalss;
	private String helptime;
	private String helpcontent;
	private String helptitle;
	private String Replycontent;
	private String replytime;
	private String helpstate;
	private String entername;
	private String servqual;
	private String enterlevel;
	private String hostdepart;
	private String helpbuild;
	private String seatname;
	private String seatip;

	public String getOriginalid() {
		return originalid;
	}

	public void setOriginalid(String originalid) {
		this.originalid = originalid;
	}

	public String getQuerytype() {
		return querytype;
	}

	public void setQuerytype(String querytype) {
		this.querytype = querytype;
	}

	public String getCalltel() {
		return calltel;
	}

	public void setCalltel(String calltel) {
		this.calltel = calltel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOtherlink() {
		return otherlink;
	}

	public void setOtherlink(String otherlink) {
		this.otherlink = otherlink;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public String getTransactionlclass() {
		return transactionlclass;
	}

	public void setTransactionlclass(String transactionlclass) {
		this.transactionlclass = transactionlclass;
	}

	public String getTransactionscalss() {
		return transactionscalss;
	}

	public void setTransactionscalss(String transactionscalss) {
		this.transactionscalss = transactionscalss;
	}

	public String getHelptime() {
		return helptime;
	}

	public void setHelptime(String helptime) {
		this.helptime = helptime;
	}

	public String getHelpcontent() {
		return helpcontent;
	}

	public void setHelpcontent(String helpcontent) {
		this.helpcontent = helpcontent;
	}

	public String getHelptitle() {
		return helptitle;
	}

	public void setHelptitle(String helptitle) {
		this.helptitle = helptitle;
	}

	public String getReplycontent() {
		return Replycontent;
	}

	public void setReplycontent(String replycontent) {
		Replycontent = replycontent;
	}

	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	public String getHelpstate() {
		return helpstate;
	}

	public void setHelpstate(String helpstate) {
		this.helpstate = helpstate;
	}

	public String getEntername() {
		return entername;
	}

	public void setEntername(String entername) {
		this.entername = entername;
	}

	public String getServqual() {
		return servqual;
	}

	public void setServqual(String servqual) {
		this.servqual = servqual;
	}

	public String getEnterlevel() {
		return enterlevel;
	}

	public void setEnterlevel(String enterlevel) {
		this.enterlevel = enterlevel;
	}

	public String getHostdepart() {
		return hostdepart;
	}

	public void setHostdepart(String hostdepart) {
		this.hostdepart = hostdepart;
	}

	public String getHelpbuild() {
		return helpbuild;
	}

	public void setHelpbuild(String helpbuild) {
		this.helpbuild = helpbuild;
	}

	public String getSeatname() {
		return seatname;
	}

	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}

	public String getSeatip() {
		return seatip;
	}

	public void setSeatip(String seatip) {
		this.seatip = seatip;
	}

}
