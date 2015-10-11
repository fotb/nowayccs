package com.ccs.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.ILightPowerStaffBO;
import com.ccs.util.Constants;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerStaffDomain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("lps.do")
public class LightPowerStaffController {
	
	@Autowired
	private ILightPowerStaffBO  lpsBO;
	
	@RequestMapping(params = "action=list")
	public String list(ModelMap model) throws Exception {
		return "power/list";
	}
	
	@RequestMapping(params = "action=buildtree", method = RequestMethod.GET)
	public @ResponseBody String buildLPSTree(@RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false) String rows) throws Exception {
		LightPowerStaffTreeBean lpsTreeBean = lpsBO.buildLPSTree();
//		JSONArray jsonObj = JSONArray.fromObject(lpsTreeBean);
//		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", lpsTreeBean.getTotal());
		
		
		JSONArray jsonArray = JSONArray.fromObject(lpsTreeBean.getRows());
		
		jsonObj.put("rows", jsonArray.toString());
		jsonObj.put("footer", JSONArray.fromObject(lpsTreeBean.getFooter()).toString());
		System.out.println("json: " + jsonObj.toString());
		return jsonObj.toString();
	}
	
	@RequestMapping(params = "action=add")
	public String add(@ModelAttribute("powerStaffDomain") PowerStaffDomain powerStaffDomain, ModelMap model) {
		if (null == powerStaffDomain) {
			powerStaffDomain = new PowerStaffDomain();
		}
		model.addAttribute("powerStaffDomain");

		return "power/add";
	}

	@RequestMapping(params = "action=save")
	public String save(@ModelAttribute("powerStaffDomain") PowerStaffDomain powerStaffDomain, HttpSession session, ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		lpsBO.saveLPS(powerStaffDomain, user);
		
		return "power/list";
	}
	
	@RequestMapping(params = "action=del")
	public @ResponseBody void del(@RequestParam String id, HttpSession session, ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		lpsBO.deleteLPS(id, user);
	}
}
