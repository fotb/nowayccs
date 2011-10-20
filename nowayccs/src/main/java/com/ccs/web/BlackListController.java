package com.ccs.web;

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

import com.ccs.bo.IBlackListBO;
import com.ccs.vo.BlackListVO;

@Controller
@RequestMapping("/blacklist.do")
public class BlackListController {

	@Autowired
	private IBlackListBO blackListBO;
	
	
	@RequestMapping
	public String list(@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value="levels", required=false) String levels, ModelMap model) {
		List<BlackListVO> list = blackListBO.findByParams(phoneNum, levels);
		
		model.addAttribute("blackListVOList", list);
		model.addAttribute("phoneNum", phoneNum);
		model.addAttribute("levels", levels);
		return "blacklist/list";
	}
	
	@RequestMapping(params = "action=add")
	public String add(ModelMap model) {
		BlackListVO vo = new BlackListVO();
		vo.setLevels("0");
		model.addAttribute("blackListVO", vo);
		return "blacklist/add";
	}
	
	@RequestMapping(params = "action=addsave")
	public String addSave(@ModelAttribute("blackListVO") BlackListVO vo, ModelMap model) {
		blackListBO.saveOrUpdate(vo);
		return "redirect:blacklist.do";
	}
	
	@RequestMapping(params = "action=edit")
	public String edit(String phoneId, ModelMap model) {
		BlackListVO vo = blackListBO.findById(phoneId);
		model.addAttribute("blackListVO", vo);
		return "blacklist/edit";
	}
	
	@RequestMapping(params = "action=editsave")
	public String editSave(@ModelAttribute("blackListVO") BlackListVO vo, ModelMap model) {
		blackListBO.saveOrUpdate(vo);
		return "redirect:blacklist.do";
	}
	
	@RequestMapping(params = "action=del")
	public String del(String phoneId, ModelMap model) {
		BlackListVO vo = blackListBO.findById(phoneId);
		blackListBO.delete(vo);
		return "redirect:blacklist.do";
	}
	
	@RequestMapping(params = "action=phonelevels", method = RequestMethod.GET)
	public @ResponseBody String getPhoneLevles(@RequestParam String phoneNum) {
		BlackListVO vo = blackListBO.findByPhoneNum(phoneNum);
		JSONArray jsonObj = JSONArray.fromObject(vo);
		return jsonObj.toString();
	}
}
