package com.ccs.util;

import java.util.Map;
import java.util.TreeMap;

public class CountHelpTypeBean implements java.io.Serializable {

	/* 求助类型： */
	public static final String INFOMATION_HELPTYPE_REFER = "1"; // 1咨询服务类
	public static final String INFOMATION_HELPTYPE_LIFE = "2"; // 2生活服务类
	public static final String INFOMATION_HELPTYPE_AFFAIR = "3"; // 3事务服务类
	public static final String INFOMATION_HELPTYPE_FERTILITY = "4"; // 4生产力服务
	public static final String INFOMATION_HELPTYPE_POWER = "5"; // 5电力服务类
	public static final Map<String, String> INFOMATION_HELPTYPE_HASHMAP = new TreeMap<String, String>();

	static {
		INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(INFOMATION_HELPTYPE_LIFE), "生活服务类");
		INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(INFOMATION_HELPTYPE_REFER), "咨询服务类");
		INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(INFOMATION_HELPTYPE_AFFAIR), "事务服务类");
		INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(INFOMATION_HELPTYPE_FERTILITY), "生产力服务");
		INFOMATION_HELPTYPE_HASHMAP.put(String.valueOf(INFOMATION_HELPTYPE_POWER), "电力服务类");
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7462465266078238211L;

	private String helpType;

	private int count;

	public String getHelpType() {
		return INFOMATION_HELPTYPE_HASHMAP.get(helpType);
	}

	public void setHelpType(String helpType) {
		this.helpType = helpType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
