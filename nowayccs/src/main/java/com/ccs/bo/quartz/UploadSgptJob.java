package com.ccs.bo.quartz;

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ccs.bo.IEventBO;
import com.ccs.vo.EventVO;

@Component
public class UploadSgptJob implements Job {
	private static final Logger logger = Logger.getLogger(UploadSgptJob.class);
	
	@Autowired
	private IEventBO eventBO;
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			
			List<EventVO> eVOList = eventBO.getUnUploadEvent();
			for (EventVO eventVO : eVOList) {
				eventBO.pushEvent(eventVO);
			}
		} catch (Exception e) {
			logger.error("uploadSgptJob error: " + e.getMessage());
			throw new JobExecutionException(e);
		}
	}
	
}
