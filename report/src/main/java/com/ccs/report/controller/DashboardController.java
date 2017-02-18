package com.ccs.report.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.report.bo.IReportBO;
import com.ccs.util.AgentStatusBean;
import com.ccs.util.CountHelpTypeBean;
import com.ccs.util.DateUtil;
import com.ccs.util.InfoAreaCountBean;
import com.ccs.util.YearCountBean;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/dashboard.do")
public class DashboardController {
	
	private static final Logger logger = Logger.getLogger(DashboardController.class);
	@Autowired
	private IReportBO reportBO;
	
	@RequestMapping
	public String init(HttpSession session, ModelMap model) throws Exception {
		return "dashboard";
	}

	
	@RequestMapping(params = "action=monthChart")
	public @ResponseBody String MonthChart(HttpSession session, ModelMap model) throws Exception {
		Date lastYear = DateUtil.addYear(new Date(), -1);
		List<YearCountBean> list = reportBO.queryInfoCountByMonth(DateUtil.format(lastYear, "yyyyMM"), DateUtil.format(new Date(), "yyyyMM"));
		System.out.println(JSONArray.fromObject(list).toString());
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeCount")
	public @ResponseBody String HelpTypeCount(HttpSession session, ModelMap model) throws Exception {
		Date lastYear = DateUtil.addYear(new Date(), -1);
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(lastYear, "yyyyMMdd"), DateUtil.format(new Date(), "yyyyMMdd"));
		System.out.println(JSONArray.fromObject(list).toString());
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeThisYearCount")
	public @ResponseBody String HelpTypeThisYearCount(HttpSession session, ModelMap model) throws Exception {
        Calendar currCal=Calendar.getInstance();  
        Date firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR));
        Date lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR));
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
		System.out.println(JSONArray.fromObject(list).toString());
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeProvYearCount")
	public @ResponseBody String HelpTypeProvYearCount(HttpSession session, ModelMap model) throws Exception {
        Calendar currCal=Calendar.getInstance();  
        Date firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR -1));
        Date lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR -1));
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
		System.out.println(JSONArray.fromObject(list).toString());
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeAllCount")
	public @ResponseBody String HelpTypeAllCount(HttpSession session, ModelMap model) throws Exception {
        Calendar currCal=Calendar.getInstance();  
        Date firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR -20));
        Date lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR));
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
		System.out.println(JSONArray.fromObject(list).toString());
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=htCount")
	public @ResponseBody String HtCount(HttpSession session, ModelMap model) throws Exception {
        Calendar currCal=Calendar.getInstance();  
        Date firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR));
        Date lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR));
		List<CountHelpTypeBean> thisYearList = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
		Map<String, List<CountHelpTypeBean>> map = new HashMap<String, List<CountHelpTypeBean>>();
		map.put("thisYear", thisYearList);
		
        currCal=Calendar.getInstance();  
        firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR) -1);
         lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR) -1);
		List<CountHelpTypeBean> provYearList = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
		map.put("provYear", provYearList);
         currCal=Calendar.getInstance();  
         firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR) -20);
         lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR));
		List<CountHelpTypeBean> allList = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
		map.put("allYear", allList);
		return JSONArray.fromObject(map).toString();
	}
	
	@RequestMapping(params = "action=agentStatus")
	public @ResponseBody String AgentStatus(HttpSession session, ModelMap model) throws Exception {
		List<AgentStatusBean> list = reportBO.queryAgentStatus();
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=count")
	public @ResponseBody String Count(@RequestParam(value = "targetDevice", required = true) String targetDevice, HttpSession session, ModelMap model) throws Exception {
		List<AgentStatusBean> list = reportBO.countPhone(new Date(), targetDevice);
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=callcount")
	public @ResponseBody String count(HttpSession session, ModelMap model) throws Exception {
		int count = reportBO.queryInTimeCallCount();
		Map<String, String> map = new HashMap<String, String>();
		map.put("count", String.valueOf(count));
		return JSONArray.fromObject(map).toString();
	}
	
	
	@RequestMapping(params = "action=areacount")
	public @ResponseBody String areaCount(HttpSession session, ModelMap model) throws Exception {
		List<InfoAreaCountBean> list = reportBO.countInfoByArea();
		return JSONArray.fromObject(list).toString();
	}
}
