package com.ccs.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IQuartzBO;
import com.ccs.bo.quartz.QuartzManager;
import com.ccs.vo.QuartzJobVO;

@Controller
@RequestMapping("/quartz.do")
public class QuartzController {
	private static final Logger logger = Logger.getLogger(QuartzController.class);

	@Resource
	private QuartzManager quartzManager;

	@Autowired
	private IQuartzBO quartzBO;

	@RequestMapping
	public String list(HttpSession session, ModelMap model) {
		return "quartz/list";
	}

	@RequestMapping(params = "action=all", method = RequestMethod.POST)
	@ResponseBody
	public List<QuartzJobVO> getAll(HttpSession session) throws Exception {
		QuartzJobVO vo = quartzBO.findById("11");

		return quartzBO.findAll();
	}

	@RequestMapping(params = "action=start")
	@ResponseBody
	public String start(@RequestParam("pid") String pid, HttpSession session) throws Exception {
		// Date quartzTime = new Date();
		// logger.info("===========开始执行调度=========时间为 " + quartzTime);
		// String cronStr = quartzManager.transCron(quartzTime);
		QuartzJobVO vo = quartzBO.findById(pid);
		logger.info("======cron表达式========" + vo.getCron());
		quartzManager.addJob(vo.getJobName(), vo.getJobGroupName(), vo.getTriggerName(), vo.getTriggerGroupName(),
				Class.forName(vo.getJobClass()), vo.getCron());
		return "";
	}
}
