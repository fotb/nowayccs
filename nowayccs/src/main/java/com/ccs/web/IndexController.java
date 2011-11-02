package com.ccs.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccs.bo.IAgentBO;
import com.ccs.util.Constants;
import com.ccs.vo.AgentVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/index.do")
public class IndexController {
	
	@Autowired
	private IAgentBO agentBO;

	@RequestMapping
	public String index(ModelMap model) {
		return "main";
	}
	@RequestMapping(params = "action=head")
	public String head(HttpSession session, ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		model.addAttribute("user", userVO);

		AgentVO agentVO = agentBO.findById(userVO.getUserId());
		model.addAttribute("agentVO", agentVO);
		
		return "head";
	}
	
	@RequestMapping(params = "action=left")
	public String left(ModelMap model) {
		return "left";
	}
	
	@RequestMapping(params = "action=noright")
	public String noRight(ModelMap model) {
		return "noright";
	}
	
	
}
