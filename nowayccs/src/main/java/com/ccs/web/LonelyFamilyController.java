package com.ccs.web;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccs.bo.IDictBO;
import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.util.Constants;
import com.ccs.util.StringUtil;
import com.ccs.vo.DictVO;
import com.ccs.vo.LonelyHelpVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.BizAccept;

@Controller
@RequestMapping("/lonelyFamily.do")
public class LonelyFamilyController {
	private static final Logger logger = Logger.getLogger(LonelyFamilyController.class);
	
	@Autowired
	private ILonelyFamilyBO lonelyFamilyBO;
	
	@Autowired
	private IDictBO dictBO;
	
	@RequestMapping(params = "action=lonelyManInfo", method = RequestMethod.GET)
	public @ResponseBody String loadFamilyInfo(@RequestParam(value = "callNo", required = false) String callNo) throws UnsupportedEncodingException {
		if(null != callNo) {
			if(callNo.startsWith("573")) {
				callNo = callNo.substring(3);
			} else if(callNo.startsWith("0573")) {
				callNo = callNo.substring(4);
			} else if(callNo.startsWith("+86")) {
				callNo = callNo.substring(3);
			} else if(callNo.startsWith("86")) {
				callNo = callNo.substring(2);
			}
		}
		LonelyManInfoVO vo = lonelyFamilyBO.findLonelyManInfo(callNo);
		return JSONObject.fromObject(vo).toString();
	}
	
	@RequestMapping(params = "action=accept")
	public String accept(@ModelAttribute("bizAccept") BizAccept bizAccept, HttpSession session, ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		
		final String helpTel = bizAccept.getHelpTel();
		LonelyManInfoVO vo = lonelyFamilyBO.findLonelyManInfo(helpTel);
		model.addAttribute("lonelyManInfoVO", vo);
		
		bizAccept.setCreator(userVO.getUserName());
		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("user", userVO);
		
		model.addAttribute("sftMap", Constants.SPECIAL_FAMILY_MAP);
		
		List<PartyMemberForLonelyVO> pmflList = lonelyFamilyBO.findByManId(vo.getManId());
		
		model.addAttribute("pmflList", pmflList);
		
		session.setAttribute("bizAccept", bizAccept);
		
//		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
//		model.addAttribute("qzfsList", qzfsList);
//				
//		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
//		
//		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
//		model.addAttribute("qzqyList", qzqyList);
//		
//		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
//		model.addAttribute("slrqList", slrqList);
		
		return "specialfamily/lonelyaccept";
	}
	
	@RequestMapping(params = "action=back")
	public String backAccept(HttpSession session, ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");

		model.addAttribute("bizAccept", bizAccept);
		model.addAttribute("user", userVO);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
				
		model.addAttribute("helpTypeMap", Constants.INFOMATION_HELPTYPE_HASHMAP);
		
		List<DictVO> qzqyList = dictBO.findByType(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyList", qzqyList);
		
		List<DictVO> slrqList = dictBO.findByType(Constants.DICT_DICTTYPE_SLRQ);
		model.addAttribute("slrqList", slrqList);
		
		return "bizaccept/accept2";
	}
	
	
	@RequestMapping(params = "action=save")
	public String save(@RequestParam("lonelyManId") String lonelyManId, @RequestParam("memberId") String memberId, HttpSession session, ModelMap model) {
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		BizAccept bizAccept = (BizAccept) session.getAttribute("bizAccept");
		
		LonelyHelpVO vo = new LonelyHelpVO();
		vo.setLonelyManId(lonelyManId);
		vo.setHelpContent(bizAccept.getHelpContent());
		vo.setDeliverer(memberId);
		vo.setCreateTime(new Date());
		vo.setCreator(user.getUserId());
		vo.setStatus("E");
		lonelyFamilyBO.helpSave(vo);
		
		session.setAttribute("bizAccept", null);
		if(!StringUtil.isNull(bizAccept.getPopupFlag())) {
			return "common/selfclose";
		} else {
			return "redirect:bizaccept.do";
		}
	}

}
