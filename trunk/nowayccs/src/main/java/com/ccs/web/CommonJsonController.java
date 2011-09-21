package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAreaBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.EntCategoryVO;

@Controller
@RequestMapping("/json.do")
public class CommonJsonController {
	
	@Autowired
	private IEntpriseBO entpriseBO;
	
	@Autowired
	private IAreaBO areaBO;
	
	@RequestMapping(params = "action=entcategory", method = RequestMethod.GET)
	public @ResponseBody String getCategory(@RequestParam String parentId) throws UnsupportedEncodingException {
		List<EntCategoryVO> list = entpriseBO.findEntCategoryByParentId(parentId);
		JSONArray jsonObj = JSONArray.fromObject(list);
		return jsonObj.toString();
	}
	
	@RequestMapping(params = "action=area", method = RequestMethod.GET)
	public @ResponseBody String getArea() throws UnsupportedEncodingException {
		List<AreaVO> list = areaBO.findAllArea();
		JSONArray jsonObj = JSONArray.fromObject(list);
		return jsonObj.toString();
	}
	
	@RequestMapping(params = "action=subarea", method = RequestMethod.GET)
	public @ResponseBody String getSubArea(@RequestParam String areaId) throws UnsupportedEncodingException {
		List<AreaSubVO> list = areaBO.findAreaSubByAreaId(areaId);
		JSONArray jsonObj = JSONArray.fromObject(list);
		return jsonObj.toString();
	}
}
