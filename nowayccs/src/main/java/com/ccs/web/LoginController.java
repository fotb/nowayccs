package com.ccs.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccs.bo.IUserBO;
import com.ccs.bo.IUserStatusBO;
import com.ccs.util.Constants;
import com.ccs.vo.UserStatusVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.LoginBean;

@Controller
@RequestMapping("/login.do")
public class LoginController {

	@Autowired
	private IUserBO userBO;
	@Autowired
	private Validator loginValidator;
	
	@Autowired
	private IUserStatusBO userStatusBO;
	
	@RequestMapping
	public String load(ModelMap model) {
		model.addAttribute("loginBean", new LoginBean());
		return "login";
	}
	
	@RequestMapping(params = "action=login")
	public String login(@ModelAttribute("loginBean") LoginBean loginBean, BindingResult result, HttpSession session, ModelMap model) {
		loginValidator.validate(loginBean, result);
		if (result.hasErrors()) {
			return "login";
		} else {
			UserVO vo = userBO.login(loginBean.getLoginName(), loginBean.getLoginPassword());
			session.setAttribute(Constants.SESSION_USER_KEY, vo);
			
			userStatusBO.updateUserStatus(vo.getUserId(), UserStatusVO.STATUS_1, session.getId());
		}
		return "redirect:index.do";
	}
	
	@RequestMapping(params = "action=logout")
	public String logout(HttpSession session, ModelMap model) {
		UserVO vo = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		session.setAttribute(Constants.SESSION_USER_KEY, null);
		userStatusBO.updateUserStatus(vo.getUserId(), UserStatusVO.STATUS_0, session.getId());
		return "redirect:login.do";
	}
	
	@RequestMapping(params = "action=relogin")
	public String reLogin(ModelMap model) {
		return "relogin";
	}
}
