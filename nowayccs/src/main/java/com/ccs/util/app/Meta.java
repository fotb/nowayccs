package com.ccs.util.app;
import java.io.Serializable;
import java.util.List;

import com.ccs.web.domain.AppInfoBean;

public class Meta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 791199870331873144L;
	private List<AppInfoBean> list;

	public Meta() {
		super();
	}

	public List<AppInfoBean> getList() {
		return list;
	}

	public void setList(List<AppInfoBean> list) {
		this.list = list;
	}
}
