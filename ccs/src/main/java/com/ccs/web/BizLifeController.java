package com.ccs.web;

import java.util.ArrayList;
import java.util.Date;
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

import com.ccs.bean.ReceiverEntDTO;
import com.ccs.bean.ReceiverVolunteerDTO;
import com.ccs.bo.IBizLifeBO;
import com.ccs.bo.IDictBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.InformationVO;
import com.ccs.vo.LifeInformationVO;
import com.ccs.vo.UserVO;
import com.ccs.vo.VolunteerVO;
import com.ccs.web.domain.LifeDispatchBean;
import com.ccs.web.domain.ReceiverSearchDomain;

@Controller
@RequestMapping("/bizlife.do")
public class BizLifeController {

	@Autowired
	private IBizLifeBO bizLifeBO;
	
	@Autowired
	private IDictBO dictBO;
	
	@Autowired
	private IVolunteerBO volunteerBO;
	
	@Autowired
	private IEntpriseBO entpriseBO;

	@RequestMapping
	public String list(
			@RequestParam(value = "pageNo", required = false) String pageNo,
			HttpSession session, ModelMap model) {

		PageInfo pageInfo = new PageInfo();
		if (StringUtil.isNull(pageNo)) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);

		List<InformationVO> list = bizLifeBO.findByCreatorAndStatus(
				userVO.getUserId(), Constants.SYS_INFOMATION_STATES_DB,
				Constants.INFOMATION_HELPTYPE_LIFE, pageInfo);

		model.addAttribute("statusMap", Constants.SYS_INFOMATION_STATES_HASHMAP);
		model.addAttribute("infoList", list);
		model.addAttribute("pageInfo", pageInfo);
		return "life/list";
	}
	
	@RequestMapping(params = "action=del")
	public String del(String infoId, @RequestParam("pageNo") String pageNo, HttpSession session, ModelMap model) {
		UserVO userVO = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		bizLifeBO.del(infoId, userVO.getUserId());
		return "redirect:bizlife.do?pageNo=" + pageNo;
	}

	@RequestMapping(params = "action=dispatch")
	public String dispatch(@RequestParam("infoId") String infoId,
			@RequestParam("pageNo") String pageNo, ModelMap model) {
		InformationVO infoVO = bizLifeBO.findInfoByInfoId(infoId);

		model.addAttribute("infoVO", infoVO);
		model.addAttribute("pageNo", pageNo);
		LifeDispatchBean bean = new LifeDispatchBean();
		bean.setInfoId(infoVO.getInfoId());
		model.addAttribute("lifeDispatchBean", bean);
		model.addAttribute("qzqyMap", dictBO.getDict(Constants.DICT_DICTTYPE_QZQY));
		model.addAttribute("pdfsList", dictBO.findByType(Constants.DICT_DICTTYPE_LLFS));
		model.addAttribute("qzfsMap", dictBO.getDict(Constants.DICT_DICTTYPE_LLFS));
		return "life/dispatch";
	}
	
	@RequestMapping(params = "action=dispatchsave")
	public String dispatchSave(@ModelAttribute("lifeDispatchBean") LifeDispatchBean lifeDispatchBean,
			@RequestParam("pageNo") String pageNo, HttpSession session, ModelMap model) {
		LifeInformationVO vo = bizLifeBO.findLifeInfoByInfoId(lifeDispatchBean.getInfoId());
		InformationVO infoVO = bizLifeBO.findInfoByInfoId(lifeDispatchBean.getInfoId());
//		vo.setInfoId(lifeDispatchBean.getInfoId());
		vo.setReceiverType(lifeDispatchBean.getReceiverType());
		vo.setReceiverId(lifeDispatchBean.getReceiverId());
		UserVO user = (UserVO) session.getAttribute(Constants.SESSION_USER_KEY);
		infoVO.setDeliverMode(lifeDispatchBean.getHandMode());
		infoVO.setDeliverer(user.getUserId());
		infoVO.setDeliverTime(new Date());
		bizLifeBO.deliverLife(infoVO, vo);
		
		return "redirect:bizlife.do?pageNo=" + pageNo;
	}
	
	@RequestMapping(params = "action=receiver")
	public String receiverSearch(
			@ModelAttribute("receiverSearchDomain") ReceiverSearchDomain receiverSearchDomain,
			@RequestParam(value = "pageNo", required = false) String pageNo, ModelMap model) {
		PageInfo pageInfo = new PageInfo();
		if(null == pageNo) {
			pageInfo.setCurrentPage(1);
		} else {
			pageInfo.setCurrentPage(Integer.parseInt(pageNo));
		}
		model.addAttribute("pageInfo", pageInfo);
		if(StringUtil.isNull( receiverSearchDomain.getReceiverType())) {
			receiverSearchDomain = new ReceiverSearchDomain();
			receiverSearchDomain.setReceiverType("1");
		} 
		model.addAttribute("receiverSearchDomain", receiverSearchDomain);
		
		if("1".equals(receiverSearchDomain.getReceiverType())) {
			List<VolunteerVO> vList = volunteerBO.search(
					Constants.SYS_YESNO_YES, Constants.SYS_YESNO_YES,
					StringUtil.emptyToNull(receiverSearchDomain.getAreaId()),
					StringUtil.emptyToNull(receiverSearchDomain.getAreaSubId()),
					receiverSearchDomain.getVolunteerNo(),
					receiverSearchDomain.getServiceName(), pageInfo);
			Map<String, String> vltSrvCountMap = bizLifeBO.getVltSrvCount(null, null, null);
			Map<String, String> vltSrvTodayCountMap = bizLifeBO.getVltSrvCount(DateUtil.format(new Date(), "yyyy-MM-dd"), DateUtil.format(new Date(), "yyyy-MM-dd)"), null);
			List<ReceiverVolunteerDTO> list = new ArrayList<ReceiverVolunteerDTO>();
			for (Iterator<VolunteerVO> iter = vList.iterator(); iter.hasNext();) {
				VolunteerVO vltVO = (VolunteerVO) iter.next();
				ReceiverVolunteerDTO dto = new ReceiverVolunteerDTO();
				dto.setPhone(vltVO.getLinkTel() + (null == vltVO.getLinkMobile() ? "" : "  " + vltVO.getLinkMobile()));
				dto.setServiceName(vltVO.getServiceName());
				dto.setVolunteerId(vltVO.getVolunteerId());
				dto.setVolunteerNo(vltVO.getVolunteerNo());
				dto.setVolunteerName(vltVO.getVolunteerName());
				dto.setTotalDispatch(vltSrvCountMap.get(vltVO.getVolunteerId()));
				dto.setTodayDispatch(vltSrvTodayCountMap.get(vltVO.getVolunteerId()));
				list.add(dto);
			}
			
			model.addAttribute("vltDTOList", list);
		} else if("2".equals(receiverSearchDomain.getReceiverType())){
			List<EntpriseVO> eList = entpriseBO.findByParams(null, receiverSearchDomain.getEntpriseNo(),
					Constants.SYS_YESNO_YES,
					StringUtil.emptyToNull(receiverSearchDomain.getBigEntCategoryId()),
					StringUtil.emptyToNull(receiverSearchDomain.getSubEntCategoryId()),
					StringUtil.emptyToNull(receiverSearchDomain.getEntCategoryId()),
					Constants.SYS_YESNO_YES, null,  pageInfo);
			Map<String, String> entSrvCountMap = bizLifeBO.getEntSrvCount(null, null, null);
			Map<String, String> entSrvTodayCountMap = bizLifeBO.getEntSrvCount(DateUtil.format(new Date(), "yyyy-MM-dd"), DateUtil.format(new Date(), "yyyy-MM-dd)"), null);
			List<ReceiverEntDTO> list = new ArrayList<ReceiverEntDTO>();
			for (Iterator<EntpriseVO> iter = eList.iterator(); iter.hasNext();) {
				EntpriseVO entVO = iter.next();
				ReceiverEntDTO dto = new ReceiverEntDTO();
				dto.setEntpriseId(entVO.getEntpriseId());
				dto.setEntpriseName(entVO.getEntpriseName());
				dto.setEntpriseNo(entVO.getEntpriseNo());
				dto.setServiceTel(entVO.getServiceTel());
				dto.setTotalDispatch(entSrvCountMap.get(entVO.getEntpriseId()));
				dto.setTodayDispatch(entSrvTodayCountMap.get(entVO.getEntpriseId()));
				list.add(dto);
			}
			model.addAttribute("entDTOList", list);
		}
		
		return "life/receiversearch";
	}
	
}
