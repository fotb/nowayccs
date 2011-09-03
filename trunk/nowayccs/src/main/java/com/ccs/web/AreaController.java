package com.ccs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IAreaBO;
import com.ccs.util.PageInfo;
import com.ccs.util.Utils;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;


@Controller                   
@RequestMapping("/area.do")
public class AreaController {
	
	@Autowired
	private IAreaBO areaBO;
	
	@RequestMapping
	public String init(@RequestParam(value="pageNo", required=false) String pageNo, ModelMap model) {
		PageInfo pageInfo=new PageInfo();
		if(Utils.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}
		List<AreaVO> list = areaBO.findAllArea(pageInfo);
		model.addAttribute("AreaList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "area/list";
	}
	
	@RequestMapping(params="action=addArea")
	public String addArea(String pageNo, ModelMap model) {
		model.addAttribute("pageNo", pageNo);
		return "area/add";
	}
	
	@RequestMapping(params="action=saveArea")
	public String saveArea(@RequestParam("name") String name, @RequestParam("pageNo") String pageNo, ModelMap model) {
		AreaVO vo = new AreaVO();
		vo.setName(name);
		areaBO.saveOrUpdate(vo);		
		model.addAttribute("pageNo", pageNo);
		return "redirect:area.do";
	}
	
	@RequestMapping(params="action=editArea")
	public String editArea(String areaId, String pageNo, ModelMap model) {
		AreaVO vo = areaBO.findByAreaId(areaId);
		model.addAttribute("areaVO", vo);
		model.addAttribute("pageNo", pageNo);
		return "area/edit";
	}
	
	@RequestMapping(params="action=editSaveArea")
	public String editSaveArea(@RequestParam("areaId") String areaId, @RequestParam("name") String name, @RequestParam("pageNo") String pageNo, ModelMap model) {
		AreaVO vo = areaBO.findByAreaId(areaId);
		vo.setName(name);
		areaBO.saveOrUpdate(vo);		
		model.addAttribute("pageNo", pageNo);
		return "redirect:area.do";
	}
	
	@RequestMapping(params="action=del")
	public String delArea(String areaId, String pageNo, ModelMap model) {
		AreaVO vo = areaBO.findByAreaId(areaId);
		areaBO.delete(vo);
		model.addAttribute("pageNo", pageNo);
		return "redirect:area.do";
	}
	@RequestMapping(params="action=subArea")
	public String listSubArea(String areaId, String pageNo, ModelMap model) {
		List<AreaSubVO> list = areaBO.findAreaSubByAreaId(areaId);
		model.addAttribute("SubAreaList", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("areaId", areaId);
		return "area/listsub";
	}
	
	@RequestMapping(params="action=addSubArea")
	public String addSubArea(String areaId, String pageNo, ModelMap model) {
		model.addAttribute("areaId", areaId);
		model.addAttribute("pageNo", pageNo);
		return "area/addsub";
	}
	
	@RequestMapping(params="action=saveSubArea")
	public String saveSubArea(@RequestParam("name") String name, @RequestParam("areaId") String areaId, @RequestParam("pageNo") String pageNo, ModelMap model) {
		AreaSubVO vo = new AreaSubVO();
		vo.setName(name);
		vo.setAreaId(areaId);
		areaBO.saveOrUpdate(vo);		
		model.addAttribute("pageNo", pageNo);
		return "redirect:area.do?action=subArea&areaId=" + areaId;
	}
	
	@RequestMapping(params="action=editSubArea")
	public String editSubArea(String areaSubId, String pageNo, ModelMap model) {
		AreaSubVO vo = areaBO.findByAreaSubId(areaSubId);
		model.addAttribute("areaSubVO", vo);
		model.addAttribute("pageNo", pageNo);
		return "area/editsub";
	}
	
	@RequestMapping(params="action=editSaveSubArea")
	public String editSaveSubArea(@RequestParam("areaSubId") String areaSubId, @RequestParam("name") String name, @RequestParam("pageNo") String pageNo, ModelMap model) {
		AreaSubVO vo = areaBO.findByAreaSubId(areaSubId);
		vo.setName(name);
		areaBO.saveOrUpdate(vo);		
		model.addAttribute("pageNo", pageNo);
		return "redirect:area.do?action=subArea&areaId=" + vo.getAreaId();
	}
	
	@RequestMapping(params="action=delSub")
	public String delSubArea(String areaSubId, @RequestParam("areaId") String areaId, String pageNo, ModelMap model) {
		AreaSubVO vo = areaBO.findByAreaSubId(areaSubId);
		areaBO.delete(vo);
		model.addAttribute("pageNo", pageNo);
		return "redirect:area.do?action=subArea&areaId=" + areaId;
	}
}
