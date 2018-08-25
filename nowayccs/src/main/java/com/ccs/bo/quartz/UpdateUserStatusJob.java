package com.ccs.bo.quartz;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccs.bo.IUserStatusBO;

@Component
public class UpdateUserStatusJob implements Job {
	private static final Logger logger = Logger.getLogger(UpdateUserStatusJob.class);
	
	@Autowired
	private IUserStatusBO userStatusBO;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			System.out.println("update user status-------------");
			//每隔5分钟检查用户状态，如果已经超过5分钟没有状态，则告诉当前用户已经退出。
			userStatusBO.updateTimeoutUserStatus(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("UpdateUserStatusTask: " + e.getMessage());
		}
	}

}
