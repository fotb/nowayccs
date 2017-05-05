package com.ccs.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EasyUiTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8675174653281438145L;
	
	private String id;
	
	private String text;
	
	private String state;
	
	private boolean isChecked;
	
	private List<EasyUiTree> children = new ArrayList<EasyUiTree>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public List<EasyUiTree> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUiTree> children) {
		this.children = children;
	}

}
