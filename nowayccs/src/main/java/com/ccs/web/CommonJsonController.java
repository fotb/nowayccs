package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAreaBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.ChomboBoxContainer;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.EntCategoryVO;
import com.ccs.vo.UserVO;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/json.do")
public class CommonJsonController {
	
	@Autowired
	private IEntpriseBO entpriseBO;
	
	@Autowired
	private IAreaBO areaBO;
	
	@Autowired
	private IUserBO userBO;
	
	@RequestMapping(params = "action=entcategory", method = RequestMethod.GET)
	public @ResponseBody List<EntCategoryVO> getCategory(@RequestParam String parentId) throws UnsupportedEncodingException {
//		List<EntCategoryVO> list = entpriseBO.findEntCategoryByParentId(parentId);
//		JSONArray jsonObj = JSONArray.fromObject(list);
		return entpriseBO.findEntCategoryByParentId(parentId);
	}
	
	@RequestMapping(params = "action=area", method = RequestMethod.GET)
	public @ResponseBody List<AreaVO> getArea() throws UnsupportedEncodingException {
//		List<AreaVO> list = areaBO.findAllArea();
//		JSONArray jsonObj = JSONArray.fromObject(list);
		return areaBO.findAllArea();
	}
	
	@RequestMapping(params = "action=subarea")
	public @ResponseBody List<AreaSubVO> getSubArea(@RequestParam String areaId) throws UnsupportedEncodingException {
//		List<AreaSubVO> list = areaBO.findAreaSubByAreaId(areaId);
//		JSONArray jsonObj = JSONArray.fromObject(list);
		return areaBO.findAreaSubByAreaId(areaId);
	}
	
	@RequestMapping(params = "action=userbyoprid", method = RequestMethod.GET)
	public @ResponseBody List<UserVO> getUserByOperationId(@RequestParam String oprId) {
//		List<UserVO> list = userBO.findUserByOpertaionId(oprId);
//		JSONArray jsonObj = JSONArray.fromObject(list);
		return userBO.findUserByOpertaionId(oprId);
	}
	
	@RequestMapping(params = "action=arealist", method = RequestMethod.GET)
	public @ResponseBody List<ChomboBoxContainer> getAreaForChomboBox() throws UnsupportedEncodingException {
		List<AreaVO> list = areaBO.findAllArea();
		List<ChomboBoxContainer> cbList = new ArrayList<ChomboBoxContainer>();
		for (AreaVO areaVO : list) {
			ChomboBoxContainer cb = new ChomboBoxContainer();
			cb.setText(areaVO.getName());
			cb.setValue(areaVO.getAreaId());
			cbList.add(cb);
		}
//		JSONArray jsonObj = JSONArray.fromObject(cbList);
		return cbList;
	}
	
	@RequestMapping(params = "action=subarealist")
	public @ResponseBody List<ChomboBoxContainer> getSubAreaForChomboBox(@RequestParam String areaId) throws UnsupportedEncodingException {
		List<AreaSubVO> list = areaBO.findAreaSubByAreaId(areaId);
		List<ChomboBoxContainer> cbList = new ArrayList<ChomboBoxContainer>();
		for (AreaSubVO areaSubVO : list) {
			ChomboBoxContainer cb = new ChomboBoxContainer();
			cb.setText(areaSubVO.getName());
			cb.setValue(areaSubVO.getAreaSubId());
			cbList.add(cb);
		}
//		JSONArray jsonObj = JSONArray.fromObject(cbList);
		return cbList;
	}
}
