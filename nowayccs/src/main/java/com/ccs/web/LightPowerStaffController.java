package com.ccs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.ccs.vo.BaseEntity;
import com.ccs.vo.PowerStaffAreaVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LightPowerStaffTreeBean;
import com.ccs.web.domain.PowerStaffDomain;
import com.ccs.web.domain.PowerStaffListBean;

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
//		System.out.println("json: " + jsonObj.toString());
		return jsonObj.toString();
	}
	
	@RequestMapping(params = "action=buildlist", method = RequestMethod.GET)
	public @ResponseBody String buildLPSList(@RequestParam(value="areaId", required=false) String areaId, @RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false) String rows) throws Exception {
		List<PowerStaffListBean> beanList = lpsBO.buildList(areaId, Integer.valueOf(page), Integer.valueOf(rows));
//		JSONArray jsonObj = JSONArray.fromObject(lpsTreeBean);
//		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", lpsBO.countBuildList(areaId));
		
		
		JSONArray jsonArray = JSONArray.fromObject(beanList);
		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}
	
	
	@RequestMapping(params = "action=load", method = RequestMethod.GET)
	public @ResponseBody String load(@RequestParam(value="areaId", required=false) String areaId, @RequestParam(value="page", required=false) String page, @RequestParam(value="rows", required=false) String rows) throws Exception {
		LightPowerStaffTreeBean lpsTreeBean = lpsBO.buildLPSTree();
//		JSONArray jsonObj = JSONArray.fromObject(lpsTreeBean);
//		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", lpsTreeBean.getTotal());
		
		
		JSONArray jsonArray = JSONArray.fromObject(lpsTreeBean.getRows());
		
		jsonObj.put("rows", jsonArray.toString());
		jsonObj.put("footer", JSONArray.fromObject(lpsTreeBean.getFooter()).toString());
//		System.out.println("json: " + jsonObj.toString());
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
		return "redirect:lps.do?action=list";
	}
	
	@RequestMapping(params = "action=saveLps")
	public @ResponseBody void save(@RequestParam String id, @RequestParam String name, @RequestParam String phone, @RequestParam String remark, HttpSession session, ModelMap model) throws Exception {
		PowerStaffVO psVO = lpsBO.findPowerStaffById(id);
		psVO.setName(name);
		psVO.setPhone(phone);
		psVO.setRemark(remark);
		lpsBO.updatePowerStaff(psVO);
	}
	
	@RequestMapping(params = "action=del")
	public @ResponseBody void del(@RequestParam String id, HttpSession session, ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		lpsBO.deleteLPS(id, user);
	}
	
	
	@RequestMapping(params = "action=associate")
	public String associate(ModelMap model) throws Exception {
		return "power/associate";
	}
	
	@RequestMapping(params = "action=pslist")
	public @ResponseBody String psList(ModelMap model) throws Exception {
		List<PowerStaffVO> list = lpsBO.findAll();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", list.size());
		JSONArray jsonArray = JSONArray.fromObject(list);
		
		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}
	
	
	@RequestMapping(params = "action=associateSave")
	public @ResponseBody void associateSave(@RequestParam String areaSubId, @RequestParam("pids[]") String[] pids,  HttpSession session,ModelMap model) throws Exception {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		Date date = new Date();
		List<PowerStaffAreaVO> psaVOList = new ArrayList<PowerStaffAreaVO>();
		for(String staffId: pids) {
			PowerStaffAreaVO psaVO = new PowerStaffAreaVO();
			psaVO.setAreaSubId(areaSubId);
			psaVO.setStaffId(staffId);
			psaVO.setCreateTime(date);
			psaVO.setUpdateDT(date);
			psaVO.setDeleteFlag(BaseEntity.DELETE_FLAG_NO);
			psaVO.setLastHandler(user.getUserId());
			psaVOList.add(psaVO);
		}
		lpsBO.saveOrUpdate(psaVOList);
	}
}
