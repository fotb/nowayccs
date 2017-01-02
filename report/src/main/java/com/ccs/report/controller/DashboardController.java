package com.ccs.report.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.report.bo.IReportBO;
import com.ccs.util.CountHelpTypeBean;
import com.ccs.util.DateUtil;
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
	public @ResponseBody String monthChart(HttpSession session, ModelMap model) throws Exception {
		Date lastYear = DateUtil.addYear(new Date(), -1);
		List<YearCountBean> list = reportBO.queryInfoCountByMonth(DateUtil.format(lastYear, "yyyyMM"), DateUtil.format(new Date(), "yyyyMM"));
		System.out.println(JSONArray.fromObject(list).toString());
		return JSONArray.fromObject(list).toString();
	}
	
	@RequestMapping(params = "action=helpTypeCount")
	public @ResponseBody String helpTypeCount(HttpSession session, ModelMap model) throws Exception {
		Date lastYear = DateUtil.addYear(new Date(), -1);
		List<CountHelpTypeBean> list = reportBO.countInfoByHelpType(DateUtil.format(lastYear, "yyyyMM"), DateUtil.format(new Date(), "yyyyMM"));
		System.out.println(JSONArray.fromObject(list).toString());
		return JSONArray.fromObject(list).toString();
	}
}
