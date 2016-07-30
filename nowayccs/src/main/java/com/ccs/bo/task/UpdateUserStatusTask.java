package com.ccs.bo.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ccs.bo.IUserStatusBO;


@Component("updateUserStatusTask")  
public class UpdateUserStatusTask {
	private static final Logger logger = Logger.getLogger(UpdateUserStatusTask.class);
	
	@Autowired
	private IUserStatusBO userStatusBO;
	
	@Scheduled(cron="0 0/5 * * * ?")
	public void doJob() {
		try {
			System.out.println("update user status-------------");
			
			userStatusBO.updateTimeoutUserStatus(new Date());
		} catch (Exception e) {
			logger.error("UpdateUserStatusTask: " + e.getMessage());
		}
		
	}
	
}
