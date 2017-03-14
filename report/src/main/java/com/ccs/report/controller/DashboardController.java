package com.ccs.report.controller;

import java.util.ArrayList;
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
import com.ccs.report.util.AgentStatusBean;
import com.ccs.report.util.CountHelpTypeBean;
import com.ccs.report.util.DateUtil;
import com.ccs.report.util.InfoAreaCountBean;
import com.ccs.report.util.InfoDateCountBean;
import com.ccs.report.util.YearCountBean;
import com.ccs.report.vo.HistCountVO;

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
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeCount")
	public @ResponseBody String HelpTypeCount(HttpSession session, ModelMap model) throws Exception {
		Date lastYear = DateUtil.addYear(new Date(), -1);
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(lastYear, "yyyyMMdd"), DateUtil.format(new Date(), "yyyyMMdd"));
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeThisYearCount")
	public @ResponseBody String HelpTypeThisYearCount(HttpSession session, ModelMap model) throws Exception {
        Calendar currCal=Calendar.getInstance();  
        Date firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR));
        Date lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR));
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeProvYearCount")
	public @ResponseBody String HelpTypeProvYearCount(HttpSession session, ModelMap model) throws Exception {
        Calendar currCal=Calendar.getInstance();  
        Date firstDay = DateUtil.getYearFirst(currCal.get(Calendar.YEAR -1));
        Date lastDay = DateUtil.getYearLast(currCal.get(Calendar.YEAR -1));
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(firstDay, "yyyyMMdd"), DateUtil.format(lastDay, "yyyyMMdd"));
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
	
	@RequestMapping(params = "action=2")
	public String navToDateCount(HttpSession session, ModelMap model) throws Exception {
		return "infodatecount";
	}
	
	@RequestMapping(params = "action=datecount")
	public @ResponseBody String dateCount(HttpSession session, @RequestParam(value = "provsMonth", required=false) String provsMonth, ModelMap model) throws Exception {
		String fromDate = "";
		if(null == provsMonth || ("").equals(provsMonth)) {
			Date pDate = DateUtil.getDayOfProvsMonth(new Date(), 12);
			fromDate = DateUtil.format(pDate, "yyyyMMdd");
		} else {
			Date pDate = DateUtil.getDayOfProvsMonth(new Date(), Integer.valueOf(provsMonth));
			fromDate = DateUtil.format(pDate, "yyyyMMdd");
		}
		
		List<InfoDateCountBean> list = reportBO.countByDate(fromDate);
		List<Object[]> l = new ArrayList<Object[]>();
		for (InfoDateCountBean bean : list) {
			Object[] sArr = new Object[]{bean.getSdate(), bean.getCount()};
			l.add(sArr);
		}
		return JSONArray.fromObject(l).toString();
	}
	
	
	@RequestMapping(params = "action=3")
	public String navToYearCount(HttpSession session, ModelMap model) throws Exception {
		return "infoyearcount";
	}
	@RequestMapping(params = "action=yearcount")
	public @ResponseBody String yearCount(HttpSession session, ModelMap model) throws Exception {
		List<HistCountVO> list = reportBO.countHsitByYear();
		List<Object[]> objList = new ArrayList<Object[]>();
		for (HistCountVO bean : list) {
			Object[] sArr = new Object[]{bean.getYear(), Float.valueOf(bean.getCount())};
			objList.add(sArr);
		}
		return JSONArray.fromObject(objList).toString();
	}
	
	@RequestMapping(params = "action=volunteer")
	public @ResponseBody String volunteerCount(HttpSession session, @RequestParam(value = "top", required=true) String top, ModelMap model) throws Exception {
		List<Object[]> list = reportBO.queryTopVolunteer(Integer.valueOf(top));
		int total = reportBO.countVolunteer();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("top", list);
		return JSONArray.fromObject(map).toString();
	}
	
	@RequestMapping(params = "action=entprise")
	public @ResponseBody String entpriseCount(HttpSession session, @RequestParam(value = "top", required=true) String top, ModelMap model) throws Exception {
		List<Object[]> list = reportBO.queryTopEntprise(Integer.valueOf(top));
		int total = reportBO.countEntprise();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("top", list);
		return JSONArray.fromObject(map).toString();
	}
	
	
	@RequestMapping(params = "action=sumtotal")
	public @ResponseBody String sumTotal(HttpSession session, ModelMap model) throws Exception {
		int total = reportBO.sumTotalInfo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		return JSONArray.fromObject(map).toString();
	}
}
