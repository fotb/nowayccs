package com.ccs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IBlackListBO;
import com.ccs.util.PageInfo;
import com.ccs.util.Response;
import com.ccs.vo.BlackListVO;

@Controller
@RequestMapping("/blacklist.do")
public class BlackListController {

	@Autowired
	private IBlackListBO blackListBO;
	
	@Autowired
	@Qualifier("blackListValidator")
	private Validator blackListValidator;
	
	@RequestMapping
	public String list(@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value="levels", required=false) String levels, 
			@RequestParam(value = "pageNo", required = false) String pageNo, ModelMap model) {
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		List<BlackListVO> list = blackListBO.findByParams(phoneNum, levels, pageInfo);
		
		model.addAttribute("blackListVOList", list);
		model.addAttribute("phoneNum", phoneNum);
		model.addAttribute("levels", levels);
		model.addAttribute("pageInfo", pageInfo);
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
	public String addSave(@ModelAttribute("blackListVO") BlackListVO vo, BindingResult result, ModelMap model) {
		blackListValidator.validate(vo, result);
		if(result.hasErrors()) {
			return "blacklist/add";
		} else {
			blackListBO.saveOrUpdate(vo);
			return "redirect:blacklist.do";
		}
	}
	
	@RequestMapping(params = "action=edit")
	public String edit(String phoneId, String pageNo, ModelMap model) {
		BlackListVO vo = blackListBO.findById(phoneId);
		model.addAttribute("blackListVO", vo);
		model.addAttribute("pageNo", pageNo);
		return "blacklist/edit";
	}
	
	@RequestMapping(params = "action=editsave")
	public String editSave(@ModelAttribute("blackListVO") BlackListVO vo, String pageNo, ModelMap model) {
			blackListBO.saveOrUpdate(vo);
			return "redirect:blacklist.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=del")
	public String del(String phoneId, ModelMap model) {
		BlackListVO vo = blackListBO.findById(phoneId);
		blackListBO.delete(vo);
		return "redirect:blacklist.do";
	}
	
	@RequestMapping(params = "action=phonelevels", method = RequestMethod.GET)
	public @ResponseBody Response getPhoneLevles(@RequestParam String phoneNum) {
		BlackListVO vo = blackListBO.findByPhoneNum(phoneNum);
		Response res = new Response();
		res.success(vo);
		return res;
	}
}
