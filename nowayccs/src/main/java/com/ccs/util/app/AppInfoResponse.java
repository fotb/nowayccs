package com.ccs.util.app;
public class AppInfoResponse {
	private static final String SUCCESS = "0";

	private String code;
	private String message;
	private Meta data;

	public AppInfoResponse() {
		super();
	}

	public Meta getData() {
		return data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Meta data) {
		this.data = data;
	}


}
