package com.ccs.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ccs.bean.ReceiverEntDTO;
import com.ccs.bean.ReceiverVolunteerDTO;
import com.ccs.bo.IBizLifeBO;
import com.ccs.bo.IEntpriseBO;
import com.ccs.bo.IUserBO;
import com.ccs.bo.IVolunteerBO;
import com.ccs.util.Constants;
import com.ccs.util.DateUtil;
import com.ccs.util.PageInfo;
import com.ccs.util.StringUtil;
import com.ccs.vo.EntpriseVO;
import com.ccs.vo.UserVO;
import com.ccs.vo.VolunteerVO;
import com.ccs.web.domain.ReceiverSearchDomain;

@Controller
@RequestMapping("/delivercount.do")
public class DeliverCountController {

	@Autowired
	private IBizLifeBO bizLifeBO;
	
	@Autowired
	private IVolunteerBO volunteerBO;
	
	@Autowired
	private IEntpriseBO entpriseBO;
	
	@Autowired
	private IUserBO userBO;
	
	@RequestMapping
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
		Map<String, UserVO> userMap = userBO.findOnJob();
		model.addAttribute("users", userMap.values());
		
		if("1".equals(receiverSearchDomain.getReceiverType())) {
			List<VolunteerVO> vList = volunteerBO.search(
					Constants.SYS_YESNO_YES, Constants.SYS_YESNO_YES,
					StringUtil.emptyToNull(receiverSearchDomain.getAreaId()),
					StringUtil.emptyToNull(receiverSearchDomain.getAreaSubId()),
					receiverSearchDomain.getVolunteerNo(),
					receiverSearchDomain.getServiceName(), pageInfo);
			Map<String, String> vltSrvCountMap = bizLifeBO.getVltSrvCount(receiverSearchDomain.getStartDt(), receiverSearchDomain.getEndDt(), receiverSearchDomain.getCreator());
			Map<String, String> vltSrvTodayCountMap = bizLifeBO.getVltSrvCount(DateUtil.format(new Date(), "yyyy-MM-dd"), DateUtil.format(new Date(), "yyyy-MM-dd)"), receiverSearchDomain.getCreator());
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
					Constants.SYS_YESNO_YES, null, pageInfo);
			Map<String, String> entSrvCountMap = bizLifeBO.getEntSrvCount(receiverSearchDomain.getStartDt(), receiverSearchDomain.getEndDt(), receiverSearchDomain.getCreator());
			Map<String, String> entSrvTodayCountMap = bizLifeBO.getEntSrvCount(DateUtil.format(new Date(), "yyyy-MM-dd"), DateUtil.format(new Date(), "yyyy-MM-dd)"), receiverSearchDomain.getCreator());
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
		
		return "delivercount/count";
	}
	
}
