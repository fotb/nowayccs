package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IVolunteerBO;
import com.ccs.util.PageInfo;
import com.ccs.util.Utils;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.VolunteerVO;
import com.ccs.web.domain.VolunteerSearch;

@Controller
@RequestMapping("/volunteer.do")
public class VolunteerController {

	@Autowired
	private IVolunteerBO volunteerBO;

	@RequestMapping
	public String list(
			@ModelAttribute("volunteerSearch") VolunteerSearch volunteerSearch,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if (Utils.isNull(pageNo)) {
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
				volunteerSearch.getVolunteerNo(), pageInfo);

		model.addAttribute("volunteerVOList", vtList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("volunteerSearch", volunteerSearch);
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
}
