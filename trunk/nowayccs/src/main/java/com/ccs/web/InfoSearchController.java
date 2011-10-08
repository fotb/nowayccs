package com.ccs.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bean.InfoSearchBean;
import com.ccs.bean.InfoSearchResultDTO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IInfoSearchBO;
import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.PageInfo;
import com.ccs.vo.AffairInformationVO;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
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
	
	@RequestMapping
	public String list(
			@ModelAttribute("infoSearchBean") InfoSearchBean infoSearchBean,
			@RequestParam(value = "pageNo", required = false) String pageNo,
			ModelMap model) {

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
		if(null != lifeInfoVO) {
			if(Constants.LIFEINFOMATION_RECEIVETYPE_ZYZ.equals(lifeInfoVO.getReceiverType())) {
				VolunteerVO volunteerVO = volunteerBO.findById(lifeInfoVO.getReceiverId());
				model.addAttribute("vltVO", volunteerVO);
			} else {
				EntpriseVO entpriseVO = entpriseBO.findEntByEntpriseId(lifeInfoVO.getReceiverId());
				model.addAttribute("entVO", entpriseVO);
			}
			
			model.addAttribute("lifeInfoVO", lifeInfoVO);
			
			if(null != lifeInfoVO.getCaller()) {
				model.addAttribute("caller", userBO.findById(lifeInfoVO.getCaller()));
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
	public String affairInfo(String infoId, String pageNo, ModelMap model) {
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
}
