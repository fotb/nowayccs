package com.ccs.bo.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ccs.services.client.ZhServiceConstants;
import com.ccs.services.client.ZhServiceSoapProxy;
import com.ccs.util.DateUtil;

@Component("loginTask")  
public class ZhServicesLoginTask {
	
	private static final Logger LOG = Logger.getLogger(ZhServicesLoginTask.class);
	

	//@Scheduled(cron="0 0/20 * * * ?")
	public void doJob() {
		
		try {
			ZhServiceSoapProxy proxy = new ZhServiceSoapProxy();
			String returnCode = proxy.login(ZhServiceConstants.USER, ZhServiceConstants.PW);
			if(ZhServiceConstants.LOGIN_STATUS_0.equals(returnCode)) {
				LOG.warn(DateUtil.format(DateUtil.getNowDate(), "yyyyMMdd HH:mm:ss") + " -- Success to Login ZhServices...............");
			} else {
				LOG.warn(DateUtil.format(DateUtil.getNowDate(), "yyyyMMdd HH:mm:ss") + " --fail to Login ZhServices whit return code: " + returnCode);
			}
		} catch (Exception e) {
			LOG.error("Log ZhServices error: " + e.getMessage());
		}
		
	}
}
