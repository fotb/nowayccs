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
import org.springframework.web.bind.support.SessionStatus;

import com.ccs.bo.IUserBO;
import com.ccs.util.PageInfo;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.User;

@Controller
@RequestMapping("/user.do")
public class UserController {

	@Autowired
	private IUserBO userBO;

	@Autowired
	@Qualifier("userValidator")
	private Validator userValidator;

	@RequestMapping(method = RequestMethod.GET)
	public String init(ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(1);
		List<UserVO> list = userBO.findAll(pageInfo);
		model.addAttribute("userList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "user/list";
	}

	@RequestMapping(params = "action=gotopage")
	public String gotoPage(@RequestParam("pageNo") String pageNo, ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(Integer.parseInt(pageNo));

		List<UserVO> list = userBO.findAll(pageInfo);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("userList", list);

		return "user/list";
	}

	@RequestMapping(params = "action=onjob")
	public String chageOnJob(@RequestParam("userId") String userId,
			@RequestParam("onJob") String onJob,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		UserVO vo = userBO.findById(userId);
		vo.setOnJob(onJob);
		userBO.saveOrUpdate(vo);
		return "redirect:user.do?action=gotopage&pageNo=" + pageNo;
	}

	@RequestMapping(params = "action=add")
	public String add(@RequestParam("pageNo") String pageNo, ModelMap model) {
		model.addAttribute("pageNo", pageNo);
		User user = new User();
		model.addAttribute(user);
		return "user/add";
	}

	@RequestMapping(params = "action=addsave")
	public String addSave(@ModelAttribute("user") User user, BindingResult result,@RequestParam("pageNo") String pageNo,
			SessionStatus status, ModelMap model) {
		model.addAttribute("pageNo", pageNo);
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "user/add";
		} else {
			UserVO vo = new UserVO();
			vo.setEmailAddr(user.getEmailAddr());
			vo.setHomeAddr(user.getHomeAddr());
			vo.setHomePostCode(user.getHomePostCode());
			vo.setHomeTel(user.getHomeTel());
			vo.setLinkMobile(user.getLinkMobile());
			vo.setLoginName(user.getLoginName());
			vo.setLoginPassword(user.getLoginPassword());
			vo.setOfficeAddr(user.getOfficeAddr());
			vo.setOfficePostCode(user.getOfficePostCode());
			vo.setOfficeTel(user.getOfficeTel());
			vo.setOnJob(UserVO.ONJOB_YES);
			vo.setUserName(user.getUserName());
			// TODO
			// role

			userBO.addUser(vo);

			return "redirect:user.do?action=gotopage";
		}
	}

}
