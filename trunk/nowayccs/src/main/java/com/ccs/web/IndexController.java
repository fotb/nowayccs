package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAgentBO;
import com.ccs.util.Constants;
import com.ccs.vo.AgentVO;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/index.do")
public class IndexController {
	private static final Logger logger = Logger.getLogger(IndexController.class);
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
	
	@RequestMapping(params = "action=xtsz")
	public String systemSetting(ModelMap model) {
		return "menu_xtsz";
	}
	
	@RequestMapping(params = "action=icdLog", method = RequestMethod.GET)
	public @ResponseBody String getSubArea(@RequestParam String logId, String action) throws UnsupportedEncodingException {
		logger.info(action + ": " + logId);
		return "";
	}
	
}
