package com.ccs.services.vo;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ThInfo") 
public class CallTelUploadVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4086963621420605769L;

	private String seatip;
	private String seatname;

	private String calltel;
	private String dealtime;
	private String dealstatus;

	public String getSeatip() {
		return seatip;
	}

	public void setSeatip(String seatip) {
		this.seatip = seatip;
	}

	public String getSeatname() {
		return seatname;
	}

	public void setSeatname(String seatname) {
		this.seatname = seatname;
	}

	public String getCalltel() {
		return calltel;
	}

	public void setCalltel(String calltel) {
		this.calltel = calltel;
	}

	public String getDealtime() {
		return dealtime;
	}

	public void setDealtime(String dealtime) {
		this.dealtime = dealtime;
	}

	public String getDealstatus() {
		return dealstatus;
	}

	public void setDealstatus(String dealstatus) {
		this.dealstatus = dealstatus;
	}

}
