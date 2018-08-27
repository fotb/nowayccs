package com.ccs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ccs.util.Response;
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
		List<Map<String, Object>> runJobList = quartzManager.queryAllJob();
		Map<String, String> map = new HashMap<String, String>();
		for (Map<String, Object> jobMap : runJobList) {
			map.put(jobMap.get("jobName").toString(), jobMap.get("jobTime").toString());
		}

		List<QuartzJobVO> volist = quartzBO.findAll();
		for (QuartzJobVO quartzJobVO : volist) {
			if (map.containsKey(quartzJobVO.getJobName())) {
				quartzJobVO.setStatus("1");
			} else {
				quartzJobVO.setStatus("0");
			}
		}
		return volist;
	}

	@RequestMapping(params = "action=start")
	@ResponseBody
	public Response start(@RequestParam("pid") String pid, HttpSession session) throws Exception {
		Response res = new Response();
		try {
			QuartzJobVO vo = quartzBO.findById(pid);
			logger.info("======cron表达式========" + vo.getCron());
			quartzManager.addJob(vo.getJobName(), vo.getJobGroupName(), vo.getTriggerName(), vo.getTriggerGroupName(),
					Class.forName(vo.getJobClass()), vo.getCron());
			res.success();
		} catch (Exception e) {
			res.failure("error!");
		}
		return res;
	}
	
	@RequestMapping(params = "action=stop")
	@ResponseBody
	public Response stop(@RequestParam("pid") String pid, HttpSession session) throws Exception {
		Response res = new Response();
		try {
			QuartzJobVO vo = quartzBO.findById(pid);
			logger.info("======cron表达式========" + vo.getCron());
			quartzManager.removeJob(vo.getJobName(), vo.getJobGroupName(), vo.getTriggerName(), vo.getTriggerGroupName());
			res.success();
		} catch (Exception e) {
			res.failure("error!");
		}
		return res;
	}
}
