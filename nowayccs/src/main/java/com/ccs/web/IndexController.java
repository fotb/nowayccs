package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IAgentBO;
import com.ccs.bo.IUserStatusBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.vo.AgentVO;
import com.ccs.vo.UserStatusVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/index.do")
public class IndexController {
	private static final Logger logger = Logger.getLogger(IndexController.class);
	@Autowired
	private IAgentBO agentBO;
	
	@Autowired
	private IUserStatusBO userStatusBO;

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
		
		return "headnew";
	}
	
	@RequestMapping(params = "action=left")
	public String left(ModelMap model) {
		return "left";
	}
	
	@RequestMapping(params = "action=leftold")
	public String leftOld(ModelMap model) {
		return "left_old";
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
	public @ResponseBody String getSubArea(@RequestParam String logId, String actionType) throws UnsupportedEncodingException {
		logger.info(DateUtil.format(new Date(), "yyyy/MM/dd HH:mm:ss") + " -- " + actionType + ": " + logId);
		return "";
	}
	
	
	@RequestMapping(params = "action=sessionHeartBeat", method = RequestMethod.POST)
	public @ResponseBody String sessionHeartBeat(@RequestParam String status, @RequestParam String phoneNo, HttpSession session, ModelMap model) throws UnsupportedEncodingException {
		UserVO vo = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
//		logger.info("Info: check user online-----------------");
		if(null == vo) {
			logger.info("Info:------session time Out!");
		} else {
			userStatusBO.updateUserStatus(vo.getUserId(), UserStatusVO.STATUS_1, status, phoneNo, session.getId());
		}
		return "";
	}

}
