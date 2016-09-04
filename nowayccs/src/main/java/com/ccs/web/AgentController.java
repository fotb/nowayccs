package com.ccs.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAgentBO;
import com.ccs.bo.ICalloutCodeBO;
import com.ccs.util.Constants;
import com.ccs.vo.AgentVO;
import com.ccs.vo.CalloutCodeVO;
import com.ccs.vo.UserVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/agent.do")
public class AgentController {

	@Autowired
	private IAgentBO agentBO;
	
	@Autowired
	private ICalloutCodeBO calloutCodeBO;
	
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
	
	@RequestMapping(params="action=callout")
	public String callOut(HttpSession session, ModelMap model) {
		return "callout/list";
	}
	
	
	@RequestMapping(params = "action=coclist")
	public @ResponseBody String list(ModelMap model) throws Exception {
		
		List<CalloutCodeVO> cocVOList = calloutCodeBO.list();
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("total", cocVOList.size());
		JSONArray jsonArray = JSONArray.fromObject(cocVOList);
		jsonObj.put("rows", jsonArray.toString());
		return jsonObj.toString();
	}
	
}
