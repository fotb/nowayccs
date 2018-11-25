package com.ccs.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.ILonelyFamilyBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.LonelyHelpVO;
import com.ccs.vo.LonelyManInfoVO;
import com.ccs.vo.PartyMemberForLonelyVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.ShsForm;
import com.ccs.web.domain.ShsResultDTO;

@Controller
@RequestMapping("/shs.do")
public class SpecialHelpSearchController {
//	private static final Logger logger = Logger.getLogger(SpecialHelpSearchController.class);
	
	@Autowired
	private ILonelyFamilyBO lonelyFamilyBO;
	
//	@Autowired
//	private IDictBO dictBO;
	
	@Autowired
	private IUserBO userBO;
	
	@RequestMapping
	public String list(
			@ModelAttribute("shsForm") ShsForm shsForm,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			HttpSession session, ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if (StringUtil.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}

		if (null == shsForm) {
			shsForm = new ShsForm();
		}

		List<ShsResultDTO> voList = lonelyFamilyBO.findSpecialHelp(shsForm, pageInfo);
		
		model.addAttribute("sftMap", Constants.SPECIAL_FAMILY_MAP);

		model.addAttribute("shsResultDTOList", voList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("shsForm", shsForm);

		return "specialfamily/helplist";
	}
	
	@RequestMapping(params = "action=view")
	public String view(@RequestParam("helpId") String helpId, @RequestParam("pageNo") String pageNo, ModelMap model) {
		model.addAttribute("pageNo", pageNo);
		
		LonelyHelpVO lhVO = lonelyFamilyBO.findLonelyHelpByHelpId(helpId);
		LonelyManInfoVO lmiVO = lonelyFamilyBO.findLonelyManInfoByManId(lhVO.getLonelyManId());
		PartyMemberForLonelyVO pmflVO = lonelyFamilyBO.findPartyMemberForLonelyById(lhVO.getDeliverer());
		UserVO userVO = userBO.findById(lhVO.getCreator());
		
		model.addAttribute("lhVO", lhVO);
		model.addAttribute("lmiVO", lmiVO);
		model.addAttribute("pmflVO", pmflVO);
		model.addAttribute("userVO", userVO);
		
		model.addAttribute("sftMap", Constants.SPECIAL_FAMILY_MAP);
		
//		UserVO userVO = userBO.findById(userId);
		
//		User user = new User();
//		user.setUserId(userVO.getUserId());
//		user.setEmailAddr(userVO.getEmailAddr());
//		user.setHomeAddr(userVO.getHomeAddr());
//		user.setHomePostCode(userVO.getHomePostCode());
//		user.setHomeTel(userVO.getHomeTel());
//		user.setLinkMobile(userVO.getLinkMobile());
//		user.setLoginName(userVO.getLoginName());
//		user.setLoginPassword(userVO.getLoginPassword());
//		user.setOfficeAddr(userVO.getOfficeAddr());
//		user.setOfficePostCode(userVO.getOfficePostCode());
//		user.setOfficeTel(userVO.getOfficeTel());
//		user.setOnJob(userVO.getOnJob());
//		user.setUserName(userVO.getUserName());
//		
//		List<UserRoleVO> userRoleList = userBO.findUserRoleByUserId(userId);
//		String[] roleIds = new String[userRoleList.size()];
//		for (int i = 0; i < userRoleList.size(); i++) {
//			UserRoleVO userRoleVO = userRoleList.get(i);
//			roleIds[i] = userRoleVO.getId().getRoleId();
//		}
//		user.setRoleIds(roleIds);
//		model.addAttribute(user);
//		
//		List<RoleVO> roleVOList = roleBO.findAllRole();
//		model.addAttribute(roleVOList);
		return "specialfamily/helpdetail";
	}

}
