package com.ccs.web.interceptor;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ccs.bo.IQuartzBO;
import com.ccs.bo.quartz.QuartzManager;
import com.ccs.vo.QuartzJobVO;

public class QuartzJobListener implements ServletContextListener {
	private WebApplicationContext springContext;
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/***处理获取数据库的job表，然后遍历循环每个加到job中 ***/
		try {
			springContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
	        QuartzManager quartzManager = (QuartzManager) springContext.getBean("quartzManager");
	        
	        IQuartzBO quartzBO = (IQuartzBO) springContext.getBean("quartzBO");
	        List<QuartzJobVO> list = quartzBO.findAll();
	        
	        for (QuartzJobVO vo : list) {
	        	if("1".equals(vo.getStatus())) {
	        		quartzManager.addJob(vo.getJobName(), vo.getJobGroupName(), vo.getTriggerName(), vo.getTriggerGroupName(),
	        				Class.forName(vo.getJobClass()), vo.getCron());
	        	}
	        }
	        System.out.println("QuartzJobListener 启动了");
        } catch (Exception e) {
            e.printStackTrace();
        } 
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
