package com.ccs.bo.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccs.bo.IUserAppInfoBO;


@Component  
public class UpdateUserAppInfoJob implements Job{
	private static final Logger logger = Logger.getLogger(UpdateUserAppInfoJob.class);
	
	@Autowired
	private IUserAppInfoBO	userAppInfoBO;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
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
