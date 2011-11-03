package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.UserVO;
import com.ccs.vo.VolunteerVO;
import com.ccs.web.domain.VolunteerSearch;

@Controller
@RequestMapping("/volunteer.do")
public class VolunteerController {

	@Autowired
	private IVolunteerBO volunteerBO;

	@Autowired
	private IUserBO userBO;
	
	@RequestMapping
	public String list(
			@ModelAttribute("volunteerSearch") VolunteerSearch volunteerSearch,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			HttpSession session, ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if (StringUtil.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}

		if (null == volunteerSearch) {
			volunteerSearch = new VolunteerSearch();
		}

		List<VolunteerVO> vtList = volunteerBO.search(
				volunteerSearch.getStatus(), volunteerSearch.getServiceType(),
				volunteerSearch.getAreaId(), volunteerSearch.getAreaSubId(),
				volunteerSearch.getVolunteerNo(), volunteerSearch.getServiceName(), pageInfo);

		model.addAttribute("volunteerVOList", vtList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("volunteerSearch", volunteerSearch);
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		if(userBO.hasOperationRight(user.getUserId(), Constants.SYS_PERMISSION_SQZYZWH)) {
			model.addAttribute("adminRight", "Y");
		} else {
			model.addAttribute("adminRight", "N");
		}
		return "volunteer/list";
	}
	
	@RequestMapping(params = "action=area", method = RequestMethod.GET)
	public @ResponseBody String getArea() throws UnsupportedEncodingException {
		List<AreaVO> list = volunteerBO.getAllArea();
		JSONArray jsonObj = JSONArray.fromObject(list);
		return jsonObj.toString();
	}
	
	@RequestMapping(params = "action=subarea", method = RequestMethod.GET)
	public @ResponseBody String getSubArea(@RequestParam String areaId) throws UnsupportedEncodingException {
		List<AreaSubVO> list = volunteerBO.getSubAreaByAreaId(areaId);
		JSONArray jsonObj = JSONArray.fromObject(list);
		return jsonObj.toString();
	}
	
	@RequestMapping(params = "action=add")
	public String add(@RequestParam String pageNo, ModelMap model) {
		VolunteerVO volunteerVO = new VolunteerVO();
		model.addAttribute("volunteerVO", volunteerVO);
		model.addAttribute("pageNo", pageNo);
		return "volunteer/add";
	}
	
	@RequestMapping(params = "action=saveorupdate")
	public String save(@ModelAttribute VolunteerVO volunteerVO, @RequestParam String pageNo, ModelMap model) {
		volunteerVO.setStatus(Constants.SYS_YESNO_YES);
		
		volunteerBO.saveOrUpdate(volunteerVO);
		return "redirect:volunteer.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=update")
	public String update(@RequestParam String volunteerId, @RequestParam String pageNo, ModelMap model) {
		VolunteerVO volunteerVO = volunteerBO.findById(volunteerId);
		model.addAttribute("volunteerVO", volunteerVO);
		model.addAttribute("pageNo", pageNo);
		return "volunteer/edit";
	}
	
	@RequestMapping(params = "action=view")
	public String view(@RequestParam String volunteerId, @RequestParam String pageNo, ModelMap model) {
		VolunteerVO volunteerVO = volunteerBO.findById(volunteerId);
		model.addAttribute("volunteerVO", volunteerVO);
		model.addAttribute("pageNo", pageNo);
		return "volunteer/view";
	}
	
	@RequestMapping(params = "action=changestatus")
	public String changeStatus(@RequestParam String volunteerId, 
			@RequestParam String tostatus, 
			@RequestParam String pageNo, ModelMap model) {
		VolunteerVO volunteerVO = volunteerBO.findById(volunteerId);
		volunteerVO.setStatus(tostatus);
		volunteerBO.saveOrUpdate(volunteerVO);
		return "redirect:volunteer.do?pageNo=" + pageNo;
	}
}
