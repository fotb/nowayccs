package com.ccs.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccs.bo.IAgentBO;
import com.ccs.util.Constants;
import com.ccs.vo.AgentVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/agent.do")
public class AgentController {

	@Autowired
	private IAgentBO agentBO;
	
	@RequestMapping
	public String list(HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		AgentVO agentVO = agentBO.findById(user.getUserId());
		model.addAttribute("agent", agentVO);
		model.addAttribute("user", user);
		return "agent/list";
	}
	
	@RequestMapping(params="action=add")
	public String add(HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		AgentVO agentVO = new AgentVO();
		agentVO.setUserId(user.getUserId());
		model.addAttribute("agent", agentVO);
		model.addAttribute("user", user);
		return "agent/add";
	}
	
	@RequestMapping(params="action=addsave")
	public String addSave(@ModelAttribute("agent") AgentVO agentVO, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		agentVO.setUserId(user.getUserId());
		agentBO.saveOrUpdate(agentVO);
		return "redirect:agent.do";
	}
	
	@RequestMapping(params="action=edit")
	public String edit(String userId, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		AgentVO agentVO = agentBO.findById(userId);
		model.addAttribute("agent", agentVO);
		model.addAttribute("user", user);
		return "agent/edit";
	}
	
	@RequestMapping(params="action=editsave")
	public String editSave(@ModelAttribute("agent") AgentVO agentVO, ModelMap model) {
		agentBO.saveOrUpdate(agentVO);
		return "redirect:agent.do";
	}
	
	@RequestMapping(params="action=view")
	public String view(String userId, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		AgentVO agentVO = agentBO.findById(userId);
		model.addAttribute("agent", agentVO);
		model.addAttribute("user", user);
		return "agent/view";
	}
		
	@RequestMapping(params = "action=transout")
	public String transOut(HttpSession session, ModelMap model) {
		return "transout";
	}	
}
