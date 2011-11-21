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

import com.ccs.bo.IBizLifeBO;
import com.ccs.bo.IBizLifeBackVstBO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.vo.DictVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.UserVO;
import com.ccs.vo.VolunteerVO;
import com.ccs.web.domain.LifeBackVstDomain;

@Controller
@RequestMapping("/bizlifebackvst.do")
public class BizLifeBackVstController {

	@Autowired
	private IBizLifeBackVstBO bizLifeBackVstBO;
	
	@Autowired
	private IBizLifeBO bizLifeBO;
	
	@Autowired
	private IUserBO userBO;
	
	@Autowired
	private IVolunteerBO volunteerBO;
	
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
		List<InformationVO> list = bizLifeBackVstBO.findByDeliverer(userId, Constants.INFOMATION_HELPTYPE_LIFE, pageInfo);
		
		List<UserVO> userList = userBO.findUserByOpertaionId(Constants.SYS_PERMISSION_SHYWCL);
		model.addAttribute("infoList", list);
		model.addAttribute("userList", userList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("deliverer", userId);
		return "life/backvst/list";
	}
	
	@RequestMapping(params = "action=del")
	public String del(String infoId, @RequestParam("pageNo") String pageNo, HttpSession session, ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		bizLifeBO.del(infoId, userVO.getUserId());
		return "redirect:bizlifebackvst.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=backvst")
	public String backVst(String infoId, String pageNo, ModelMap model) {
		
		InformationVO infoVO = bizLifeBackVstBO.findInfoByInfoId(infoId);
		
		LifeInformationVO lifeInfoVO = bizLifeBackVstBO.findLifeInfoByInfoId(infoId);
		
		VolunteerVO vltVO = volunteerBO.findById(lifeInfoVO.getReceiverId());
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("lifeInfoVO", lifeInfoVO);
		model.addAttribute("vltVO", vltVO);
		LifeBackVstDomain lifeBackVstDomain = new LifeBackVstDomain();
		lifeBackVstDomain.setCallMode(lifeInfoVO.getCallMode());
		lifeBackVstDomain.setCallResult(lifeInfoVO.getCallResult());
		lifeBackVstDomain.setCallTime(DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		lifeBackVstDomain.setDealContent(lifeInfoVO.getDealContent());
		lifeBackVstDomain.setDealResult(lifeInfoVO.getDealResult());
		lifeBackVstDomain.setFinishTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
		lifeBackVstDomain.setHelpApprove(lifeInfoVO.getHelpApprove());
		lifeBackVstDomain.setPrincipal(lifeInfoVO.getPrincipal());
		lifeBackVstDomain.setRemark(lifeInfoVO.getRemark());
		lifeBackVstDomain.setUnApproveCause(lifeInfoVO.getUnApproveCause());
		lifeBackVstDomain.setHelpAddr(infoVO.getHelpAddr());
		lifeBackVstDomain.setHelpContent(infoVO.getHelpContent());
		
		model.addAttribute("lifeBackVstDomain", lifeBackVstDomain);
		
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
		return "life/backvst/backvst";
	}
	
	@RequestMapping(params = "action=backvstsave")
	public String backVstSave(
			@ModelAttribute("lifeBackVstDomain") LifeBackVstDomain lifeBackVstDomain,
			@RequestParam("infoId") String infoId, @RequestParam("pageNo") String pageNo, HttpSession session, ModelMap model) {

		LifeInformationVO lifeInfoVO = readyLifeInfoVO(lifeBackVstDomain,
				infoId);
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		lifeInfoVO.setCaller(user.getUserId());
		
		InformationVO infoVO = bizLifeBackVstBO.findInfoByInfoId(infoId);
		infoVO.setHelpAddr(lifeBackVstDomain.getHelpAddr());
		infoVO.setHelpContent(lifeBackVstDomain.getHelpContent());
		
		bizLifeBackVstBO.bizLifeFinish(lifeInfoVO, infoVO);
		return "redirect:bizlifebackvst.do?action=backvst&infoId=" + infoId + "&pageNo=" + pageNo;
	}

	private LifeInformationVO readyLifeInfoVO(
			LifeBackVstDomain lifeBackVstDomain, String infoId) {
		LifeInformationVO lifeInfoVO = bizLifeBackVstBO.findLifeInfoByInfoId(infoId);
		lifeInfoVO.setCallMode(lifeBackVstDomain.getCallMode());
		lifeInfoVO.setCallResult(lifeBackVstDomain.getCallResult());
		lifeInfoVO.setCallTime(DateUtil.parse(lifeBackVstDomain.getCallTime(), "yyyy-MM-dd HH:mm:ss"));
		lifeInfoVO.setDealContent(lifeBackVstDomain.getDealContent());
		lifeInfoVO.setDealResult(lifeBackVstDomain.getDealResult());
		lifeInfoVO.setHelpApprove(lifeBackVstDomain.getHelpApprove());
		lifeInfoVO.setPrincipal(lifeBackVstDomain.getPrincipal());
		lifeInfoVO.setRemark(lifeBackVstDomain.getRemark());
		lifeInfoVO.setUnApproveCause(lifeBackVstDomain.getUnApproveCause());
		return lifeInfoVO;
	}
	
	@RequestMapping(params = "action=bizfinish")
	public String bizFinish(
			@ModelAttribute("lifeBackVstDomain") LifeBackVstDomain lifeBackVstDomain,
			@RequestParam("infoId") String infoId, @RequestParam("pageNo") String pageNo, ModelMap model) {
		LifeInformationVO lifeInfoVO = readyLifeInfoVO(lifeBackVstDomain,
				infoId);
		
		InformationVO infoVO = bizLifeBackVstBO.findInfoByInfoId(infoId);
		infoVO.setFinishTime(DateUtil.parse(lifeBackVstDomain.getCallTime(), "yyyy-MM-dd"));
		infoVO.setStatus(Constants.SYS_INFOMATION_STATES_YJA);
		
		infoVO.setHelpAddr(lifeBackVstDomain.getHelpAddr());
		infoVO.setHelpContent(lifeBackVstDomain.getHelpContent());
		
		bizLifeBackVstBO.bizLifeFinish(lifeInfoVO, infoVO);
		return "redirect:bizlifebackvst.do?&pageNo=" + pageNo;
	}
}
