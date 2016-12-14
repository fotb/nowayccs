package com.ccs.report.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dashboard.do")
public class DashboardController {
	
	private static final Logger logger = Logger.getLogger(DashboardController.class);
	
	@RequestMapping(params = "action=init")
	public String init(HttpSession session, ModelMap model) {
		
		System.out.println("dafafadfsd");
		return "dashboard";
	}

}
