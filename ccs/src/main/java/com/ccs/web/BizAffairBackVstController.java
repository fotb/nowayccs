package com.ccs.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bo.IBizAffairBO;
import com.ccs.bo.IBizAffairBackVstBO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IUserBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.DictVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.UserVO;
import com.ccs.web.domain.AffairBackVstDomain;

@Controller
@RequestMapping("/bizaffairbackvst.do")
public class BizAffairBackVstController {

	@Autowired
	private IBizAffairBackVstBO bizAffairBackVstBO;
	
	@Autowired
	private IBizAffairBO bizAffariBO;
	
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IDictBO dictBO;
	
	@RequestMapping
	public String list(@RequestParam(value = "deliverer", required = false) String deliverer,
			@RequestParam(value = "pageNo", required = false) String pageNo, HttpSession session,
			ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.parseInt(pageNo));
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		String userId = null == deliverer ? user.getUserId() : deliverer;
		List<InformationVO> list = bizAffairBackVstBO.findByDeliverer(userId, Constants.INFOMATION_HELPTYPE_AFFAIR, pageInfo);
		
		List<UserVO> userList = userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SHYWCL);
		model.addAttribute("infoList", list);
		model.addAttribute("userList", userList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("deliverer", userId);
		return "affair/backvst/list";
	}
	
	@RequestMapping(params = "action=del")
	public String del(String infoId, @RequestParam("pageNo") String pageNo, HttpSession session, ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		bizAffariBO.del(infoId, userVO.getUserId());
		return "redirect:bizaffairbackvst.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=backvst")
	public String backVst(String infoId, String pageNo, ModelMap model, HttpSession session) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		if(null != userBO.findUserRole(userVO.getUserId(), Constants.SYS_ROLE_ADMIN)) {
			model.addAttribute("adminRight", "Y");
		} else {
			model.addAttribute("adminRight", "N");
		}
		
		InformationVO infoVO = bizAffairBackVstBO.findInfoByInfoId(infoId);
		
		AffairInformationVO affairInfoVO = bizAffairBackVstBO.findAffairInfoByInfoId(infoId);
		
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("affairInfoVO", affairInfoVO);
		AffairBackVstDomain affairBackVstDomain = new AffairBackVstDomain();
		affairBackVstDomain.setAnswerMode(affairInfoVO.getAnswerMode());
		affairBackVstDomain.setAnswerResult(affairInfoVO.getAnswerResult());
		affairBackVstDomain.setAnswerTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		affairBackVstDomain.setCallMode(affairInfoVO.getCallMode());
		affairBackVstDomain.setCallResult(affairInfoVO.getCallResult());
		affairBackVstDomain.setCallTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		affairBackVstDomain.setFinishTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
		affairBackVstDomain.setHelpApprove(affairInfoVO.getHelpApprove());
		if(!StringUtil.isNull(affairInfoVO.getPrincipal())) {
			affairBackVstDomain.setPrincipal(affairInfoVO.getPrincipal());
		} else {
			affairBackVstDomain.setPrincipal(userVO.getUserId());
		}
		affairBackVstDomain.setRemark(affairInfoVO.getRemark());
		affairBackVstDomain.setUnApproveCause(affairInfoVO.getUnApproveCause());
		affairBackVstDomain.setHelpAddr(infoVO.getHelpAddr());
		affairBackVstDomain.setHelpContent(infoVO.getHelpContent());
		
		model.addAttribute("affairBackVstDomain", affairBackVstDomain);
		
		List<DictVO> qzfsList = dictBO.findByType(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsList", qzfsList);
		
		List<DictVO> mydList = dictBO.findByType(Constants.DICT_DICTTYPE_MYD);
		model.addAttribute("mydList", mydList);
		
		Map<String, String> qzfsMap = dictBO.getDict(Constants.DICT_DICTTYPE_QZFS);
		model.addAttribute("qzfsMap", qzfsMap);
		
		Map<String, String> mydMap = dictBO.getDict(Constants.DICT_DICTTYPE_MYD);
		model.addAttribute("mydMap", mydMap);
		
		List<UserVO> userList = userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SHYWCL);
		model.addAttribute("userList", userList);
		
		Map<String, String> qzqyMap = dictBO.getDict(Constants.DICT_DICTTYPE_QZQY);
		model.addAttribute("qzqyMap", qzqyMap);
		
		model.addAttribute("pageNo", pageNo);
		return "affair/backvst/backvst";
	}
	
	@RequestMapping(params = "action=backvstsave")
	public String backVstSave(
			@ModelAttribute("affairBackVstDomain") AffairBackVstDomain affairBackVstDomain,
			@RequestParam("infoId") String infoId, @RequestParam("pageNo") String pageNo, HttpSession session, ModelMap model) {

		AffairInformationVO affairInfoVO = readyLifeInfoVO(affairBackVstDomain,
				infoId);
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		affairInfoVO.setCaller(user.getUserId());
		
		
		InformationVO infoVO = bizAffairBackVstBO.findInfoByInfoId(infoId);
		infoVO.setHelpAddr(affairBackVstDomain.getHelpAddr());
		infoVO.setHelpContent(affairBackVstDomain.getHelpContent());
		
		bizAffairBackVstBO.bizAffairFinish(affairInfoVO, infoVO);
//		return "redirect:bizaffairbackvst.do?action=backvst&infoId=" + infoId + "&pageNo=" + pageNo;
		return "redirect:bizaffairbackvst.do?pageNo=" + pageNo;
	}

	private AffairInformationVO readyLifeInfoVO(
			AffairBackVstDomain affairBackVstDomain, String infoId) {
		AffairInformationVO affairInfoVO = bizAffairBackVstBO.findAffairInfoByInfoId(infoId);
		affairInfoVO.setAnswerMode(affairBackVstDomain.getAnswerMode());
		affairInfoVO.setAnswerResult(affairBackVstDomain.getAnswerResult());
		affairInfoVO.setAnswerTime(DateUtil.parse(affairBackVstDomain.getAnswerTime(), "yyyy-MM-dd HH:mm:ss"));
		affairInfoVO.setCallMode(affairBackVstDomain.getCallMode());
		affairInfoVO.setCallResult(affairBackVstDomain.getCallResult());
		affairInfoVO.setCallTime(DateUtil.parse(affairBackVstDomain.getCallTime(), "yyyy-MM-dd HH:mm:ss"));
		affairInfoVO.setHelpApprove(affairBackVstDomain.getHelpApprove());
		affairInfoVO.setPrincipal(affairBackVstDomain.getPrincipal());
		affairInfoVO.setRemark(affairBackVstDomain.getRemark());
		affairInfoVO.setUnApproveCause(affairBackVstDomain.getUnApproveCause());
		return affairInfoVO;
	}
	
	@RequestMapping(params = "action=bizfinish")
	public String bizFinish(
			@ModelAttribute("affairBackVstDomain") AffairBackVstDomain affairBackVstDomain,
			@RequestParam("infoId") String infoId, @RequestParam("pageNo") String pageNo, ModelMap model) {
		AffairInformationVO affairInfoVO = readyLifeInfoVO(affairBackVstDomain,
				infoId);
		
		InformationVO infoVO = bizAffairBackVstBO.findInfoByInfoId(infoId);
		infoVO.setFinishTime(DateUtil.parse(affairBackVstDomain.getFinishTime(), "yyyy-MM-dd"));
		infoVO.setStatus(Constants.SYS_INFOMATION_STATES_YJA);
		infoVO.setHelpAddr(affairBackVstDomain.getHelpAddr());
		infoVO.setHelpContent(affairBackVstDomain.getHelpContent());
		
		bizAffairBackVstBO.bizAffairFinish(affairInfoVO, infoVO);
		return "redirect:bizaffairbackvst.do?&pageNo=" + pageNo;
	}
}
