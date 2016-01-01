package com.ccs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.ILightPowerStaffBO;
import com.ccs.web.domain.PowerStaffReportBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("preport.do")
public class PowerReportController {

	@Autowired
	private ILightPowerStaffBO lpsBO;
	
	@RequestMapping
	public String open(ModelMap model) throws Exception {
		return "power/report/reportlist";
	}

	@RequestMapping(params = "action=list", method = RequestMethod.GET)
	public @ResponseBody String list(@RequestParam(value = "areaId", required = false) String areaId,
			@RequestParam(value = "areaSubId", required = false) String areaSubId,
			@RequestParam(value = "startdt", required = false) String startDt,
			@RequestParam(value = "enddt", required = false) String endDt, ModelMap model) throws Exception {

		List<PowerStaffReportBean> beanList = lpsBO.powerStaffReport(areaId, areaSubId, startDt, endDt);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", beanList.size());
		
		
		JSONArray jsonArray = JSONArray.fromObject(beanList);
		
		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}
}
