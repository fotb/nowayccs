package com.ccs.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bean.MessageBean;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IMessageBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.DictVO;
import com.ccs.vo.MessageVO;
import com.ccs.vo.UserVO;

@Controller
@RequestMapping("/msg.do")
public class MessageController {

	@Autowired
	private IMessageBO messageBO;
	
	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IUserBO userBO;
	

	@RequestMapping
	public String list(@ModelAttribute("messageBean") MessageBean msgBean,
			@RequestParam(value = "pageNo", required = false) String pageNo, @RequestParam(value = "msgType", required = false) String msgType, HttpSession session, 
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		
		if(!StringUtil.isNull(msgType)) {
			msgBean.setMessageType(msgType);
		}
		
		List<MessageVO> msgVOList = messageBO.findByParams(
				msgBean.getMessageType(), msgBean.getCreator(),
				msgBean.getTitle(), msgBean.getStartDt(), msgBean.getEndDt(),
				pageInfo);
		
		List<DictVO> msgTypeList = dictBO.findByType(Constants.DICT_DICTTYPE_COMMONLB);
		Map<String, String> msgTypeMap = new HashMap<String, String>();
		for(DictVO dictVO : msgTypeList) {
			msgTypeMap.put(dictVO.getSortIndex(), dictVO.getValue());
		}

		Map<String, UserVO> userMap = userBO.findAll();
		
		UserVO currentUser = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("msgVOList", msgVOList);
		model.addAttribute("msgTypeList", msgTypeList);
		model.addAttribute("msgTypeMap", msgTypeMap);
		model.addAttribute("userMap", userMap);
		model.addAttribute("users", userMap.values());
		model.addAttribute("pageInfo", pageInfo);
		return "msg/list";
	}
	
	@RequestMapping(params = "action=add")
	public String add(String msgType, HttpSession session, ModelMap model) {
		Map<String, String> msgTypeMap = dictBO.getDict(Constants.DICT_DICTTYPE_COMMONLB);
		UserVO currentUser = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		model.addAttribute("currentUser", currentUser);
		
		model.addAttribute("msgTypeValue", msgTypeMap.get(msgType));
		model.addAttribute("msgType", msgType);
		MessageVO vo = new MessageVO();
		vo.setMessageType(msgType);
		model.addAttribute("messageVO", vo);
		return "msg/add";
	}
	
	
	@RequestMapping(params = "action=addsave")
	public String addSave(@ModelAttribute("messageVO") MessageVO msgVO, HttpSession session, ModelMap model) {
		UserVO currentUser = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		msgVO.setCreator(currentUser.getUserId());
		msgVO.setCreateDate(new Date());
		messageBO.saveOrUpdate(msgVO);
		return "redirect:msg.do?msgType=" + msgVO.getMessageType();
	}
	
	
	
	@RequestMapping(params = "action=edit")
	public String edit(String msgId, HttpSession session, ModelMap model) {
		List<DictVO> msgTypeList = dictBO.findByType(Constants.DICT_DICTTYPE_COMMONLB);
		model.addAttribute("msgTypeList", msgTypeList);
		
		MessageVO msgVO = messageBO.findById(msgId);
		model.addAttribute("messageVO", msgVO);
		
		Map<String, String> msgTypeMap = dictBO.getDict(Constants.DICT_DICTTYPE_COMMONLB);
		model.addAttribute("msgTypeValue", msgTypeMap.get(msgVO.getMessageType()));

		UserVO userVO = userBO.findById(msgVO.getCreator());
		model.addAttribute("userVO", userVO);
		return "msg/update";
	}
	
	@RequestMapping(params = "action=editsave")
	public String editSave(@ModelAttribute("messageVO") MessageVO msgVO, HttpSession session, ModelMap model) {
		UserVO currentUser = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		MessageVO vo = messageBO.findById(msgVO.getMessageId());
		vo.setContent(msgVO.getContent());
		vo.setTitle(msgVO.getTitle());
		vo.setMessageType(msgVO.getMessageType());
		vo.setCreator(currentUser.getUserId());
		messageBO.saveOrUpdate(vo);
		return "redirect:msg.do?msgType=" + msgVO.getMessageType();
	}
	
	@RequestMapping(params = "action=del")
	public String del(@ModelAttribute("messageBean") MessageBean msgBean,
			@RequestParam(value = "pageNo", required = false) String pageNo, 
			String msgId, String msgType, HttpSession session, ModelMap model) {
		MessageVO msgVO = messageBO.findById(msgId);
		messageBO.delete(msgVO);
		
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		
		if(!StringUtil.isNull(msgType)) {
			msgBean.setMessageType(msgType);
		}
		
		List<MessageVO> msgVOList = messageBO.findByParams(
				msgBean.getMessageType(), msgBean.getCreator(),
				msgBean.getTitle(), msgBean.getStartDt(), msgBean.getEndDt(),
				pageInfo);
		
		List<DictVO> msgTypeList = dictBO.findByType(Constants.DICT_DICTTYPE_COMMONLB);
		Map<String, String> msgTypeMap = new HashMap<String, String>();
		for(DictVO dictVO : msgTypeList) {
			msgTypeMap.put(dictVO.getSortIndex(), dictVO.getValue());
		}

		Map<String, UserVO> userMap = userBO.findAll();
		
		UserVO currentUser = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("msgVOList", msgVOList);
		model.addAttribute("msgTypeList", msgTypeList);
		model.addAttribute("msgTypeMap", msgTypeMap);
		model.addAttribute("userMap", userMap);
		model.addAttribute("users", userMap.values());
		model.addAttribute("pageInfo", pageInfo);
		return "msg/list";
	}
}
