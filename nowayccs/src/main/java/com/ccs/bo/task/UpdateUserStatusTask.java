package com.ccs.bo.task;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccs.bo.IUserStatusBO;


@Component("updateUserStatusTask")  
public class UpdateUserStatusTask {
	private static final Logger logger = Logger.getLogger(UpdateUserStatusTask.class);
	
	@Autowired
	private IUserStatusBO userStatusBO;
	
	//@Scheduled(cron="0 0/1 * * * ?")
	public void doJob() {
		try {
			//System.out.println("update user status-------------");
			//每隔5分钟检查用户状态，如果已经超过5分钟没有状态，则告诉当前用户已经退出。
			userStatusBO.updateTimeoutUserStatus(new Date());
		} catch (Exception e) {
			logger.error("UpdateUserStatusTask: " + e.getMessage());
		}
		
	}
	
}
