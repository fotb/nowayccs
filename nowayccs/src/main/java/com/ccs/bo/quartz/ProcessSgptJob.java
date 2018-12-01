package com.ccs.bo.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccs.bo.IEventBO;

@Component
public class ProcessSgptJob implements Job {
	private static final Logger logger = Logger.getLogger(ProcessSgptJob.class);
	
	@Autowired
	private IEventBO eventBO;
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			eventBO.processEvent();
		} catch (Exception e) {
			logger.error("ProcessSgptJob error: " + e.getMessage());
			throw new JobExecutionException(e);
		}
	}
}
