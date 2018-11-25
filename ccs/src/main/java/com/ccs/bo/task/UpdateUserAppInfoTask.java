package com.ccs.bo.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ccs.bo.IUserAppInfoBO;


@Component("updateUserAppInfoTask")  
public class UpdateUserAppInfoTask {
	private static final Logger logger = Logger.getLogger(UpdateUserAppInfoTask.class);
	
	@Autowired
	private IUserAppInfoBO	userAppInfoBO;
	
	//@Scheduled(cron="0 0/1 * * * ?")
	public void doJob() {
		try {
			logger.info("start update user app info---------");
			userAppInfoBO.updateUserAppInfo();
			logger.info("end update user app info---------");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UpdateUserAppInfoTask: " + e.getMessage());
		}
		
	}
	
}
