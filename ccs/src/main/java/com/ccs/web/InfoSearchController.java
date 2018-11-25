package com.ccs.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.InfoSearchResultDTO;
import com.ccs.bo.IAreaBO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IInfoSearchBO;
import com.ccs.bo.ILightPowerStaffBO;
import com.ccs.bo.IPowerInformationBO;
import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.AppInfoVO;
import com.ccs.vo.AreaSubVO;
import com.ccs.vo.AreaVO;
import com.ccs.vo.ElevatorVO;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.EventVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.PowerInformationVO;
import com.ccs.vo.PowerStaffVO;
import com.ccs.vo.ReferInformationVO;
import com.ccs.vo.UserVO;
import com.ccs.vo.VolunteerVO;

@Controller
@RequestMapping("/infosearch.do")
public class InfoSearchController {

	@Autowired
	private IInfoSearchBO infoSearchBO;

	@Autowired
	private IUserBO userBO;

	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IVolunteerBO volunteerBO;
	
	@Autowired
	private IEntpriseBO entpriseBO;
	
	@Autowired
	private IPowerInformationBO powerInforBO;
	
	@Autowired
	private ILightPowerStaffBO lpsBO;
	
	@Autowired
	private IAreaBO areaBO;
	
		
	@RequestMapping
	public String list(
			@ModelAttribute("infoSearchBean") InfoSearchBean infoSearchBean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model, HttpSession session) {

		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(null == pageNo ? 1 : Integer.valueOf(pageNo));
		List<InformationVO> list = infoSearchBO.findByParams(infoSearchBean,
				pageInfo);
		Map<String, UserVO> userMap = userBO.findAll();
		Map<String, String> dictMap = dictBO.getDict(Constants.DICT_DICTTYPE_MYD);
		Map<String, String> resultMap = infoSearchBO.getResult(list);
		
		List<InfoSearchResultDTO> dtoList = new ArrayList<InfoSearchResultDTO>();
		for (Iterator<InformationVO> iter = list.iterator(); iter.hasNext();) {
			InformationVO infoVO = iter.next();
			InfoSearchResultDTO dto = new InfoSearchResultDTO();
			dto.setInfoId(infoVO.getInfoId());
			dto.setAddress(infoVO.getHelpAddr());
			final String result = resultMap.get(infoVO.getInfoId());
			
			dto.setCallResult(null == result ? "" : dictMap.get(result));
			dto.setCreateTime(infoVO.getCreateTime());
			dto.setCreator(userMap.get(infoVO.getCreator()).getUserName());
			dto.setFinishTime(infoVO.getFinishTime());
			dto.setHelpContent(infoVO.getHelpContent());
			dto.setHelpName(infoVO.getHelpName());
			dto.setHelpTel(infoVO.getHelpTel());
			dto.setHelpType(infoVO.getHelpType());
			dto.setHelpTypeValue(Constants.INFOMATION_HELPTYPE_HASHMAP.get(infoVO.getHelpType()));
			dto.setStatus(Constants.SYS_INFOMATION_STATES_HASHMAP.get(infoVO.getStatus()));
			dtoList.add(dto);
		}
		
		model.addAttribute("dtoList", dtoList);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("infoSearchBean", infoSearchBean);
		
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		if(null != userBO.findUserRole(user.getUserId(), Constants.SYS_ROLE_ADMIN)) {
			model.addAttribute("adminRight", "Y");
		} else {
			model.addAttribute("adminRight", "N");
		}
		
		return "infosearch/list";
	}
	
	@RequestMapping(params = "action=lifeinfo")
	public String lifeInfo(String infoId, String pageNo, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		LifeInformationVO lifeInfoVO = infoSearchBO.findLifeInfoByInfoId(infoId);
		if(!Constants.SYS_INFOMATION_STATES_DB.equals(infoVO.getStatus()) && null != lifeInfoVO) {
			if(Constants.LIFEINFOMATION_RECEIVETYPE_ZYZ.equals(lifeInfoVO.getReceiverType())) {
				VolunteerVO volunteerVO = volunteerBO.findById(lifeInfoVO.getReceiverId());
				model.addAttribute("vltVO", volunteerVO);
			} else {
				EntpriseVO entpriseVO = entpriseBO.findEntByEntpriseId(lifeInfoVO.getReceiverId());
				model.addAttribute("entVO", entpriseVO);
			}
			
			model.addAttribute("lifeInfoVO", lifeInfoVO);
			
			if(null != lifeInfoVO.getPrincipal()) {
				model.addAttribute("caller", userBO.findById(lifeInfoVO.getPrincipal()));
			}
		}
		return "infosearch/lifeinfo";
	}
	
	@RequestMapping(params = "action=referinfo")
	public String referInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		ReferInformationVO referInfoVO = infoSearchBO.findReferInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("referInfoVO", referInfoVO);
		
		model.addAttribute("dealer", userBO.findById(referInfoVO.getDealer()));
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		return "infosearch/referinfo";
	}
	
	@RequestMapping(params = "action=productivityinfo")
	public String productivityInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		ReferInformationVO referInfoVO = infoSearchBO.findReferInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("referInfoVO", referInfoVO);
		
		model.addAttribute("dealer", userBO.findById(referInfoVO.getDealer()));
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		return "infosearch/productivityinfo";
	}
	
	
	@RequestMapping(params = "action=affairinfo")
	public String affairInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		
		AffairInformationVO affairInfoVO = infoSearchBO.findAffairInfoByInfoId(infoId);
		
		if(null != affairInfoVO) {
			model.addAttribute("affairInfoVO", affairInfoVO);
			
			if(null != affairInfoVO.getPrincipal()) {
				model.addAttribute("caller", userBO.findById(affairInfoVO.getPrincipal()));
			}
		}
		return "infosearch/affairinfo";
	}
	
	@RequestMapping(params = "action=powerinfo")
	public String powerInfo(String infoId, ModelMap model) throws Exception {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZFS));
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		model.addAttribute("dealer", userBO.findById(infoVO.getCreator()));

		PowerInformationVO piVO = powerInforBO.findByInfoId(infoId).get(0);
		
		if(null != piVO) {
			model.addAttribute("piVO", piVO);
			
			if(null != piVO.getPowerStaffId()) {
				PowerStaffVO psVO = lpsBO.findPowerStaffById(piVO.getPowerStaffId());
				model.addAttribute("psVO", psVO);
				
//				PowerStaffAreaVO psaVO = lpsBO.findPSAById(psVO.getPid()).get(0);
				AreaSubVO asVO = areaBO.findByAreaSubId(piVO.getAreaSubId());
				model.addAttribute("asVO", asVO);
				AreaVO areaVO = areaBO.findByAreaId(asVO.getAreaId());
				model.addAttribute("areaVO", areaVO);
				
			}
		}
		return "infosearch/powerinfo";
	}
	
	
	
	@RequestMapping(params = "action=appinfo")
	public String appInfo(String infoId, String pageNo, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		AppInfoVO appInfoVO = infoSearchBO.findAppInfoByInfoId(infoVO.getCallId());
		model.addAttribute("appInfoVO", appInfoVO);
		return "infosearch/appinfo";
	}
	
	
	@RequestMapping(params = "action=elevatorinfo")
	public String elevatorInfo(String infoId, String pageNo, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		ElevatorVO eVO = infoSearchBO.findElevatorInfoByInfoId(infoId);
		model.addAttribute("elevatorVO", eVO);
		return "infosearch/elevatorinfo";
	}
	
	@RequestMapping(params = "action=elevinfo")
	public String sgptInfo(String infoId, String pageNo, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		
		model.addAttribute("infoVO", infoVO);
		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		
		model.addAttribute("areaMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		
		model.addAttribute("mydMap", dictBO.getDict(Constants.DICT_DICTTYPE_MYD));
		
		EventVO eventVO = infoSearchBO.findSgptInfoByInfoId(infoId);
		model.addAttribute("eventVO", eventVO);
		return "infosearch/sgptinfo";
	}
	
	@RequestMapping(params = "action=showinfo")
	public String showInfo(String infoId, ModelMap model) {
		InformationVO infoVO = infoSearchBO.findInfoByInfoId(infoId);
		if("6".equals(infoVO.getHelpMode())) {
			return "redirect:infosearch.do?action=appinfo&infoId=" + infoId;
		} else {
			if(Constants.INFOMATION_HELPTYPE_LIFE.equals(infoVO.getHelpType())) {
				return "redirect:infosearch.do?action=lifeinfo&infoId=" + infoId;
			} else if(Constants.INFOMATION_HELPTYPE_AFFAIR.equals(infoVO.getHelpType())) {
				return "redirect:infosearch.do?action=affairinfo&infoId=" + infoId;
			} else if(Constants.INFOMATION_HELPTYPE_REFER.equals(infoVO.getHelpType())) {
				return "redirect:infosearch.do?action=referinfo&infoId=" + infoId;
			} else if(Constants.INFOMATION_HELPTYPE_FERTILITY.equals(infoVO.getHelpType())) {
				return "redirect:infosearch.do?action=productivityinfo&infoId=" + infoId;
			} else if(Constants.INFOMATION_HELPTYPE_POWER.equals(infoVO.getHelpType())) {
				return "redirect:infosearch.do?action=powerinfo&infoId=" + infoId;
			} else if(Constants.INFOMATION_HELPTYPE_ELEVATOR.equals(infoVO.getHelpType())) {
				return "redirect:infosearch.do?action=elevatorinfo&infoId=" + infoId;
			} else if(Constants.INFOMATION_HELPTYPE_SGPT.equals(infoVO.getHelpType())) {
				return "redirect:infosearch.do?action=sgptinfo&infoId=" + infoId;
			} else {
				return "";
			}
		}
	}
}
