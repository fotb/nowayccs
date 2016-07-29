package com.ccs.services.vo;

import com.ccs.util.Constants;

public class UploadUtils {
	
	//获取求助方式
	public static String getQuerytype(String helpMode) {
		String queryType = "";
		switch (helpMode) {
		case "1":  //来电
			queryType = "01";
			break;
		case "2":  //来信
			queryType = "05";
			break;
		case "3": //来访
			queryType = "02";
			break;
			
		case "4": //短信
			queryType = "03";
			break;
			
		case "5":   //微信
			queryType = "07";
			break;
		default:
			queryType = "";
			break;
		}
		return queryType;
	}

	
	public static String getTransType(String helpType) {
		String transType = "";
		switch (helpType) {
		case Constants.INFOMATION_HELPTYPE_REFER: //资讯类服务
			transType = "01";
			break;
		case Constants.INFOMATION_HELPTYPE_LIFE: //生活服务类
			transType = "02";
			break;
		case Constants.INFOMATION_HELPTYPE_AFFAIR: //事务服务类
			transType = "03";
			break;
		case Constants.INFOMATION_HELPTYPE_FERTILITY: //生产力服务
			transType = "04";
			break;
		case Constants.INFOMATION_HELPTYPE_POWER: //电力服务类
			transType = "02";
			break;
		default:
			transType = "04";
			break;
		}
		
		return transType;
	}
	
	public static String getHelpState(String status) {
		String helpState = "";
		
		switch (status) {
		case Constants.SYS_INFOMATION_STATES_DB:
			helpState = "2";
			break;
		case Constants.SYS_INFOMATION_STATES_CLZ:
			helpState = "3";
			break;
		case Constants.SYS_INFOMATION_STATES_YJA:
			helpState = "1";
			break;
		default:
			helpState = "3";
			break;
		}
		return helpState;
	}
}
