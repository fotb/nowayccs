package com.ccs.web.domain;

import java.io.Serializable;

public class LPSRowBean implements Serializable {
	
	private static final long serialVersionUID = -3126875119232526411L;
	
	private String id;
	
	private String name;
	
	private String phone;
	
	private String remark;
	
	private String category;
	
	private String iconCls;
	
	public static final String ICON_OK = "icon-ok";
	
	private String state;
	
	public static final String STATE_CLOSED = "closed";
	
	private String _parentId;

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
