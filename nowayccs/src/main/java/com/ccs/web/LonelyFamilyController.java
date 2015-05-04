package com.ccs.web;

import java.io.UnsupportedEncodingException;
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

import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.util.Constants;
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
	
	@RequestMapping(params = "action=lonelyManInfo", method = RequestMethod.GET)
	public @ResponseBody String loadFamilyInfo(@RequestParam(value = "callNo", required = false) String callNo) throws UnsupportedEncodingException {
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

}
